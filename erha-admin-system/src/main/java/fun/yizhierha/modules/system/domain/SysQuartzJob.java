package fun.yizhierha.modules.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 定时任务
 */
@ApiModel(value = "定时任务")
@Data
@TableName(value = "sys_quartz_job")
public class SysQuartzJob implements Serializable {

    public static final String JOB_KEY = "JOB_KEY";


    /**
     * ID
     */
    @TableId(value = "job_id", type = IdType.INPUT)
    @ApiModelProperty(value = "ID")
    private Long jobId;

    /**
     * Spring Bean名称
     */
    @TableField(value = "bean_name")
    @ApiModelProperty(value = "Spring Bean名称")
    private String beanName;

    /**
     * cron 表达式
     */
    @TableField(value = "cron_expression")
    @ApiModelProperty(value = "cron 表达式")
    private String cronExpression;

    /**
     * 状态：1暂停、0启用
     */
    @TableField(value = "is_pause")
    @ApiModelProperty(value = "状态：1暂停、0启用")
    private Boolean isPause;

    /**
     * 任务名称
     */
    @TableField(value = "job_name")
    @ApiModelProperty(value = "任务名称")
    private String jobName;

    /**
     * 方法名称
     */
    @TableField(value = "method_name")
    @ApiModelProperty(value = "方法名称")
    private String methodName;

    /**
     * 参数
     */
    @TableField(value = "params")
    @ApiModelProperty(value = "参数")
    private String params;

    /**
     * 备注
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "备注")
    private String description;

    /**
     * 负责人
     */
    @TableField(value = "person_in_charge")
    @ApiModelProperty(value = "负责人")
    private String personInCharge;

    /**
     * 报警邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value = "报警邮箱")
    private String email;

    /**
     * 子任务ID
     */
    @TableField(value = "sub_task")
    @ApiModelProperty(value = "子任务ID")
    private String subTask;

    /**
     * 任务失败后是否暂停
     */
    @TableField(value = "pause_after_failure")
    @ApiModelProperty(value = "任务失败后是否暂停")
    private Boolean pauseAfterFailure;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value = "创建者")
    private String createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    /**
     * 创建日期
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_JOB_ID = "job_id";

    public static final String COL_BEAN_NAME = "bean_name";

    public static final String COL_CRON_EXPRESSION = "cron_expression";

    public static final String COL_IS_PAUSE = "is_pause";

    public static final String COL_JOB_NAME = "job_name";

    public static final String COL_METHOD_NAME = "method_name";

    public static final String COL_PARAMS = "params";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_PERSON_IN_CHARGE = "person_in_charge";

    public static final String COL_EMAIL = "email";

    public static final String COL_SUB_TASK = "sub_task";

    public static final String COL_PAUSE_AFTER_FAILURE = "pause_after_failure";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}