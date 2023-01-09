package fun.yizhierha.tools.other.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("查询七牛云vo")
public class RetrieveToolQiniuConfigVo{
    private Long configId;

    private Boolean active;
}