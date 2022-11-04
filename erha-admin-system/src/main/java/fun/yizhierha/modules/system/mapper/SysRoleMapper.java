package fun.yizhierha.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.yizhierha.modules.system.domain.SysRole;
import fun.yizhierha.modules.system.service.dto.RoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    Set<SysRole> selectSysRoleByUserId(@Param("userId") Long userId);

    Set<RoleDto> selectRoleDtoListByUserIds(@Param("userIds") List<Long> userIds);
}