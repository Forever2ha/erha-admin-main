package fun.yizhierha.tools.other.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel("查询存储管理vo")
public class RetrieveToolLocalStorageVo{

    @ApiModelProperty("文件真实的名称")
    private String realName;

    @ApiModelProperty("文件名")
    private String name;

    @ApiModelProperty("后缀")
    private String suffix;

    @ApiModelProperty(value = "起始大小")
    private Long startBitSize;

    @ApiModelProperty(value = "结束大小")
    private Long endBitSize;

    @ApiModelProperty(value = "起始创建日期",dataType = "String",example = "2022-03-03")
    private Timestamp startCreateTime;

    @ApiModelProperty(value = "结束创建日期",dataType = "String",example = "2022-03-22")
    private Timestamp endCreateTime;

    @ApiModelProperty(value = "起始更新时间",dataType = "String",example = "2022-03-03")
    private Timestamp startUpdateTime;

    @ApiModelProperty(value = "结束更新时间",dataType = "String",example = "2022-03-22")
    private Timestamp endUpdateTime;

}