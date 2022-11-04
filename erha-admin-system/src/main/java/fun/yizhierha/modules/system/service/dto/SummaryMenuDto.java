package fun.yizhierha.modules.system.service.dto;

import fun.yizhierha.common.base.BaseDTO;
import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class SummaryMenuDto extends BaseDTO {

    @ApiModelProperty(value = "ID")
    @ExcelExport("ID")
    private Long id;

    @ApiModelProperty(value = "上级菜单ID")
    @ExcelExport("上级菜单ID")
    private Long pid;

    @ApiModelProperty(value = "子菜单数目")
    @ExcelExport("子菜单数目")
    private Integer subCount;

    @ApiModelProperty(value = "菜单类型")
    @ExcelExport("菜单类型")
    private Long type;

    @ApiModelProperty(value = "菜单标题")
    @ExcelExport("菜单标题")
    private String title;

    @ApiModelProperty(value = "组件名称")
    @ExcelExport("组件名称")
    private String name;

    @ApiModelProperty(value = "语言包键名")
    @ExcelExport("语言包键名")
    private String locale;

    @ApiModelProperty(value = "排序")
    @ExcelExport("排序")
    private Integer order;

    @ApiModelProperty(value = "图标")
    @ExcelExport("图标")
    private String icon;

    @ApiModelProperty(value = "链接地址")
    @ExcelExport("链接地址")
    private String path;

    @ApiModelProperty(value = "是否外链")
    @ExcelExport("是否外链")
    private Boolean iFrame;

    @ApiModelProperty(value = "缓存")
    @ExcelExport("缓存")
    private Boolean cache;

    @ApiModelProperty(value = "隐藏")
    @ExcelExport("隐藏")
    private Boolean hidden;

    @ApiModelProperty(value = "权限")
    @ExcelExport("权限")
    private String permission;

    @ApiModelProperty(value = "子菜单")
    @ExcelExport("子菜单")
    private List<SummaryMenuDto> children;


}
