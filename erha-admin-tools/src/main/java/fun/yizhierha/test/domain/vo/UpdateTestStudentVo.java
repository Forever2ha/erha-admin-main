package fun.yizhierha.test.domain.vo;

import fun.yizhierha.common.base.UpdateVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Timestamp;


@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateTestStudentVo extends UpdateVo{

    @ApiModelProperty(value = "学生姓名")
    private String name;

    @ApiModelProperty(value = "水果")
    private String[] likeFood;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "班级名")
    private String clazzName;

    @ApiModelProperty(value = "性别")
    private Boolean gender;

    @ApiModelProperty(value = "综合评分")
    private BigDecimal avgScore;

    @ApiModelProperty(value = "状态")
    private Boolean enabled;

    @ApiModelProperty(value = "账号过期时间")
    private Timestamp expireTime;

}