package fun.yizhierha.modules.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Data
@ApiModel("查询用户列表Vo")
public class RetrieveUserVo {
    @ApiModelProperty("部门id")
    private Set<Long> deptIds;
    @ApiModelProperty("username或者nickName或者邮箱")
    private String name;
    @ApiModelProperty(value = "起始创建时间",dataType = "String",example = "2022-03-03")
    private Timestamp startCreateTime;
    @ApiModelProperty(value = "结束创建时间",dataType = "String",example = "2022-03-22")
    private Timestamp endCreateTime;
    @ApiModelProperty("状态")
    private Boolean userStatus; 
}
