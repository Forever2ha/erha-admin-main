package fun.yizhierha.tools.other.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel("发送邮件vo")
public class SendEmailVo {

    @Email(message = "邮件地址不合法")
    @ApiModelProperty("发送地址")
    private String address;

    @NotBlank(message = "发送内容不能为空")
    @ApiModelProperty("内容")
    private String content;

    @NotBlank(message = "主题不能为空")
    @ApiModelProperty("主题")
    private String title;
}
