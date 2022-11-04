package fun.yizhierha.common.utils;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@ApiModel
@Data
public class R<T> {

    @ApiModelProperty("返回码")
    private Integer code = 20000;

    @ApiModelProperty("信息")
    private String msg = "success";

    @ApiModelProperty("数据")
    private T data;

    public R() {

    }

    public R<T> setData(T data){
        this.data = data;
        return this;
    }

    public T getData(){
        return data;
    }

    public static <T> R<T> error() {
        return error(HttpStatus.HTTP_INTERNAL_ERROR, "未知异常，请联系管理员");
    }

    public static <T> R<T> error(String msg) {
        return error(HttpStatus.HTTP_INTERNAL_ERROR, msg);
    }

    public static <T> R<T> error(int code, String msg) {
        R<T> r = new R<T>();
        r.code = code;
        r.msg = msg;
        return r;
    }


    public static <T> R<T> ok(String msg) {
        R<T> r = new R<T>();
        r.setMsg(msg);
        return r;
    }

    public static <T> R<T> ok() {
        return new R<>();
    }


}
