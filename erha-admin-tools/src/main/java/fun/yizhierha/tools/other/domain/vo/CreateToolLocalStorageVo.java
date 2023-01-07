package fun.yizhierha.tools.other.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateToolLocalStorageVo{

    @NotNull(message = "新增时[文件名]不能为空")
    @ApiModelProperty(value = "文件名")
    private String name;

}