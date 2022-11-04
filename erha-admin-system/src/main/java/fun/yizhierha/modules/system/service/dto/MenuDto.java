package fun.yizhierha.modules.system.service.dto;

import fun.yizhierha.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("菜单Dto")
public class MenuDto extends BaseDTO {

    @ApiModelProperty(value = "菜单id")
    private Long menuId;

    @ApiModelProperty(value = "菜单父id")
    private Long pid;

    @ApiModelProperty(value = "子菜单个数")
    private Long subCount;

    @ApiModelProperty(value = "菜单类型[0:目录,1:菜单,2:目录]")
    private Integer type;

    @ApiModelProperty(value = "路由")
    private String path;

    @ApiModelProperty(value = "路由名称")
    private String name;

    @ApiModelProperty("菜单元信息")
    private Meta meta;

    @ApiModelProperty(value = "子路由")
    private List<MenuDto> children;


    @Data
    @ApiModel("菜单元信息")
    public static class Meta{

        @ApiModelProperty(value = "语言包键值")
        private String locale;

        @ApiModelProperty(value = "是否需要登录鉴权")
        private Boolean requiresAuth = true;

        @ApiModelProperty(value = "图标")
        private String icon;

        @ApiModelProperty(value = "权限")
        private List<String> roles;

        @ApiModelProperty(value = "排序")
        private Long order;

        @ApiModelProperty(value = "是否隐藏菜单")
        private Boolean hideInMenu;

        @ApiModelProperty(value = "缓存")
        private Boolean ignoreCache;

    }
}
