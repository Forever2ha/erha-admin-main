package fun.yizhierha.common.exception.handler;

import com.alibaba.fastjson.JSONException;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.exception.InternalServerException;
import fun.yizhierha.common.utils.R;
import fun.yizhierha.common.utils.ValidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;

import java.util.HashMap;
import java.util.Map;

import static fun.yizhierha.common.exception.BizCodeEnum.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InternalServerException.class)
    public R handleInternalServerException(InternalServerException e){
        return R.error(Internal_Error_Business.getCode(), Internal_Error_Business.getMsg()+":"+e.getMessage());
    }

    //数据校验格式错误异常处理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e){

        log.debug("数据校验出现问题{},异常类型:{}",e.getMessage(),e.getClass());

        Map<String,String> errorInfo = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach((errors)-> errorInfo.put(errors.getField(),errors.getDefaultMessage()));
        return R.error(Client_Error_Param_InValid.getCode()
                , Client_Error_Param_InValid.getMsg()).setData(errorInfo);
    }

    //无权访问异常
    @ExceptionHandler(AccessDeniedException.class)
    public R handleAccessDeniedException(AccessDeniedException e){
        return R.error(Client_Error_No_Permission.getCode(), Client_Error_No_Permission.getMsg());
    }

    //坏的请求异常
    @ExceptionHandler(BadRequestException.class)
    public R handleBadRequestException(BadRequestException e){
        R<Object> error = R.error(e.getStatus() == null ? Client_Error_BadRequest.getCode() : e.getStatus(),
                e.getMessage() == null ? Client_Error_BadRequest.getMsg() : e.getMessage());
        if (e.getData() != null){
            error.setData(e.getData());
        }
        return error;
    }

    //认证异常
    @ExceptionHandler(BadCredentialsException.class)
    public R handlerBadCredentialsException(BadCredentialsException e){
        return R.error(Client_Error_Login_Fail.getCode(), Client_Error_Login_Fail.getMsg()).setData(e.getMessage());
    }

    //redis连接异常
    @ExceptionHandler(RedisConnectionFailureException.class)
    public R handerRedisConnectionFailureException(RedisConnectionFailureException e){
        return R.error(Internal_Error_Redis_ConnFail.getCode(), Internal_Error_Redis_ConnFail.getMsg());
    }

    //
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R handleJSONException(Exception e){
        // Json解析异常
        if (e.getCause() instanceof JSONException){
            e.printStackTrace();
            JSONException err = (JSONException) e.getCause();
            return R.error(Common_Error_JSONException.getCode(), Common_Error_JSONException.getMsg()+": "+ err.getCause().getMessage());
        }
        return handleAllException(e);
    }

    // 处理用户未激活异常
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public R handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e){
        if (e.getCause() instanceof BadRequestException){
            return handleBadRequestException((BadRequestException) e.getCause());
        }else {
            return handleAllException(e);
        }
    }

    //全局异常处理
    @ExceptionHandler(Throwable.class)
    public R handleAllException(Throwable t){
        log.error("出现未知异常:{},异常类型:{}",t.getMessage(),t.getClass());
        t.printStackTrace();
        return R.error(Internal_Error_Unknown.getCode()
                ,Internal_Error_Unknown.getMsg()).setData(ValidUtils.getStackTrace(t));
    }
}
