package fun.yizhierha.tools.other.domain.vo;

import fun.yizhierha.common.base.UpdateVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateToolLocalStorageVo extends UpdateVo{

    @ApiModelProperty(value = "文件名")
    private String name;

}