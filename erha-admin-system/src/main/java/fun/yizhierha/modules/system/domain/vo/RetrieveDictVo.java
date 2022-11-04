package fun.yizhierha.modules.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("查询字典vo")
public class RetrieveDictVo {
    @ApiModelProperty("字典名")
    private String name;

}
