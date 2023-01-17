package fun.yizhierha.operation.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("查询应用管理vo")
public class RetrieveOraAppVo{

    @ApiModelProperty("应用名称")
    private String name;


}