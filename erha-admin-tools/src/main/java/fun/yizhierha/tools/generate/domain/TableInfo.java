package fun.yizhierha.tools.generate.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("表格信息")
public class TableInfo {

    private String tableName;

    private Date createTime;

    private String engine;

    private String tableCollation;

    private String tableComment;
}
