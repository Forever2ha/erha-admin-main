package fun.yizhierha.operation.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("查询项目管理vo")
public class RetrieveOraProjectVo{

    @ApiModelProperty("项目名称")
    private String pname;

    @ApiModelProperty("创建者")
    private String createBy;

    @ApiModelProperty("状态")
    private Boolean enabled;

}