package fun.yizhierha.tools.other.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("查询邮件配置vo")
public class RetrieveToolEmailConfigVo{

    @ApiModelProperty("收件人")
    private String fromUser;

}