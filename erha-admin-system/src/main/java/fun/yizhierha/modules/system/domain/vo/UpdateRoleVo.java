package fun.yizhierha.modules.system.domain.vo;

import fun.yizhierha.common.base.UpdateVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("修改角色Vo")
public class UpdateRoleVo extends UpdateVo {

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("等级")
    @Min(value = 1L,message = "等级必须大于1")
    private Long level;

    @ApiModelProperty("数据权限范围")
    private String dataScope;

    @ApiModelProperty("自定义数据权限的部门")
    private List<Long> deptIds;
}
