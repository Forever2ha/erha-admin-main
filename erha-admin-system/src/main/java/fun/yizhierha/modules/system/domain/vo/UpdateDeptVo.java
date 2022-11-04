package fun.yizhierha.modules.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("修改角色Vo")
public class UpdateDeptVo extends UpdateVo{

    @ApiModelProperty("部门父id")
    private Long pid;

    @ApiModelProperty("状态")
    private Boolean enabled;

    @ApiModelProperty("是否是顶级部门")
    private Boolean isTop;

    @ApiModelProperty("名称")
    @Size(min = 2,max = 20,message = "部门名称在2-20个字符之间")
    private String name;

    @ApiModelProperty("排序")
    @Min(value = 0,message = "排序最小值为0")
    private Long deptSort;

}
