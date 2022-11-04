package fun.yizhierha.modules.security.service;

import fun.yizhierha.common.utils.EncryptUtils;
import fun.yizhierha.common.utils.RedisUtils;
import fun.yizhierha.common.utils.StringUtils;
import fun.yizhierha.modules.security.config.SecurityProperties;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.security.service.dto.OnlineUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OnlineUserService {
    private final SecurityProperties securityProperties;
    private final RedisUtils redisUtils;

    public OnlineUserService(SecurityProperties securityProperties, RedisUtils redisUtils) {
        this.securityProperties = securityProperties;
        this.redisUtils = redisUtils;
    }

    public OnlineUserDto getByToken(String token) {
        return (OnlineUserDto) redisUtils.get(securityProperties.getOnlineKey() + token);
    }

    public void save(UserDetailsDto userDetailsDto, String token, HttpServletRequest request) {
        String encryptToken = EncryptUtils.desEncrypt(token);
        String dept = userDetailsDto.getUser().getDeptName();
        String ip = StringUtils.getIpByRequest(request);
        String browser = StringUtils.getBrowserByRequest(request);
        String address = StringUtils.getCityInfoByIp(ip);

        OnlineUserDto od = new OnlineUserDto();
        od.setAddress(address);od.setBrowser(browser);od.setDept(dept);od.setEncryptKey(encryptToken);
        od.setIp(ip);od.setUserName(userDetailsDto.getUsername());od.setNickName(userDetailsDto.getUser().getNickName());
        od.setLoginTime(new Date());
        //放入redis
        redisUtils.set(securityProperties.getOnlineKey()+token,od,securityProperties.getTokenValidityInMinutes()*60);
    }


    public void removeIfExist(String username) {
        //查出所有在线用户redis中的key
        List<String> keys = redisUtils.scan(securityProperties.getOnlineKey() + "*");
        List<OnlineUserDto> onlineUserDtos = new ArrayList<>();


        //查出所有在线用户的信息
        keys.forEach((key)->{
            onlineUserDtos.add((OnlineUserDto) redisUtils.get(key));
        });
        List<String> toDelTokens = new ArrayList<>();
        //查出要删除的token
        onlineUserDtos.forEach((onlineUserDto -> {
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
}
