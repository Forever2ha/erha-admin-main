package fun.yizhierha.modules.system.service.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import fun.yizhierha.modules.system.domain.SysDept;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;


@Data
public class DeptDto{
    private List<DeptDto> childrenDept;

    /**
     * ID
     */
    private Long deptId;

    /**
     * 上级部门
     */
    private Long pid;

    /**
     * 子部门数目
     */
    private Integer subCount;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer deptSort;

    /**
     * 状态
     */
    private Boolean enabled;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
