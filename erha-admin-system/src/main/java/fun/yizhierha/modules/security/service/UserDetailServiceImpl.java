package fun.yizhierha.modules.security.service;

import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.modules.security.config.LoginProperties;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service("userDetailService")
@RequiredArgsConstructor
@SuppressWarnings("all")
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    SysUserService userService;

    //userDetailDto用户信息缓存管理
    public final UserCacheManager userCacheManager;
    //login配置
    public final LoginProperties loginProperties;

    //线程池
    public static ExecutorService executorService = newThreadPool();


    private static ExecutorService newThreadPool() {
        ThreadFactory namedThreadFactory = new ThreadFactory() {
            final AtomicInteger sequence = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                int seq = this.sequence.getAndIncrement();
                thread.setName("future-task-thread-" + seq);
                if (!thread.isDaemon()) {
                    thread.setDaemon(true);
                }

                return thread;
            }
        };
        return new ThreadPoolExecutor(10, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //不开启缓存
        if (!loginProperties.isCacheEnable()){
            UserDetailsDto userDetailsDto = getUserDetailDtoFromDb(username);
            if (userDetailsDto.getUser().getEnabled()){
                return userDetailsDto;
            }
            //走到这里说明账号未激活
            throw new BadRequestException("账号未激活！");
        }

        //缓存中拿

        FutureTask<UserDetailsDto> futureTask = userCacheManager.getByUsername(username);

        UserDetailsDto userDetailsDto = null;
        if (futureTask == null){
            Callable<UserDetailsDto> callable = ()->getUserDetailDtoFromDb(username);
            futureTask = new FutureTask<>(callable);
            //放入缓存
            userCacheManager.putIfAbsent(username,futureTask);
            executorService.submit(futureTask);
        }

        try {
            userDetailsDto = futureTask.get();
            if (userDetailsDto == null){
                throw new UsernameNotFoundException("此用户不存在");
            }else {
                if (!userDetailsDto.getUser().getEnabled()){
                    throw new BadRequestException(BizCodeEnum.Client_Error_Login_Fail.getCode(), BizCodeEnum.Client_Error_Login_Fail.getMsg(),"用户未激活");
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            userCacheManager.cleanCacheByUsername(username);
            e.printStackTrace();
        }

        return userDetailsDto;

    }

    private UserDetailsDto getUserDetailDtoFromDb(String username) {
        UserDetailsDto userDetailsDto = userService.selectUserDetailDtoByUsername(username);
        return userDetailsDto;
    }


}
