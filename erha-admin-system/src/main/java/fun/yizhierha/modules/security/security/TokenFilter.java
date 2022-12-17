package fun.yizhierha.modules.security.security;

import com.alibaba.fastjson.JSON;
import fun.yizhierha.common.utils.R;
import fun.yizhierha.common.utils.StringUtils;
import fun.yizhierha.monitor.config.SecurityProperties;
import fun.yizhierha.monitor.service.OnlineUserService;
import fun.yizhierha.modules.security.service.UserCacheManager;
import fun.yizhierha.monitor.service.dto.OnlineUserDto;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import static fun.yizhierha.common.exception.BizCodeEnum.*;


@RequiredArgsConstructor
public class TokenFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;
    private final SecurityProperties securityProperties;
    private final OnlineUserService onlineUserService;
    private final UserCacheManager userCacheManager;


    //访问接口先进来这里
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = resolveToken(httpServletRequest,servletResponse);
        //token不为空则查redis
        if (StringUtils.isNotBlank(token) ){

            OnlineUserDto onlineUserDto = onlineUserService.getByToken(token);
            //当前用户是在线用户则授权
            if (onlineUserDto != null){
                //根据token获取authentication
                Authentication authentication = tokenProvider.getAuthentication(token);
                //授权
                SecurityContextHolder.getContext().setAuthentication(authentication);
                //token续期
                tokenProvider.checkRenewal(token);
            }else {
                //token可能有问题，try catch一下
                try {
                    userCacheManager.cleanCacheByUsername(String.valueOf(tokenProvider.getClaims(token).get(TokenProvider.AUTHORITIES_KEY)));
                }catch (JwtException e){
                    logger.debug("token存在问题->"+e.getMessage());
                    servletResponse.setCharacterEncoding("UTF-8");
                    servletResponse.setContentType("application/json");
                    servletResponse.getWriter().println(JSON.toJSONString(R.error(Client_Error_Token_ExpiredOrNotExist.getCode(), "token有误！")));
                    servletResponse.getWriter().flush();
                    return;
                }

            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 预处理token
     * @param request
     * @param response
     * @return
     */
    private String resolveToken(HttpServletRequest request, ServletResponse response) {
        String header = request.getHeader(securityProperties.getHeader());

        if (StringUtils.isBlank(header)) return "";
        //todo 这里有个诡异的问题

        /*logger.warn(header.startsWith(securityProperties.getTokenStartWith()));
        logger.warn(header);
        logger.warn(securityProperties.getTokenStartWith());
*/
        if (header.startsWith(securityProperties.getTokenStartWith())){
            //去掉前缀
            header = header.replace(securityProperties.getTokenStartWith(),"");
            return header;
        }else {
            logger.debug("非法token");
        }

        return "";
    }


}
