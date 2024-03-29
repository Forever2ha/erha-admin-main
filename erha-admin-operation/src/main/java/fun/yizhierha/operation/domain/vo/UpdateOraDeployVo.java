package fun.yizhierha.operation.domain.vo;

import fun.yizhierha.common.base.UpdateVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateOraDeployVo extends UpdateVo {


    @ApiModelProperty(value = "应用编号")
    private Long appId;


    @ApiModelProperty(value = "服务器")
    private List<Long> serverId;

}