package fun.yizhierha.modules.system.service.dto;

import fun.yizhierha.common.base.BaseDTO;
import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class SummaryDictDto extends BaseDTO {

    @ApiModelProperty(value = "ID")
    @ExcelExport("ID")
    private Long dictId;

    @ApiModelProperty(value = "名称")
    @ExcelExport("名称")
    private String name;


    @ApiModelProperty(value = "描述")
    @ExcelExport("描述")
    private String description;

    @ApiModelProperty(value = "字典详情id")
    @ExcelExport("字典详情id")
    private Long detailId;

    @ApiModelProperty(value = "标签")
    @ExcelExport("标签")
    private String label;

    @ApiModelProperty(value = "值")
    @ExcelExport("值")
    private String value;

    @ApiModelProperty(value = "排序")
    @ExcelExport("排序")
    private Long dictSort;


}
