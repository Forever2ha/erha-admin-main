package fun.yizhierha.modules.system.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.yizhierha.modules.system.domain.SysRole;
import fun.yizhierha.modules.system.domain.vo.CreateRoleVo;
import fun.yizhierha.modules.system.domain.vo.UpdateRoleVo;
import fun.yizhierha.modules.system.service.dto.SummaryRoleDto;
import fun.yizhierha.modules.system.service.dto.SummaryUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "roleId",source = "id")
    SysRole toSysRole(UpdateRoleVo updateRoleVo);

    SysRole toSysRole(CreateRoleVo createRoleVo);

    Set<SummaryUserDto.Role> toSummaryUserDtoSet(Set<SysRole> sysRoles);

    @Mapping(target = "id",source = "roleId")
    SummaryRoleDto toSummaryRoleDto(SysRole sysRole);

    List<SummaryRoleDto> toSummaryRoleDto(List<SysRole> sysRole);

    Page<SummaryRoleDto> toBeSummaryRoleDtoIPage(IPage<SysRole> sysRoleIPage);

    default Page<SummaryRoleDto> toSummaryRoleDtoIPage(IPage<SysRole> sysRoleIPage){
        Page<SummaryRoleDto> summaryRoleDtoPage = toBeSummaryRoleDtoIPage(sysRoleIPage);
        summaryRoleDtoPage.setRecords(toSummaryRoleDto(sysRoleIPage.getRecords()));
        return summaryRoleDtoPage;
    }

    default SummaryUserDto.Role map(SysRole sysRole){
        SummaryUserDto.Role role = new SummaryUserDto.Role();
        role.setId(sysRole.getRoleId());
        role.setDataScope(sysRole.getDataScope());
        role.setName(sysRole.getName());
        role.setLevel(role.getLevel());
        return role;
    }

}
