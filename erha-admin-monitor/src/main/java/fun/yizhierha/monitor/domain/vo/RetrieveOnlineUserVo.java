package fun.yizhierha.monitor.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel("查询在线用户vo")
public class RetrieveOnlineUserVo{

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("岗位")
    private String dept;

    @ApiModelProperty("浏览器")
    private String browser;

    @ApiModelProperty("ip")
    private String ip;

    @ApiModelProperty("登录地点")
    private String address;

    @ApiModelProperty(value = "起始登录时间",dataType = "String",example = "2022-03-03")
    private Timestamp startLoginTime;

    @ApiModelProperty(value = "结束登录时间",dataType = "String",example = "2022-03-22")
    private Timestamp endLoginTime;

}