package fun.yizhierha.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.yizhierha.modules.system.domain.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {
    String[] selectNameByRoleId(@Param("roleId") Long id);
}