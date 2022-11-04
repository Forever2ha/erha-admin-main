package fun.yizhierha.modules.system.service;

import fun.yizhierha.modules.system.domain.SysRole;
import fun.yizhierha.modules.system.domain.SysRolesDepts;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.modules.system.service.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface SysRolesDeptsService extends IService<SysRolesDepts>{


    Set<Long> getDataScopeByRoles(List<SysRole> roleIds, UserDto userDto);
}
