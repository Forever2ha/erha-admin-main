package fun.yizhierha.operation.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("查询服务器vo")
public class RetrieveOraServerVo{

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("项目ID")
    private Long projectId;

}