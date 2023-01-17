package fun.yizhierha.operation.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("查询部署管理vo")
public class RetrieveOraDeployHistoryVo{
    @ApiModelProperty("部署id")
    private Long deployId;

    @ApiModelProperty("应用名称")
    private String appName;
}