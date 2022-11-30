package fun.yizhierha.test.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.math.BigDecimal;

@Data
@ApiModel("查询学生vo")
public class RetrieveTestStudentVo{

    @ApiModelProperty("学生姓名")
    private String name;

    @ApiModelProperty(value = "水果")
    private String[] likeFood;

    @ApiModelProperty("班级名")
    private String clazzName;

    @ApiModelProperty("性别")
    private Boolean gender;

    @ApiModelProperty("更新时间")
    private Timestamp updateTime;

    @ApiModelProperty("账号过期时间")
    private Timestamp expireTime;

    @ApiModelProperty(value = "起始综合评分")
    private BigDecimal startAvgScore;

    @ApiModelProperty(value = "结束综合评分")
    private BigDecimal endAvgScore;

}