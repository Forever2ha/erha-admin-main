package fun.yizhierha.operation.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "应用管理")
@Data
@TableName(value = "ora_deploy_server")
public class OraDeployServer implements Serializable {
    @TableField(value = "deploy_id")
    @ApiModelProperty(value = "部署ID")
    @ExcelExport("部署ID")
    private Long deployId;
    @TableField(value = "server_id")
    @ApiModelProperty(value = "服务ID")
    @ExcelExport("服务ID")
    private Long serverId;
    @TableField(value = "project_id")
    @ApiModelProperty(value = "项目ID")
    @ExcelExport("项目ID")
    private Long projectId;
}
