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
    * 部署历史管理
    */
@ApiModel(value="部署历史管理")
@Data
@TableName(value = "mnt_deploy_history")
public class MntDeployHistory implements Serializable {
    /**
     * ID
     */
    @TableId(value = "history_id", type = IdType.INPUT)
    @ApiModelProperty(value="ID")
    private String historyId;

    /**
     * 应用名称
     */
    @TableField(value = "app_name")
    @ApiModelProperty(value="应用名称")
    private String appName;

    /**
     * 部署日期
     */
    @TableField(value = "deploy_date")
    @ApiModelProperty(value="部署日期")
    private Date deployDate;

    /**
     * 部署用户
     */
    @TableField(value = "deploy_user")
    @ApiModelProperty(value="部署用户")
    private String deployUser;

    /**
     * 服务器IP
     */
    @TableField(value = "ip")
    @ApiModelProperty(value="服务器IP")
    private String ip;

    /**
     * 部署编号
     */
    @TableField(value = "deploy_id")
    @ApiModelProperty(value="部署编号")
    private Long deployId;

    private static final long serialVersionUID = 1L;

    public static final String COL_HISTORY_ID = "history_id";

    public static final String COL_APP_NAME = "app_name";

    public static final String COL_DEPLOY_DATE = "deploy_date";

    public static final String COL_DEPLOY_USER = "deploy_user";

    public static final String COL_IP = "ip";

    public static final String COL_DEPLOY_ID = "deploy_id";
}