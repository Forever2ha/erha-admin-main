package fun.yizhierha.modules.system.service.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import fun.yizhierha.common.base.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DictDetailDto extends BaseDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "字典id")
    private Long dictId;


    @ApiModelProperty(value = "字典标签")
    private String label;


    @ApiModelProperty(value = "字典值")
    private String value;

     @ApiModelProperty(value = "排序")
    private Integer dictSort;
}
