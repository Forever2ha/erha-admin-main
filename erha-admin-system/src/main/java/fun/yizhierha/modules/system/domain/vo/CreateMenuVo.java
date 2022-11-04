package fun.yizhierha.modules.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("创建菜单vo")
public class CreateMenuVo {

    @ApiModelProperty("上级菜单")
    private Long pid;

    @ApiModelProperty("title")
    @NotBlank(message = "菜单标题名称不能为空")
    private String title;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("路径")
    private String path;


    @ApiModelProperty("权限")
    private String permission;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("语言包键")
    private String locale;

    @ApiModelProperty("菜单类型")
    @NotNull(message = "菜单类型不能为空")
    @Min(value = 0,message = "菜单类型取值:[0:目录,1:菜单,2:按钮]")
    @Max(value = 2,message = "菜单类型取值:[0:目录,1:菜单,2:按钮]")
    private Integer type;

    @ApiModelProperty("排序")
    @NotNull(message = "排序不能为空")
    @Min(value = 0,message = "排序最小值为0")
    private Long order;

    @ApiModelProperty("缓存")
    @NotNull(message = "是否缓存不能为空")
    private Boolean cache;

    @ApiModelProperty("隐藏")
    @NotNull(message = "是否隐藏不能为空")
    private Boolean hidden;

    @ApiModelProperty("外链")
    @NotNull(message = "是否外链不能为空")
    private Boolean iFrame;

}
