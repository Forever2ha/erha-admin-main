package fun.yizhierha.operation.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CreateOraDeployHistoryVo {

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "部署日期")
    private Timestamp deployDate;

    @ApiModelProperty(value = "部署用户")
    private String deployUser;

    @ApiModelProperty(value = "服务器IP")
    private String ip;

    @ApiModelProperty(value = "部署编号")
    private Long deployId;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;
//    @ApiModelProperty(value = "创建者")
//    private String createBy;
@ApiModelProperty(value = "文件名称")
private String fileName;
}