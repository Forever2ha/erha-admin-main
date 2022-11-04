package fun.yizhierha.modules.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 定时任务日志
 */
@ApiModel(value = "定时任务日志")
@Data
@TableName(value = "sys_quartz_log")
public class SysQuartzLog implements Serializable {
    /**
     * ID
     */
    @TableId(value = "log_id", type = IdType.INPUT)
    @ApiModelProperty(value = "ID")
    @ExcelExport("id")
    private Long id;

    @TableField(value = "bean_name")
    @ApiModelProperty(value = "")
    @ExcelExport("beanName")
    private String beanName;

    @TableField(value = "create_time")
    @ApiModelProperty(value = "")
    @ExcelExport("createTime")
    private Date createTime;

    @TableField(value = "cron_expression")
    @ApiModelProperty(value = "")
    @ExcelExport("cronExpression")
    private String cronExpression;

    @TableField(value = "exception_detail")
    @ApiModelProperty(value = "")
    @ExcelExport("exceptionDetail")
    private String exceptionDetail;

    @TableField(value = "is_success")
    @ApiModelProperty(value = "")
    @ExcelExport("Boolean")
    private Boolean isSuccess;

    @TableField(value = "job_name")
    @ApiModelProperty(value = "")
    @ExcelExport("jobName")
    private String jobName;

    @TableField(value = "method_name")
    @ApiModelProperty(value = "")
    @ExcelExport("methodName")
    private String methodName;

    @TableField(value = "params")
    @ApiModelProperty(value = "")
    @ExcelExport("params")
    private String params;

    @TableField(value = "`time`")
    @ApiModelProperty(value = "")
    @ExcelExport("time")
    private Long time;
    @TableField(value = "`exec_time`")
    @ApiModelProperty(value = "")
    @ExcelExport("exec_time")
    private Date execTime;


    private static final long serialVersionUID = 1L;

    public static final String COL_EXEC_TIME = "exec_time";
    public static final String COL_LOG_ID = "log_id";

    public static final String COL_BEAN_NAME = "bean_name";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_CRON_EXPRESSION = "cron_expression";

    public static final String COL_EXCEPTION_DETAIL = "exception_detail";

    public static final String COL_IS_SUCCESS = "is_success";

    public static final String COL_JOB_NAME = "job_name";

    public static final String COL_METHOD_NAME = "method_name";

    public static final String COL_PARAMS = "params";

    public static final String COL_TIME = "time";
}