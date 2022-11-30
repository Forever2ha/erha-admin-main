package fun.yizhierha.tools.generate.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
    * 代码生成器配置
    */
@ApiModel(value="代码生成器配置")
@Data
@TableName(value = "code_gen_config")
public class CodeGenConfig implements Serializable {
    /**
     * ID
     */
    @TableId(value = "config_id", type = IdType.INPUT)
    @ApiModelProperty(value="ID")
    private Long configId;

    /**
     * 表名
     */
    @TableField(value = "`table_name`")
    @ApiModelProperty(value="表名")
    private String tableName;

    /**
     * 作者
     */
    @TableField(value = "author")
    @ApiModelProperty(value="作者")
    private String author;

    /**
     * 是否覆盖
     */
    @TableField(value = "cover")
    @ApiModelProperty(value="是否覆盖")
    private Boolean cover;

    /**
     * 模块名称
     */
    @TableField(value = "module_name")
    @ApiModelProperty(value="模块名称")
    private String moduleName;

    /**
     * 至于哪个包下
     */
    @TableField(value = "pack")
    @ApiModelProperty(value="至于哪个包下")
    private String pack;

    /**
     * 前端代码生成的路径
     */
    @TableField(value = "`path`")
    @ApiModelProperty(value="前端代码生成的路径")
    private String path;

    /**
     * 前端Api文件路径
     */
    @TableField(value = "api_path")
    @ApiModelProperty(value="前端Api文件路径")
    private String apiPath;

    /**
     * 表前缀
     */
    @TableField(value = "`prefix`")
    @ApiModelProperty(value="表前缀")
    private String prefix;

    /**
     * 接口名称
     */
    @TableField(value = "api_alias")
    @ApiModelProperty(value="接口名称")
    private String apiAlias;

    private static final long serialVersionUID = 1L;

    public static final String COL_CONFIG_ID = "config_id";

    public static final String COL_TABLE_NAME = "table_name";

    public static final String COL_AUTHOR = "author";

    public static final String COL_COVER = "cover";

    public static final String COL_MODULE_NAME = "module_name";

    public static final String COL_PACK = "pack";

    public static final String COL_PATH = "path";

    public static final String COL_API_PATH = "api_path";

    public static final String COL_PREFIX = "prefix";

    public static final String COL_API_ALIAS = "api_alias";
}