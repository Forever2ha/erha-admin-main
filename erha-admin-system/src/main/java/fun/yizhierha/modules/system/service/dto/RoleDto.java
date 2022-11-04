package fun.yizhierha.modules.system.service.dto;

import fun.yizhierha.modules.system.domain.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDto extends SysRole {
    private Long id; // sys_users_roles的 id
    private Long userId; // sys_users_roles的 user_id
}
