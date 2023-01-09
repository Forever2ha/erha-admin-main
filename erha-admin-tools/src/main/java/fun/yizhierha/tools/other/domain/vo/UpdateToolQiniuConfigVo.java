package fun.yizhierha.tools.other.domain.vo;

import fun.yizhierha.common.base.UpdateVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateToolQiniuConfigVo extends UpdateVo{

    @ApiModelProperty(value = "accessKey")
    private String accessKey;

    @ApiModelProperty(value = "Bucket 识别符")
    private String bucket;

    @ApiModelProperty(value = "外链域名")
    private String host;

    @ApiModelProperty(value = "secretKey")
    private String secretKey;

    @ApiModelProperty(value = "空间类型")
    private String type;

    @ApiModelProperty(value = "机房")
    private String zone;

}