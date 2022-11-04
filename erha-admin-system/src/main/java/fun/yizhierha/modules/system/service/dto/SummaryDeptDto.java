package fun.yizhierha.modules.system.service.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import fun.yizhierha.common.base.BaseDTO;
import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class SummaryDeptDto extends BaseDTO {


    @ApiModelProperty(value = "ID")
    @ExcelExport("ID")
    private Long id;

    @ApiModelProperty(value = "上级部门")
    @ExcelExport("上级部门")
    private Long pid;


    @ApiModelProperty(value = "子部门数目")
    @ExcelExport("子部门数目")
    private Integer subCount;

    @ApiModelProperty(value = "名称")
    @ExcelExport("名称")
    private String name;

    @ApiModelProperty(value = "排序")
    @ExcelExport("排序")
    private Integer deptSort;

    @ApiModelProperty(value = "状态")
    @ExcelExport("状态")
    private Boolean enabled;

}
