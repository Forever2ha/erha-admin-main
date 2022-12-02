package fun.yizhierha.monitor.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.common.utils.file.ExcelUtils;
import fun.yizhierha.monitor.config.SecurityProperties;
import fun.yizhierha.monitor.domain.vo.RetrieveOnlineUserVo;
import fun.yizhierha.monitor.service.OnlineUserService;
import fun.yizhierha.monitor.service.dto.OnlineUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnlineUserServiceImpl implements OnlineUserService {
    private final RedisUtils redisUtils;

    private final SecurityProperties securityProperties;

    public OnlineUserDto getByToken(String token) {
        return (OnlineUserDto) redisUtils.get(securityProperties.getOnlineKey() + token);
    }

    public void save(String deptName,
                     String username,
                     String nickName,
                     String token, HttpServletRequest request) {
        String encryptToken = EncryptUtils.desEncrypt(token);
        String ip = StringUtils.getIpByRequest(request);
        String browser = StringUtils.getBrowserByRequest(request);
        String address = StringUtils.getCityInfoByIp(ip);

        OnlineUserDto od = new OnlineUserDto();
        od.setAddress(address);od.setBrowser(browser);od.setDept(deptName);od.setEncryptKey(encryptToken);
        od.setIp(ip);od.setUserName(username);od.setNickName(nickName);
        od.setLoginTime(new Timestamp(new Date().getTime()));
        //放入redis
        redisUtils.set(securityProperties.getOnlineKey()+token,od,securityProperties.getTokenValidityInMinutes()*60);
    }


    public void removeIfExist(String username) {
        List<String> toDelTokens = new ArrayList<>();
        //查出要删除的token
        list().forEach((onlineUserDto -> {
            if (username.equalsIgnoreCase(onlineUserDto.getUserName())){
                toDelTokens.add(securityProperties.getOnlineKey()+onlineUserDto.getEncryptKey());
            }
        }));
        //删除
        redisUtils.del(toDelTokens.toArray(new String[0]));
    }

    public void logout(String token) {
        redisUtils.del(securityProperties.getOnlineKey() + token);
    }

    @Override
    public void removeIfExist(Collection<String> usernames) {
        Map<String,Object> map = new HashMap<>();
        for (String username : usernames) {
            map.put(username,new Object());
        }
        List<String> toDelTokens = new ArrayList<>();
        //查出要删除的token
        list().forEach((onlineUserDto -> {
            if (map.get(onlineUserDto.getUserName()) != null){
                try {
                    toDelTokens.add(securityProperties.getOnlineKey()+EncryptUtils.desDecrypt(onlineUserDto.getEncryptKey()));
                } catch (Exception e) {
                    throw new BadRequestException(e.getMessage());
                }
            }
        }));
        //删除
        redisUtils.del(toDelTokens.toArray(new String[0]));
    }

    @Override
    public void download(HttpServletResponse response) {
        ExcelUtils.export(response,"在线用户信息表",this.list(), OnlineUserDto.class);
    }


    @Override
    public List<OnlineUserDto> list() {
        //查出所有在线用户redis中的key
        List<String> keys = redisUtils.scan(securityProperties.getOnlineKey() + "*");
        List<OnlineUserDto> onlineUserDtos = new ArrayList<>();

        //查出所有在线用户的信息
        keys.forEach((key)->{
            onlineUserDtos.add((OnlineUserDto) redisUtils.get(key));
        });
        return onlineUserDtos;
    }

    @Override
    public PageUtils<OnlineUserDto> list(RetrieveOnlineUserVo retrieveOnlineUserVo, Query.PageVo pageVo) {
        IPage<OnlineUserDto> page = new Query<OnlineUserDto>().getPage(pageVo);
        List<OnlineUserDto> onlineUserDtoList = list();
        List<OnlineUserDto> result = new ArrayList<>();

        // 检索符合条件的
        String userName = retrieveOnlineUserVo.getUserName();
        String nickname = retrieveOnlineUserVo.getNickname();
        String dept = retrieveOnlineUserVo.getDept();
        String browser = retrieveOnlineUserVo.getBrowser();
        String ip = retrieveOnlineUserVo.getIp();
        String address = retrieveOnlineUserVo.getAddress();
        Timestamp startLoginTime = retrieveOnlineUserVo.getStartLoginTime();
        Timestamp endLoginTime = retrieveOnlineUserVo.getEndLoginTime();

        for (OnlineUserDto onlineUserDto : onlineUserDtoList) {
            if (userName != null){
                if (!onlineUserDto.getUserName().contains(userName)) continue;
            }
            if (nickname != null){
                if (!onlineUserDto.getNickName().contains(nickname)) continue;
            }
            if (dept != null){
                if (!onlineUserDto.getDept().contains(dept)) continue;
            }
            if (browser != null){
                if (!onlineUserDto.getBrowser().contains(browser)) continue;
            }
            if (ip != null){
                if (!ip.equals(onlineUserDto.getIp())) continue;
            }
            if (address != null){
                if (!onlineUserDto.getAddress().contains(address)) continue;
            }

            if (startLoginTime != null && endLoginTime != null){
                long time = onlineUserDto.getLoginTime().getTime();
                long start = startLoginTime.getTime();
                long end = endLoginTime.getTime();
                if (!(time >= start && time <= end)) continue;
            }

            result.add(onlineUserDto);

        }
        int total = result.size();
        int size = (int) page.getSize();
        int current = (int) page.getCurrent();

        int fromIndex = (current-1)*size;
        if (fromIndex > result.size()-1){
            return new PageUtils<OnlineUserDto>(result,total,size,current);
        }
        int toIndex = fromIndex + size;
        if (toIndex > result.size()-1){
          toIndex = result.size();
        }
        result = result.subList(fromIndex,toIndex);

        return new PageUtils<OnlineUserDto>(result,total,size,current);
    }


}
