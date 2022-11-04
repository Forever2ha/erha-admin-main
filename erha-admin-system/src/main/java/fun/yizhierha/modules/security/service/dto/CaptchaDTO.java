package fun.yizhierha.modules.security.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("验证码")
@Data
public class CaptchaDTO{
    @ApiModelProperty("标识码")
    private String uuid;
    @ApiModelProperty("图片信息")
    private String img;
}
