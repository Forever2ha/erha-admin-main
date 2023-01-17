package fun.yizhierha.operation.domain.vo;

import fun.yizhierha.common.base.UpdateVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateOraDatabaseVo extends UpdateVo{

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "jdbc连接")
    private String jdbcUrl;

    @ApiModelProperty(value = "账号")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "类型")
    private String typeDatabese;
    @ApiModelProperty("更新者")
    private String updateBy;
}