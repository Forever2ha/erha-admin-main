package fun.yizhierha.modules.system.service.dto;


import com.baomidou.mybatisplus.annotation.TableId;
import fun.yizhierha.common.base.BaseDTO;
import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class SummaryJobDto extends BaseDTO {

    @ApiModelProperty(value = "ID")
    @ExcelExport("ID")
    private Long id;

    @ExcelExport("岗位名称")
    @ApiModelProperty(value = "岗位名称")
    private String name;


    @ExcelExport("岗位状态")
    @ApiModelProperty(value = "岗位状态")
    private Boolean enabled;


    @ExcelExport("排序")
    @ApiModelProperty(value = "排序")
    private Integer jobSort;
}
