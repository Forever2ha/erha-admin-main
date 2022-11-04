package fun.yizhierha.modules.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
public class UpdateRoleMenuVo {

    @NotNull(message = "角色id不能为空")
    @ApiModelProperty("roleId")
    private Long roleId;

    @NotNull(message = "菜单id不能为空")
    @ApiModelProperty("menuId")
    private List<Long> menuIdList;

}
