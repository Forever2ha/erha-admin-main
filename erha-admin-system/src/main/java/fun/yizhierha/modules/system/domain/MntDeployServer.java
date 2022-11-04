package fun.yizhierha.modules.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
    * 应用与服务器关联
    */
@ApiModel(value="应用与服务器关联")
@Data
@TableName(value = "mnt_deploy_server")
public class MntDeployServer implements Serializable {
    /**
     * 部署ID
     */
    @TableId(value = "deploy_id", type = IdType.INPUT)
    @ApiModelProperty(value="部署ID")
    private Long deployId;

    /**
     * 服务ID
     */
    @TableId(value = "server_id", type = IdType.INPUT)
    @ApiModelProperty(value="服务ID")
    private Long serverId;

    private static final long serialVersionUID = 1L;

    public static final String COL_DEPLOY_ID = "deploy_id";

    public static final String COL_SERVER_ID = "server_id";
}