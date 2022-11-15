package fun.yizhierha.modules.system.domain.vo;

import fun.yizhierha.common.base.UpdateVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateDictDetailVo extends UpdateVo {


    @ApiModelProperty("标签")
    private String label;

    @ApiModelProperty("值")
    private String value;

    @ApiModelProperty("排序")
    @Min(value = 0,message = "排序最小值为0")
    private Long dictSort;
}
