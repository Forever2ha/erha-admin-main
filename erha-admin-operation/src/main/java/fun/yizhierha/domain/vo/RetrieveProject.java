package fun.yizhierha.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("查询项目列表Vo")
public class RetrieveProject {
    @ApiModelProperty("项目名称")
    private String pname;
    @ApiModelProperty("状态：1启用、0禁用")
    private Boolean enabled;

}
