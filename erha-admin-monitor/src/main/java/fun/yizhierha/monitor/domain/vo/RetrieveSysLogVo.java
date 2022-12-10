package fun.yizhierha.monitor.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel("查询操作日志vo")
public class RetrieveSysLogVo{

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("日志类型")
    private String logType;

    @ApiModelProperty("参数")
    private String params;

    @ApiModelProperty("IP来源")
    private String requestIp;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("IP归属地")
    private String address;

    @ApiModelProperty("浏览器")
    private String browser;

    @ApiModelProperty(value = "起始耗时")
    private Long startTime;

    @ApiModelProperty(value = "结束耗时")
    private Long endTime;

    @ApiModelProperty(value = "起始创建时间",dataType = "String",example = "2022-03-03")
    private Timestamp startCreateTime;

    @ApiModelProperty(value = "结束创建时间",dataType = "String",example = "2022-03-22")
    private Timestamp endCreateTime;

}