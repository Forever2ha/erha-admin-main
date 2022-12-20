package fun.yizhierha.service.dto;

import fun.yizhierha.common.base.BaseDTO;
import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class SummaryProjectDto  extends BaseDTO {
    @ApiModelProperty("项目id")
    @ExcelExport(value = "项目id")
    private Long projectId;
    @ApiModelProperty("项目名称")
    @ExcelExport(value = "项目名称")
    private String pname;
    @ApiModelProperty("项目计划开始日期")
    @ExcelExport(value = "项目计划开始日期")
    private Date planStartDate;
    @ApiModelProperty("项目计划结束日期")
    @ExcelExport(value = "项目计划结束日期")
    private Date planFinishDate;
    @ApiModelProperty("创建者")
    @ExcelExport(value = "创建者")
    private Date createBy;
    @ApiModelProperty("更新者")
    @ExcelExport(value = "更新者")
    private String updateBy;
    @ApiModelProperty("创建时间")
    @ExcelExport(value = "创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    @ExcelExport(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty("实际开始日期")
    @ExcelExport(value = "实际开始日期")
    private Date actuStartDate;
    @ApiModelProperty("实际结束日期")
    @ExcelExport(value = "实际结束日期")
    private Date actuFinishDate;

    private Boolean enabled;//是否启用
}
