package fun.yizhierha.modules.security.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("在线用户信息")
public class OnlineUserDto {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("岗位")
    private String dept;

    @ApiModelProperty("浏览器")
    private String browser;

    @ApiModelProperty("IP")
    private String ip;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("token")
    private String encryptKey;

    @ApiModelProperty("登录时间")
    private Date loginTime;


}
