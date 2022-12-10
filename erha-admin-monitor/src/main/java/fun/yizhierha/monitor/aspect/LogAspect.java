/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package fun.yizhierha.monitor.aspect;

import fun.yizhierha.common.utils.SecurityUtils;
import fun.yizhierha.common.utils.StringUtils;
import fun.yizhierha.common.utils.ValidUtils;
import fun.yizhierha.monitor.domain.SysLog;
import fun.yizhierha.monitor.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zheng Jie
 * @date 2018-11-24
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    private final SysLogService sysLogService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    public LogAspect(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(fun.yizhierha.common.annotation.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
        SysLog log = new SysLog();
        log.setLogType("INFO");
        log.setTime(System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        sysLogService.save(getUsername(), StringUtils.getBrowserByRequest(request), StringUtils.getIpByRequest(request),joinPoint, log);
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        SysLog log = new SysLog();
        log.setLogType("ERROR");
        log.setTime(System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        log.setExceptionDetail(ValidUtils.getStackTrace(e));
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        sysLogService.save(getUsername(), StringUtils.getBrowserByRequest(request), StringUtils.getIpByRequest(request), (ProceedingJoinPoint)joinPoint, log);
    }

    public String getUsername() {
        try {
            return SecurityUtils.getCurrentUser().getUsername();
        }catch (Exception e){
            return "";
        }
    }
}
