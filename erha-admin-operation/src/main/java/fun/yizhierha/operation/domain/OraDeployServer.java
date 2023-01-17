package fun.yizhierha.operation.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "应用管理")
@Data
@TableName(value = "ora_deploy_server")
public class OraDeployServer implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty("id")
    private Long id;

    @TableField(value = "deploy_id")
    @ApiModelProperty(value = "部署ID")
    private Long deployId;
    @TableField(value = "server_id")
    @ApiModelProperty(value = "服务ID")
    private Long serverId;


    public static final String COL_DEPLOY_ID = "deploy_id";
    public static final String COL_SERVER_ID = "server_id";
}
