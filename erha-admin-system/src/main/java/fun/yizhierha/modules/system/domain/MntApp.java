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
    * 应用管理
    */
@ApiModel(value="应用管理")
@Data
@TableName(value = "mnt_app")
public class MntApp implements Serializable {
    /**
     * ID
     */
    @TableId(value = "app_id", type = IdType.INPUT)
    @ApiModelProperty(value="ID")
    private Long appId;

    /**
     * 应用名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="应用名称")
    private String name;

    /**
     * 上传目录
     */
    @TableField(value = "upload_path")
    @ApiModelProperty(value="上传目录")
    private String uploadPath;

    /**
     * 部署路径
     */
    @TableField(value = "deploy_path")
    @ApiModelProperty(value="部署路径")
    private String deployPath;

    /**
     * 备份路径
     */
    @TableField(value = "backup_path")
    @ApiModelProperty(value="备份路径")
    private String backupPath;

    /**
     * 应用端口
     */
    @TableField(value = "port")
    @ApiModelProperty(value="应用端口")
    private Integer port;

    /**
     * 启动脚本
     */
    @TableField(value = "start_script")
    @ApiModelProperty(value="启动脚本")
    private String startScript;

    /**
     * 部署脚本
     */
    @TableField(value = "deploy_script")
    @ApiModelProperty(value="部署脚本")
    private String deployScript;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value="创建者")
    private String createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    @ApiModelProperty(value="更新者")
    private String updateBy;

    /**
     * 创建日期
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建日期")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_APP_ID = "app_id";

    public static final String COL_NAME = "name";

    public static final String COL_UPLOAD_PATH = "upload_path";

    public static final String COL_DEPLOY_PATH = "deploy_path";

    public static final String COL_BACKUP_PATH = "backup_path";

    public static final String COL_PORT = "port";

    public static final String COL_START_SCRIPT = "start_script";

    public static final String COL_DEPLOY_SCRIPT = "deploy_script";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}