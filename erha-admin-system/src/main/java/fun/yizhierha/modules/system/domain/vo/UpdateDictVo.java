package fun.yizhierha.modules.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateDictVo extends UpdateVo{

    @ApiModelProperty("字典名称")
    private String name;

    @ApiModelProperty("字典描述")
    private String description;
}
