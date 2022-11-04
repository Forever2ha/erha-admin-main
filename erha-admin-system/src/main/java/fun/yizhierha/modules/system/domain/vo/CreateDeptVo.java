package fun.yizhierha.modules.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel("添加部门vo")
public class CreateDeptVo {

    @ApiModelProperty("部门父id")
    private Long pid;

    @ApiModelProperty("状态")
    @NotNull(message = "部门状态不能为空")
    private Boolean enabled;

    @ApiModelProperty("是否是顶级部门")
    @NotNull(message = "部门是否是顶级部门不能为空")
    private Boolean isTop;

    @ApiModelProperty("名称")
    @NotBlank(message = "部门名称不能为空")
    @Size(min = 2,max = 20,message = "部门名称在2-20个字符之间")
    private String name;

    @ApiModelProperty("排序")
    @NotNull(message = "排序不能为空")
    @Min(value = 0,message = "排序最小值为0")
    private Long deptSort;

}
