package fun.yizhierha.operation.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("查询部署管理vo")
public class RetrieveOraDeployVo{

    @ApiModelProperty("应用编号")
    private Long appId;

    @ApiModelProperty("服务器")
    private Long serverId;

}