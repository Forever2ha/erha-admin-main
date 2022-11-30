package fun.yizhierha.tools.generate.domain.vo;

import fun.yizhierha.common.base.UpdateVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("修改表字段vo")
public class UpdateTableColVo extends UpdateVo {
    private String columnType;

    private String dictName;

    private Boolean formShow;

    private String formType;


    private Boolean listShow;

    private Boolean notNull;

    private String queryType;

    private String remark;

    private String dateAnnotation;
}
