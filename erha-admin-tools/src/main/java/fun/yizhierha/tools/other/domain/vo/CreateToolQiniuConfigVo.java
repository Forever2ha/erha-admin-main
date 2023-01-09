package fun.yizhierha.tools.other.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateToolQiniuConfigVo{

    @NotNull(message = "新增时[accessKey]不能为空")
    @ApiModelProperty(value = "accessKey")
    private String accessKey;

    @NotNull(message = "新增时[Bucket 识别符]不能为空")
    @ApiModelProperty(value = "Bucket 识别符")
    private String bucket;

    @NotNull(message = "新增时[外链域名]不能为空")
    @ApiModelProperty(value = "外链域名")
    private String host;

    @NotNull(message = "新增时[secretKey]不能为空")
    @ApiModelProperty(value = "secretKey")
    private String secretKey;

    @NotNull(message = "新增时[空间类型]不能为空")
    @ApiModelProperty(value = "空间类型")
    private String type;

    @ApiModelProperty(value = "机房")
    private String zone;

}