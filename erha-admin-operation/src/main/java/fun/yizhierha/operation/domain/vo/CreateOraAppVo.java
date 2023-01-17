package fun.yizhierha.operation.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class CreateOraAppVo {

    @ApiModelProperty(value = "应用名称")
    private String name;

    @ApiModelProperty(value = "上传目录")
    private String uploadPath;

    @ApiModelProperty(value = "部署路径")
    private String deployPath;

    @ApiModelProperty(value = "备份路径")
    private String backupPath;

    @ApiModelProperty(value = "应用端口")
    private Integer port;

    @ApiModelProperty(value = "启动脚本")
    private String startScript;

    @ApiModelProperty(value = "部署脚本")
    private String deployScript;

    @ApiModelProperty(value = "创建者")
    private String createBy;

}