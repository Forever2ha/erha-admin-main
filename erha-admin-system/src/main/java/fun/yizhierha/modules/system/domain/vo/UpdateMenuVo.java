package fun.yizhierha.modules.system.domain.vo;

import fun.yizhierha.common.base.UpdateVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("修改菜单vo")
public class UpdateMenuVo extends UpdateVo {

    @ApiModelProperty("菜单标题")
    private String title;


    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("语言包键值")
    private String locale;

    @ApiModelProperty("路由名称")
    private String name;

    @ApiModelProperty("路由")
    private String path;

    @ApiModelProperty("排序")
    @Min(value = 0,message = "菜单排序最小为0")
    private Integer order;

    @ApiModelProperty("权限")
    private String permission;

    @ApiModelProperty("是否是外链")
    private Boolean iFrame;

    @ApiModelProperty("是否隐藏")
    private Boolean hidden;

    @ApiModelProperty("是否缓存")
    private Boolean cache;

}
