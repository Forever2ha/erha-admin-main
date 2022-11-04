package fun.yizhierha.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.yizhierha.modules.security.service.UserCacheManager;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.vo.UpdateRoleMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.modules.system.domain.SysRolesMenus;
import fun.yizhierha.modules.system.mapper.SysRolesMenusMapper;
import fun.yizhierha.modules.system.service.SysRolesMenusService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SysRolesMenusServiceImpl extends ServiceImpl<SysRolesMenusMapper, SysRolesMenus> implements SysRolesMenusService{


    @Autowired
    UserCacheManager userCacheManager;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized void updateRoleMenu(UpdateRoleMenuVo updateMenuVo) {
        // 1. 删除原有信息
        remove(new QueryWrapper<SysRolesMenus>().eq(SysRolesMenus.COL_ROLE_ID,updateMenuVo.getRoleId()));
        // 2. 添加新信息
        List<SysRolesMenus> sysRolesMenusList = new ArrayList<>();
        for (Long menuId : updateMenuVo.getMenuIdList()) {
            SysRolesMenus sysRolesMenus = new SysRolesMenus();
            sysRolesMenus.setMenuId(menuId);
            sysRolesMenus.setRoleId(updateMenuVo.getRoleId());
            sysRolesMenusList.add(sysRolesMenus);
        }
        // 3. 清除此角色用户的缓存
        userCacheManager.cleanCacheByRoleIds(Collections.singletonList(updateMenuVo.getRoleId()));
        // 4. 保存
        saveBatch(sysRolesMenusList);
    }
}
