package fun.yizhierha.modules.system.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.modules.system.domain.vo.CreateMenuVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveMenuVo;
import fun.yizhierha.modules.system.domain.vo.UpdateMenuVo;
import fun.yizhierha.modules.system.service.dto.MenuDto;
import fun.yizhierha.modules.system.service.dto.SummaryMenuDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public interface SysMenuService extends IService<SysMenu>{


    List<SysMenu> listByRoleIds(List<Long> roleIds);

    List<MenuDto> listMenu(UserDetailsDto currentUser);

    PageUtils<SummaryMenuDto> listTree(RetrieveMenuVo retrieveMenuVo, Query.PageVo pageVo);

    void saveMenu(CreateMenuVo createMenuVo, UserDetailsDto currentUser);

    void editMenu(ValidList<UpdateMenuVo> updateMenuVoList, List<BaseErrDto> errDtoList, UserDetailsDto currentUser);

    void removeMenu(Set<Long> menuIds);

    void download(HttpServletResponse response);

}
