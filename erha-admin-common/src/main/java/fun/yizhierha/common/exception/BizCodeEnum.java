package fun.yizhierha.common.exception;



/*
* 错误码列表:
*   10:通用
*       001:JSON解析异常,可能是参数传递有误
*   20:成功
*       000:请求成功
*
*   40:客户端请求错误
*       000:坏的请求
*       001:请求参数有误
*       002:未授权
*       003:没有权限
*       004:登录失败
*       005:Token过期或者不存在
*       006:Crud错误，主要是让前端interceptor放开让crud.ts进行处理
*   50:服务器内部错误
*      000:服务器未知异常
*      001:Redis连接失败
* */
public enum BizCodeEnum {
    // 50
    Internal_Error_Unknown(50000,"服务器未知异常"),
    Internal_Error_Redis_ConnFail(50001,"redis连接失败"),
    Internal_Error_Business(50002,"业务逻辑出现问题"),
    // 20
    Ok_(20000,"请求成功"),
    // 40
    Client_Error_BadRequest(40000,"请求不合法"),
    Client_Error_Param_InValid(40001,"请求参数有误"),
    Client_Error_Unauthorized(40002,"未授权"),
    Client_Error_No_Permission(40003,"权限不足"),
    Client_Error_Login_Fail(40004,"登录失败"),
    Client_Error_Token_ExpiredOrNotExist(40005,"token过期或者不存在"),
    Client_Error_CRUD(40006,"CRUD请求有误"),
    // 10
    Common_Error_JSONException(10001,"JSON解析异常,可能是参数传递有误"),


    ;


    private Integer code;
    private String msg;


    BizCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
