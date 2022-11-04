package fun.yizhierha.modules.security.service;

import fun.yizhierha.common.utils.StringUtils;
import fun.yizhierha.modules.security.config.UserCacheProperties;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Slf4j
public class UserCacheManager {

    @Autowired
    private UserCacheProperties userCacheProperties;


    //user缓存FutureCache<UserDetailsDto>
    public final Map<String,Node> cache = new ConcurrentHashMap<>();
    //原子锁,防止多次清理缓存
    private final AtomicBoolean expelLock = new AtomicBoolean(true);
    //
    private long nextMinEvictableTime = 0;


    /**
     * 根据username清除缓存
     * @param username 要清除的姓名
     */
    public void cleanCacheByUsername(String username){
        if (StringUtils.isNotBlank(username)){
            cache.remove(username);
        }
    }

    /**
     * 获取所有缓存
     * @return
     */
    public List<UserDetailsDto> getAllCache(){
        List<UserDetailsDto> res = new ArrayList<>();
        cache.forEach((key,node) ->{
            try {
                res.add(node.jwtUserDtoFutureTask.get());
            } catch (Exception e) {

            }
        });
        return res;
    }

    /**
     * 清理所有缓存
     */
    public void clearAllCache(){cache.clear();}

    public FutureTask<UserDetailsDto> putIfAbsent(String username, FutureTask<UserDetailsDto> task){
        Node node = cache.putIfAbsent(username, new Node(task));
        expel();
        return nodeToData(node);
    }


    /**
     * 缓存回收
     * 为避免超过边界后回收热点数据设置了最小生存时间
     * 回收时会保留在最小生存时间内的数据
     **/
    public void expel() {
        // todo 这里还没看明白，有时间看看
        long now = System.currentTimeMillis();
        if (cache.size() < userCacheProperties.getMinEvictableSize() ||
                now < nextMinEvictableTime ||
                //有线程去清理当前线程就不用去清理了
                !expelLock.compareAndSet(true, false)) {
            return;
        }
        long oldestTime = now;
        int evictedCount = 0;
        try {
            Iterator<Map.Entry<String, Node>> iterator = cache.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Node> entry = iterator.next();
                long nodeTime = entry.getValue().getTime();
                if (nodeTime + userCacheProperties.getMinIdleTime() < now) {
                    iterator.remove();
                    evictedCount++;
                }
                oldestTime = Math.min(oldestTime, nodeTime);
            }
        } finally {
            this.nextMinEvictableTime = Math.max(now + userCacheProperties.getMinEvictableInterval(), oldestTime);
            expelLock.set(true);
            log.info("回收掉【{}】条用户缓存, 剩余缓存数为【{}】,下次可回收时间为【{}】秒后",
                    evictedCount,
                    cache.size(),
                    (this.nextMinEvictableTime - now) / 1000);
        }
    }


    private FutureTask<UserDetailsDto> nodeToData(Node node) {
        return node == null ? null:node.getJwtUserDtoFutureTask();
    }

    public FutureTask<UserDetailsDto> getByUsername(String username) {
        Node node = cache.get(username);
        return node == null ? null:node.getJwtUserDtoFutureTask();
    }

    public void cleanCacheByRoleIds(Collection<Long> roleIdList) {
        List<UserDetailsDto> allCache = this.getAllCache();
        for (UserDetailsDto userDetailsDto : allCache) {
            if (roleIdList.contains(userDetailsDto.getNowRole().getRoleId())){
                this.cleanCacheByUsername(userDetailsDto.getUsername());
            }
        }

    }

    @Getter
    public static class Node{
        private final FutureTask<UserDetailsDto> jwtUserDtoFutureTask;
        private final long time;

        public Node(FutureTask<UserDetailsDto> jwtUserDtoFutureTask) {
            this.jwtUserDtoFutureTask = jwtUserDtoFutureTask;
            this.time = System.currentTimeMillis();
        }
    }

}
