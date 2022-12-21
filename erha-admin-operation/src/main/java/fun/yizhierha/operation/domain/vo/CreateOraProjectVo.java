package fun.yizhierha.operation.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CreateOraProjectVo {

    @ApiModelProperty(value = "项目名称")
    private String pname;

    @ApiModelProperty(value = "项目计划开始日期")
    private Timestamp planStartDate;

    @ApiModelProperty(value = "项目计划结束日期")
    private Timestamp planFinishDate;

    @ApiModelProperty(value = "实际开始日期")
    private Timestamp actuStartDate;

    @ApiModelProperty(value = "实际结束日期")
    private Timestamp actuFinishDate;

    @ApiModelProperty(value = "状态")
    private Boolean enabled;
    @ApiModelProperty(value = "创建者")
    private String createBy;


}