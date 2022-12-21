package fun.yizhierha.operation.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateOraDeployVo {

//    @NotNull(message = "新增时[ID]不能为空")
    @ApiModelProperty(value = "ID")
    private Long deployId;
    @NotNull(message = "新增时[ID]不能为空")
    @ApiModelProperty(value = "应用编号")
    private Long appId;
    @NotNull(message = "新增时[项目ID]不能为空")
    @ApiModelProperty(value = "项目ID")
    private Long projectId;
    @NotNull(message = "新增时[服务器]不能为空")
    @ApiModelProperty(value = "服务器")
    private Long serverId;
    @ApiModelProperty(value = "创建者")
    private String createBy;
}