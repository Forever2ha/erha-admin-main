package fun.yizhierha.operation.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateOraDatabaseVo{

    @NotNull(message = "新增时[名称]不能为空")
    @ApiModelProperty(value = "名称")
    private String name;

    @NotNull(message = "新增时[jdbc连接]不能为空")
    @ApiModelProperty(value = "jdbc连接")
    private String jdbcUrl;

    @NotNull(message = "新增时[账号]不能为空")
    @ApiModelProperty(value = "账号")
    private String userName;

    @NotNull(message = "新增时[密码]不能为空")
    @ApiModelProperty(value = "密码")
    private String pwd;

    @NotNull(message = "新增时[项目ID]不能为空")
    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @NotNull(message = "新增时[类型]不能为空")
    @ApiModelProperty(value = "类型")
    private String typeDatabese;

    @ApiModelProperty(value = "创建者")
    private String createBy;
}