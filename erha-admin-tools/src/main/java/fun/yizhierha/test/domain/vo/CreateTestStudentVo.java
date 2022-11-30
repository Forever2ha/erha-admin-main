package fun.yizhierha.test.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.validation.constraints.NotNull;

@Data
public class CreateTestStudentVo{

    @NotNull(message = "新增时[学生姓名]不能为空")
    @ApiModelProperty(value = "学生姓名")
    private String name;

    @NotNull(message = "新增时[水果]不能为空")
    @ApiModelProperty(value = "水果")
    private String[] likeFood;

    @NotNull(message = "新增时[年龄]不能为空")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @NotNull(message = "新增时[班级名]不能为空")
    @ApiModelProperty(value = "班级名")
    private String clazzName;

    @NotNull(message = "新增时[性别]不能为空")
    @ApiModelProperty(value = "性别")
    private Boolean gender;

    @NotNull(message = "新增时[综合评分]不能为空")
    @ApiModelProperty(value = "综合评分")
    private BigDecimal avgScore;

    @NotNull(message = "新增时[状态]不能为空")
    @ApiModelProperty(value = "状态")
    private Boolean enabled;

    @NotNull(message = "新增时[账号过期时间]不能为空")
    @ApiModelProperty(value = "账号过期时间")
    private Timestamp expireTime;

}