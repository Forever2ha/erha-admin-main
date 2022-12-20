package fun.yizhierha.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目
 *
 * @author wxf
 * @date 2022/12/20
 */
@ApiModel(value = "项目管理")
@Data
@TableName(value = "ora_project")
public class OperProject implements Serializable {
    private static final long serialVersionUID = 682400920598062454L;
    @TableId(value = "project_id", type = IdType.INPUT)
    @ApiModelProperty(value = "主键")
    private Long projectId;
    @TableField(value = "pname")
    @ApiModelProperty(value = "项目名称")
    private String pname;
    @TableField(value = "plan_start_date")
    @ApiModelProperty(value = "项目计划开始日期")
    private Date planStartDate;
    @TableField(value = "plan_finish_date")
    @ApiModelProperty(value = "项目计划结束日期")
    private Date planFinishDate;

    @TableField(value = "create_by")
    @ApiModelProperty(value = "创建者")
    private String createBy;

    @TableField(value = "update_by")
    @ApiModelProperty(value = "更新者")
    private String updateBy;
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @TableField(value = "actu_start_date")
    @ApiModelProperty(value = "实际开始日期")
    private Date actuStartDate;
    @TableField(value = "actu_finish_date")
    @ApiModelProperty(value = "实际结束日期")
    private Date actuFinishDate;
    @TableField("enabled")
    @ApiModelProperty("状态：1启用、0禁用")
    private Boolean enabled;

    private final static String PROJECT_ID = "project_id";
    private final static String PNAME = "pname";
    private final static String PLAN_START_DATE = "plan_start_date";
    private final static String PLAN_FINISH_DATE = "plan_finish_date";
    private final static String CREATE_BY = "create_by";
    private final static String UPDATE_BY = "update_by";
    private final static String CREATE_TIME = "create_time";
    private final static String UPDATE_TIME = "update_time";
    private final static String ACTU_START_DATE = "actu_start_date";
    private final static String ACTU_FINISH_DATE = "actu_finish_date";
    private final static String ENABLED = "enabled";
}
