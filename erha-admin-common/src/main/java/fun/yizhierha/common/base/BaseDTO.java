package fun.yizhierha.common.base;

import fun.yizhierha.common.utils.file.ExcelExport;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;

/**
 * @author Zheng Jie
 * @date 2019年10月24日20:48:53
 */
@Getter
@Setter
public class BaseDTO implements Serializable {

    @ExcelExport(value = "被谁创建")
    @ApiModelProperty("被谁创建")
    private String createBy;

    @ExcelExport(value = "创建时间")
    @ApiModelProperty(example = "2022-10-10",dataType = "String",value = "创建时间")
    private Timestamp createTime;

    @ExcelExport(value = "被谁更新")
    @ApiModelProperty("被谁更新")
    private String updateBy;

    @ExcelExport(value = "更新时间")
    @ApiModelProperty(example = "2022-10-10",dataType = "String",value = "更新时间")
    private Timestamp updateTime;

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                builder.append(f.getName(), f.get(this)).append("\n");
            }
        } catch (Exception e) {
            builder.append("toString builder encounter an error");
        }
        return builder.toString();
    }
}
