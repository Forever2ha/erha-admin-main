package fun.yizhierha.modules.system.service.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import fun.yizhierha.common.base.BaseDTO;
import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel
public class SummaryRoleDto extends BaseDTO {

    @ApiModelProperty(value = "ID")
    @ExcelExport(value = "ID")
    private Long id;

    @ApiModelProperty(value = "名称")
    @ExcelExport(value = "名称")
    private String name;


    @ApiModelProperty(value = "角色级别")
    @ExcelExport(value = "角色级别")
    private Integer level;


    @ApiModelProperty(value = "描述")
    @ExcelExport(value = "描述")
    private String description;


    @ApiModelProperty(value = "数据范围")
    @ExcelExport(value = "数据范围")
    private String dataScope;

    @ApiModelProperty(value = "数据权限")
    private List<Long> dataPerm;


    @ExcelExport(value = "数据权限")
    private String dataPermStr;
}
