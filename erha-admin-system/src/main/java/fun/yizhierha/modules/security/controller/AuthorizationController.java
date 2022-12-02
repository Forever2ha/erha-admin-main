package fun.yizhierha.modules.security.controller;

import cn.hutool.core.util.IdUtil;
import com.wf.captcha.base.Captcha;
import fun.yizhierha.common.annotation.rest.AnonymousGetMapping;
import fun.yizhierha.common.annotation.rest.AnonymousPostMapping;
import fun.yizhierha.common.config.RsaEncryptConfig;
import fun.yizhierha.common.utils.EncryptUtils;
import fun.yizhierha.common.utils.R;
import fun.yizhierha.common.utils.RedisUtils;
import fun.yizhierha.common.utils.StringUtils;
import fun.yizhierha.modules.security.config.LoginCodeEnum;
import fun.yizhierha.modules.security.config.LoginProperties;
import fun.yizhierha.monitor.config.SecurityProperties;
import fun.yizhierha.modules.security.security.TokenProvider;
import fun.yizhierha.modules.security.service.dto.CaptchaDTO;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.security.service.dto.UserInfoDto;
import fun.yizhierha.modules.security.vo.AuthUserVo;
import fun.yizhierha.monitor.service.OnlineUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;
import static fun.yizhierha.common.exception.BizCodeEnum.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Api(tags = "授权接口")
public class AuthorizationController {
    private final SecurityProperties securityProperties;
    private final LoginProperties loginProperties;
    private final RedisUtils redisUtils;
    private final OnlineUserService onlineUserService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RsaEncryptConfig rsaEncryptConfig;

    @ApiOperation("登录")
    @AnonymousPostMapping("/login")
    public R<UserInfoDto> login(@Validated @RequestBody AuthUserVo authUserVo, HttpServletRequest request) throws Exception {
        String password = EncryptUtils.rsaDecryptByPrivateKey(rsaEncryptConfig.getPrivateKey(),authUserVo.getPassword());
        //查验证码
        String captchaValue = (String) redisUtils.get(authUserVo.getUuid());
        //删除验证码
        redisUtils.del(authUserVo.getUuid());
        if (StringUtils.isBlank(captchaValue)){
            throw new BadCredentialsException("验证码过期");
        }
        if (!authUserVo.getCaptcha().equalsIgnoreCase(captchaValue)){
            throw new BadCredentialsException("验证码错误");
        }
        //springSecurity授权登录，
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authUserVo.getUsername(), password);
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String token = tokenProvider.createToken(authenticate);
        UserDetailsDto userDetailsDto = (UserDetailsDto) authenticate.getPrincipal();

        //返回前端数据
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserDetailsDto(userDetailsDto);
        userInfoDto.setToken(securityProperties.getTokenStartWith() + token);

        //是否为单点登录
        if (!loginProperties.isSingleLogin()){
            //踢掉之前的token
          onlineUserService.removeIfExist(authUserVo.getUsername());
        }


        String username = userDetailsDto.getUsername();
        String deptName = userDetailsDto.getUser().getDeptName();
        String nickName = userDetailsDto.getUser().getNickName();
        //保存在线信息
        onlineUserService.save(deptName,username,nickName,token,request);

        return R.<UserInfoDto>ok().setData(userInfoDto);
    }

    @ApiOperation("登出")
    @AnonymousPostMapping("/logout")
    public R logout(HttpServletRequest request){
        onlineUserService.logout(tokenProvider.getToken(request));
        return R.ok();
    }



    @ApiOperation("获取验证码")
    @AnonymousGetMapping("/code")
    public R<CaptchaDTO> getCode(){
        //获取运算的结果
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = securityProperties.getCodeKey() + IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.ARITHMETIC.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        boolean saveSuccess = redisUtils.set(uuid, captchaValue, loginProperties.getLoginCodeProperties().getExpiration(), TimeUnit.MINUTES);
        if (saveSuccess){
            CaptchaDTO captchaDTO = new CaptchaDTO();
            captchaDTO.setUuid(uuid);
            captchaDTO.setImg(captcha.toBase64());
            return R.<CaptchaDTO>ok().setData(captchaDTO);
        }else {
            return R.error(Internal_Error_Redis_ConnFail.getCode(), Internal_Error_Redis_ConnFail.getMsg());
        }
    }

}
