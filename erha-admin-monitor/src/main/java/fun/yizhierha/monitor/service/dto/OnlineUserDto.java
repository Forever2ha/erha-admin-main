package fun.yizhierha.monitor.service.dto;

import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("在线用户信息")
public class OnlineUserDto {

    @ApiModelProperty("用户名")
    @ExcelExport("用户名")
    private String userName;

    @ApiModelProperty(value = "昵称")
    @ExcelExport("昵称")
    private String nickName;

    @ApiModelProperty(value = "岗位")
    @ExcelExport("岗位")
    private String dept;

    @ApiModelProperty(value = "浏览器")
    @ExcelExport("浏览器")
    private String browser;

    @ApiModelProperty(value = "ip")
    @ExcelExport("ip")
    private String ip;

    @ApiModelProperty(value = "登录地点")
    @ExcelExport("登录地点")
    private String address;

    @ApiModelProperty(value = "加密token")
    @ExcelExport("加密token")
    private String encryptKey;

    @ApiModelProperty(value = "登录时间")
    @ExcelExport("登录时间")
    private Timestamp loginTime;

}
