package fun.yizhierha.common.config;

import fun.yizhierha.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("eh")
@Slf4j
public class EhPermissionConfig {

    public Boolean check(String ...permissions){
        UserDetails currentUser = SecurityUtils.getCurrentUser();

        //获取当前用户所有权限
        List<String> collect = currentUser
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        boolean hasAuth = collect.contains("admin") || Arrays.stream(permissions).anyMatch(collect::contains);
        if (!hasAuth){
            log.warn("用户:{} 试图访问其没有权限({})的接口",currentUser.getUsername(), Arrays.toString(permissions));
        }
        //判断是否有权限
        return hasAuth;
    }
}
