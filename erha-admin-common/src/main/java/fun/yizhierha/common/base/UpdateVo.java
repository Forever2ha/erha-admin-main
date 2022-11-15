package fun.yizhierha.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateVo {
    @NotNull(message = "Id不能为空")
    @ApiModelProperty("Id")
    private Long id;

}
