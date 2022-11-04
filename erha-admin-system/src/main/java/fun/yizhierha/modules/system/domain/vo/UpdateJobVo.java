package fun.yizhierha.modules.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateJobVo extends UpdateVo{

    @ApiModelProperty("名称")
    @Size(min = 2,max = 20,message = "岗位名称在2-20个字符之间")
    private String name;

    @ApiModelProperty("状态")
    private Boolean enabled;

    @ApiModelProperty("排序")
    @Min(value = 0,message = "排序最小值为0")
    private Long jobSort;

}
