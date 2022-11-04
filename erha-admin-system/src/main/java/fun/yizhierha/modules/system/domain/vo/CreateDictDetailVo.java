package fun.yizhierha.modules.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("添加字典详情vo")
public class CreateDictDetailVo {

    @ApiModelProperty("字典id")
    @NotNull(message = "字典id不能为空")
    private Long dictId;

    @ApiModelProperty("标签")
    @NotBlank(message = "标签不能为空")
    private String label;

    @ApiModelProperty("值")
    @NotBlank(message = "值不能为空")
    private String value;

    @ApiModelProperty("排序")
    @NotNull(message = "排序不能为空")
    @Min(value = 0,message = "排序最小值为0")
    private Long dictSort;
}
