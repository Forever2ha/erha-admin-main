package fun.yizhierha.operation.domain.vo;

import fun.yizhierha.common.base.UpdateVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;


@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateOraServerVo extends UpdateVo{

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "IP地址")
    private String ip;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "端口")
    private Integer port;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

}