package fun.yizhierha.modules.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel("查询任务执行日志vo")
public class RetrieveQuartzLogVo {
    @ApiModelProperty("任务名称")
    private String name;

    @ApiModelProperty("执行成功与否")
    private Boolean isSuccess;

    @ApiModelProperty(value = "起始执行时间",dataType = "String",example = "2022-03-03")
    private Timestamp startExecTime;

    @ApiModelProperty(value = "结束执行时间",dataType = "String",example = "2022-03-22")
    private Timestamp endExecTime;
}
