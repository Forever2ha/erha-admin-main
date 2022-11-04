package fun.yizhierha.modules.system.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.modules.system.domain.vo.CreateRoleVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveRoleVo;
import fun.yizhierha.modules.system.domain.vo.UpdateRoleVo;
import fun.yizhierha.modules.system.service.dto.RoleDto;
import fun.yizhierha.modules.system.service.dto.SummaryRoleDto;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public interface SysRoleService extends IService<SysRole>{


    Set<SysRole> selectSysRoleByUserId(Long userId);


    List<GrantedAuthority> mapRolesPermissionToAuthorities(Set<SysRole> roles, Long userId);

    void switchNowRole(UserDetailsDto currentUser, SysRole roleToBeChanged);

    Set<RoleDto> getRoleDtoSetByUserIds(List<Long> userIds);

    PageUtils<SummaryRoleDto> list(RetrieveRoleVo retrieveRoleVo, Query.PageVo pageVo);

    void save(CreateRoleVo createRoleVo, UserDetailsDto currentUser);

    void editRole(ValidList<UpdateRoleVo> updateRoleVos, List<BaseErrDto> errDtos, UserDetailsDto currentUser);

    void removeByIds(Set<Long> list);

    void download(HttpServletResponse response);
}
