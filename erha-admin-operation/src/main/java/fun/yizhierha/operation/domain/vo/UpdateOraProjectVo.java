package fun.yizhierha.operation.domain.vo;

import fun.yizhierha.common.base.UpdateVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;


@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateOraProjectVo extends UpdateVo{

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

}