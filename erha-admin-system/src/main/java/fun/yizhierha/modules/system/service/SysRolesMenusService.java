package fun.yizhierha.modules.system.service;

import fun.yizhierha.modules.system.domain.SysRolesMenus;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.modules.system.domain.vo.UpdateRoleMenuVo;

public interface SysRolesMenusService extends IService<SysRolesMenus>{


    void updateRoleMenu(UpdateRoleMenuVo updateMenuVo);

}
