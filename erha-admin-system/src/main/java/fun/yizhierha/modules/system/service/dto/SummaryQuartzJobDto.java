package fun.yizhierha.modules.system.service.dto;


import fun.yizhierha.common.base.BaseDTO;
import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SummaryQuartzJobDto extends BaseDTO {


    @ExcelExport("ID")
    @ApiModelProperty(value = "ID")
    private Long id;

    @ExcelExport("Bean名称")
    @ApiModelProperty(value = "Spring Bean名称")
    private String beanName;

    @ExcelExport("表达式")
    @ApiModelProperty(value = "cron 表达式")
    private String cronExpression;

    @ExcelExport("启用")
    @ApiModelProperty(value = "状态：1暂停、0启用")
    private Boolean isPause;

    @ExcelExport("任务名称")
    @ApiModelProperty(value = "任务名称")
    private String jobName;

    @ExcelExport("方法名称")
    @ApiModelProperty(value = "方法名称")
    private String methodName;

    @ExcelExport("参数")
    @ApiModelProperty(value = "参数")
    private String params;

    @ExcelExport("备注")
    @ApiModelProperty(value = "备注")
    private String description;

    @ExcelExport("负责人")
    @ApiModelProperty(value = "负责人")
    private String personInCharge;

    @ExcelExport("报警邮箱")
    @ApiModelProperty(value = "报警邮箱")
    private String email;

    @ExcelExport("子任务ID")
    @ApiModelProperty(value = "子任务ID")
    private String subTask;

    @ExcelExport("任务失败后是否暂停")
    @ApiModelProperty(value = "任务失败后是否暂停")
    private Boolean pauseAfterFailure;

}
