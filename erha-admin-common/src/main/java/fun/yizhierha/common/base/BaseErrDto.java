package fun.yizhierha.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("基础错误结果dto")
public class BaseErrDto {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("错误字段")
    private String errorField;

    @ApiModelProperty("错误值")
    private Object errorVal;

    @ApiModelProperty("错误原因")
    private String errorMsg;
}
