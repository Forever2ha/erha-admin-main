package fun.yizhierha.modules.system.service.dto;

import fun.yizhierha.common.base.BaseDTO;
import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DictDto extends BaseDTO {

    @ApiModelProperty(value = "ID")
    @ExcelExport("ID")
    private Long id;

    @ExcelExport("字典名称")
    @ApiModelProperty(value = "字典名称")
    private String name;

    @ExcelExport("字典描述")
    @ApiModelProperty(value = "字典描述")
    private String description;

}
