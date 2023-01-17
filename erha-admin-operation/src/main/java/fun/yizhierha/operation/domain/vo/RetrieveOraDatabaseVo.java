package fun.yizhierha.operation.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("查询部署管理vo")
public class RetrieveOraDatabaseVo{

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("账号")
    private String userName;

    @ApiModelProperty("类型")
    private String typeDatabese;

}