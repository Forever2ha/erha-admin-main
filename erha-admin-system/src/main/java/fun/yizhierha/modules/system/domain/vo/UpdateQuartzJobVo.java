package fun.yizhierha.modules.system.domain.vo;

import fun.yizhierha.common.annotation.valid.CronExpression;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateQuartzJobVo extends UpdateVo{

    @ApiModelProperty(value = "Spring Bean名称")
    private String beanName;

    @CronExpression
    @ApiModelProperty(value = "cron 表达式")
    private String cronExpression;

    @ApiModelProperty(value = "状态：1暂停、0启用")
    private Boolean isPause;

    @ApiModelProperty(value = "任务名称")
    private String jobName;

    @ApiModelProperty(value = "方法名称")
    private String methodName;

    @ApiModelProperty(value = "参数")
    private String params;

    @ApiModelProperty(value = "备注")
    private String description;

    @ApiModelProperty(value = "负责人")
    private String personInCharge;

    @ApiModelProperty(value = "报警邮箱")
    private String email;

    @ApiModelProperty(value = "子任务ID")
    private String subTask;

    @ApiModelProperty(value = "任务失败后是否暂停")
    private Boolean pauseAfterFailure;

    @ApiModelProperty(value = "是否立即执行一次任务")
    private Boolean runJobNow;
}
