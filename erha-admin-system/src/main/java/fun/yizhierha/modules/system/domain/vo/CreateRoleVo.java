package fun.yizhierha.modules.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("创建用户Vo")
public class CreateRoleVo {
    @ApiModelProperty("名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("等级")
    @Min(value = 1L,message = "等级必须大于1")
    @NotNull(message = "角色等级不能为空")
    private Long level;

    @ApiModelProperty("数据权限范围")
    @NotBlank(message = "角色数据权限范围不能为空")
    private String dataScope;

    @ApiModelProperty("自定义数据权限的部门")
    private List<Long> deptIds;

}
