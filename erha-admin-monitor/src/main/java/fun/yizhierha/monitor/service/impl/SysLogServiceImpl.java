package fun.yizhierha.monitor.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.SecurityUtils;
import fun.yizhierha.common.utils.StringUtils;
import fun.yizhierha.common.utils.file.ExcelUtils;
import fun.yizhierha.monitor.domain.SysLog;
import fun.yizhierha.monitor.domain.vo.RetrieveSysLogVo;
import fun.yizhierha.monitor.mapper.SysLogMapper;
import fun.yizhierha.monitor.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

/** generated by EH-Admin
* @author 二哈
* @date Fri Dec 09 20:47:12 CST 2022
**/
@Service
@RequiredArgsConstructor
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService{


    @Override
    public PageUtils<SysLog> list(RetrieveSysLogVo retrieveSysLogVo, Query.PageVo pageVo) {
        QueryWrapper<SysLog> wrapper = new QueryWrapper<>();
        String description = retrieveSysLogVo.getDescription();
        String logType = retrieveSysLogVo.getLogType();
        String params = retrieveSysLogVo.getParams();
        String requestIp = retrieveSysLogVo.getRequestIp();
        String username = retrieveSysLogVo.getUsername();
        String address = retrieveSysLogVo.getAddress();
        String browser = retrieveSysLogVo.getBrowser();
        Long startTime = retrieveSysLogVo.getStartTime();
        Long endTime = retrieveSysLogVo.getEndTime();
        Timestamp startCreateTime = retrieveSysLogVo.getStartCreateTime();
        Timestamp endCreateTime = retrieveSysLogVo.getEndCreateTime();

        if (description != null){
            wrapper.like(SysLog.COL_DESCRIPTION,description);
        }
        if (logType != null){
            wrapper.eq(SysLog.COL_LOG_TYPE,logType);
        }
        if (params != null){
            wrapper.like(SysLog.COL_PARAMS,params);
        }
        if (requestIp != null){
            wrapper.eq(SysLog.COL_REQUEST_IP,requestIp);
        }
        if (username != null){
            wrapper.like(SysLog.COL_USERNAME,username);
        }
        if (address != null){
            wrapper.like(SysLog.COL_ADDRESS,address);
        }
        if (browser != null){
            wrapper.like(SysLog.COL_BROWSER,browser);
        }

        if (startTime != null && endTime != null){
            wrapper.between(SysLog.COL_TIME,startTime,endTime);
        }
        if (startCreateTime != null && endCreateTime != null){
            wrapper.between(SysLog.COL_CREATE_TIME,startCreateTime,endCreateTime);
        }
        wrapper.orderByDesc(SysLog.COL_LOG_ID);

        IPage<SysLog> iPage = baseMapper.selectPage(new Query<SysLog>().getPage(pageVo), wrapper);
        return new PageUtils<>(iPage);
    }


    @Override
    public void remove(Set<Long> ids) {
        this.removeByIds(ids);
    }

    @Override
    public void download(HttpServletResponse response) {
        ExcelUtils.export(response,"操作日志信息表",this.list(), SysLog.class);
    }

    @Override
    public void save(String username, String browser, String ip, ProceedingJoinPoint joinPoint, SysLog log) {
        if (log == null) {
            throw new IllegalArgumentException("Log 不能为 null!");
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log aopLog = method.getAnnotation(Log.class);

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";

        // 描述
        log.setDescription(aopLog.value());

        log.setRequestIp(ip);
        log.setAddress(StringUtils.getCityInfoByIp(log.getRequestIp()));
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(getParameter(method, joinPoint.getArgs()));
        // 记录登录用户，隐藏密码信息
        if(log.getDescription().equals("登录")){
            JSONObject obj = JSONUtil.parseObj(log.getParams());
            log.setUsername(obj.get("username").toString());
            log.setParams(JSONUtil.toJsonStr(Dict.create().set("username", log.getUsername()).set("password","******")));
        }
        // 修改密码，隐藏密码信息
        if (log.getDescription().equals("修改密码")){
            log.setUsername(SecurityUtils.getCurrentUsername());
            log.setParams(JSONUtil.toJsonStr(Dict.create().set("username", log.getUsername()).set("newPassword","******")));
        }
        log.setBrowser(browser);
        log.setCreateTime(new Timestamp(new Date().getTime()));
        this.save(log);
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private String getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>(4);
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.isEmpty()) {
            for (int i = 0; i < parameters.length; i++) {
                argList.add(args[i]);
            }
            if(argList.isEmpty()){
                return "";
            }
        }
        return argList.size() == 1 ? JSONUtil.toJsonStr(argList.get(0)) : JSONUtil.toJsonStr(argList);
    }

}