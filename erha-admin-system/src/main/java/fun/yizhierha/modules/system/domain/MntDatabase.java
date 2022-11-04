package fun.yizhierha.modules.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
    * 数据库管理
    */
@ApiModel(value="数据库管理")
@Data
@TableName(value = "mnt_database")
public class MntDatabase implements Serializable {
    /**
     * ID
     */
    @TableId(value = "db_id", type = IdType.INPUT)
    @ApiModelProperty(value="ID")
    private String dbId;

    /**
     * 名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="名称")
    private String name;

    /**
     * jdbc连接
     */
    @TableField(value = "jdbc_url")
    @ApiModelProperty(value="jdbc连接")
    private String jdbcUrl;

    /**
     * 账号
     */
    @TableField(value = "user_name")
    @ApiModelProperty(value="账号")
    private String userName;

    /**
     * 密码
     */
    @TableField(value = "pwd")
    @ApiModelProperty(value="密码")
    private String pwd;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value="创建者")
    private String createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    @ApiModelProperty(value="更新者")
    private String updateBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_DB_ID = "db_id";

    public static final String COL_NAME = "name";

    public static final String COL_JDBC_URL = "jdbc_url";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_PWD = "pwd";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}