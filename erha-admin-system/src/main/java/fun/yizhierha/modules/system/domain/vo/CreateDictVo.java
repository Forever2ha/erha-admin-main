package fun.yizhierha.modules.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel("添加字典vo")
public class CreateDictVo {

    @ApiModelProperty("名称")
    @NotNull(message = "字典名称不能为空")
    private String name;

    @ApiModelProperty("描述")
    private String description;
}
