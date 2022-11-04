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
 * 系统日志
 */
@ApiModel(value = "系统日志")
@Data
@TableName(value = "sys_log")
public class SysLog implements Serializable {
    /**
     * ID
     */
    @TableId(value = "log_id", type = IdType.INPUT)
    @ApiModelProperty(value = "ID")
    private Long logId;

    @TableField(value = "description")
    @ApiModelProperty(value = "")
    private String description;

    @TableField(value = "log_type")
    @ApiModelProperty(value = "")
    private String logType;

    @TableField(value = "`method`")
    @ApiModelProperty(value = "")
    private String method;

    @TableField(value = "params")
    @ApiModelProperty(value = "")
    private String params;

    @TableField(value = "request_ip")
    @ApiModelProperty(value = "")
    private String requestIp;

    @TableField(value = "`time`")
    @ApiModelProperty(value = "")
    private Long time;

    @TableField(value = "username")
    @ApiModelProperty(value = "")
    private String username;

    @TableField(value = "address")
    @ApiModelProperty(value = "")
    private String address;

    @TableField(value = "browser")
    @ApiModelProperty(value = "")
    private String browser;

    @TableField(value = "exception_detail")
    @ApiModelProperty(value = "")
    private String exceptionDetail;

    @TableField(value = "create_time")
    @ApiModelProperty(value = "")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_LOG_ID = "log_id";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_LOG_TYPE = "log_type";

    public static final String COL_METHOD = "method";

    public static final String COL_PARAMS = "params";

    public static final String COL_REQUEST_IP = "request_ip";

    public static final String COL_TIME = "time";

    public static final String COL_USERNAME = "username";

    public static final String COL_ADDRESS = "address";

    public static final String COL_BROWSER = "browser";

    public static final String COL_EXCEPTION_DETAIL = "exception_detail";

    public static final String COL_CREATE_TIME = "create_time";
}