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
    * 部署管理
    */
@ApiModel(value="部署管理")
@Data
@TableName(value = "mnt_deploy")
public class MntDeploy implements Serializable {
    /**
     * ID
     */
    @TableId(value = "deploy_id", type = IdType.INPUT)
    @ApiModelProperty(value="ID")
    private Long deployId;

    /**
     * 应用编号
     */
    @TableField(value = "app_id")
    @ApiModelProperty(value="应用编号")
    private Long appId;

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

    @TableField(value = "create_time")
    @ApiModelProperty(value="")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_DEPLOY_ID = "deploy_id";

    public static final String COL_APP_ID = "app_id";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}