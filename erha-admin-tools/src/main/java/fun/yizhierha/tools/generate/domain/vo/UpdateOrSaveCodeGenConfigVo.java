package fun.yizhierha.tools.generate.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改或新增Table生成配置vo")
public class UpdateOrSaveCodeGenConfigVo {

    @ApiModelProperty(value="ID")
    private Long configId;

    /**
     * 表名
     */
    @ApiModelProperty(value="表名")
    private String tableName;

    /**
     * 作者
     */
    @ApiModelProperty(value="作者")
    private String author;

    /**
     * 是否覆盖
     */
    @ApiModelProperty(value="是否覆盖")
    private Boolean cover;

    /**
     * 模块名称
     */
    @ApiModelProperty(value="模块名称")
    private String moduleName;

    /**
     * 至于哪个包下
     */
    @ApiModelProperty(value="至于哪个包下")
    private String pack;

    /**
     * 前端代码生成的路径
     */
    @ApiModelProperty(value="前端代码生成的路径")
    private String path;

    /**
     * 前端Api文件路径
     */
    @ApiModelProperty(value="前端Api文件路径")
    private String apiPath;

    /**
     * 表前缀
     */
    @ApiModelProperty(value="表前缀")
    private String prefix;

    /**
     * 接口名称
     */
    @ApiModelProperty(value="接口名称")
    private String apiAlias;

}
