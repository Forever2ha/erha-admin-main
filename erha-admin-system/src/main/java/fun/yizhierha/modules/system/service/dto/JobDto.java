package fun.yizhierha.modules.system.service.dto;

import fun.yizhierha.modules.system.domain.SysJob;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class JobDto extends SysJob {
    private Long id; // sys_users_jobs的 id
    private Long userId; // sys_users_jobs的 user_id
}
