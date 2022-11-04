package fun.yizhierha.modules.system.domain.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel("查询岗位vo")
public class RetrieveJobVo  {
    @ApiModelProperty("部门名")
    private String name;

    @ApiModelProperty(value = "起始创建时间",dataType = "String",example = "2022-03-03")
    private Timestamp startCreateTime;
    @ApiModelProperty(value = "结束创建时间",dataType = "String",example = "2022-03-22")
    private Timestamp endCreateTime;

    @ApiModelProperty("岗位状态")
    private Boolean jobStatus;
}
