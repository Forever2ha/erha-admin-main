package fun.yizhierha.modules.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.constant.EhAdminConstant;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.StringUtils;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.common.utils.file.ExcelUtils;
import fun.yizhierha.modules.security.service.UserCacheManager;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysRolesMenus;
import fun.yizhierha.modules.system.domain.vo.CreateMenuVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveMenuVo;
import fun.yizhierha.modules.system.domain.vo.UpdateMenuVo;
import fun.yizhierha.modules.system.domain.vo.UpdateRoleMenuVo;
import fun.yizhierha.modules.system.service.SysRolesMenusService;
import fun.yizhierha.modules.system.service.dto.MenuDto;
import fun.yizhierha.modules.system.service.dto.SummaryMenuDto;
import fun.yizhierha.modules.system.service.mapstruct.MenuMapper;
import fun.yizhierha.tools.generate.domain.CodeGenConfig;
import fun.yizhierha.tools.generate.service.CodeGenConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.modules.system.domain.SysMenu;
import fun.yizhierha.modules.system.mapper.SysMenuMapper;
import fun.yizhierha.modules.system.service.SysMenuService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService{

    @Autowired(required = false)
    MenuMapper menuMapper;

    @Autowired
    SysRolesMenusService sysRolesMenusService;

    @Autowired
    CodeGenConfigService codeGenConfigService;

    @Autowired
    UserCacheManager cacheManager;

    @Override
    public List<SysMenu> listByRoleIds(List<Long> roleIds) {
        return baseMapper.selectByRoleIds(roleIds);
    }

    @Override
    public List<MenuDto> listMenu(UserDetailsDto currentUser) {
        List<SysMenu> sysMenuList = listByRoleIds(Collections.singletonList(currentUser.getNowRole().getRoleId()));
        List<MenuDto> resMenuDtoList = new ArrayList<>();

        for (SysMenu sysMenu : sysMenuList) {
            // 1.??????????????????
            if (sysMenu.getPid() == null){
                MenuDto menuDto = menuMapper.toMenuDto(sysMenu);
                resMenuDtoList.add(menuDto);
            }
        }

        // 1.5 ??????
        sysMenuList.sort(Comparator.comparing(SysMenu::getOrder));

        // 2.????????????
        for (MenuDto menuDto : resMenuDtoList) {
            // ?????????????????????0
            if (!menuDto.getSubCount().equals(0L)){
                List<MenuDto> menuDtoList = getChildMenu(menuDto.getMenuId(),sysMenuList);
                // ??????
                menuDtoList.sort(Comparator.comparing((t) -> t.getMeta().getOrder()));
                menuDto.setChildren(menuDtoList);
            }
        }



        return resMenuDtoList;
    }

    @Override
    public PageUtils<SummaryMenuDto> listTree(RetrieveMenuVo retrieveMenuVo, Query.PageVo pageVo) {
        // 1. ??????????????????
        // 1.1 ??????????????????wrapper
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        String title = retrieveMenuVo.getTitle();
        Timestamp startCreateTime = retrieveMenuVo.getStartCreateTime();
        Timestamp endCreateTime = retrieveMenuVo.getEndCreateTime();
        if (startCreateTime != null && endCreateTime != null) {
            wrapper.between(SysMenu.COL_CREATE_TIME,startCreateTime,endCreateTime);
        }
        List<SummaryMenuDto> allMenus;
        // 1.2 ??????????????????SummaryMenuDto
        if (wrapper.isEmptyOfWhere()){
            allMenus = menuMapper.toSummaryMenuDtoList(list());
        }else {
            allMenus = menuMapper.toSummaryMenuDtoList(list(wrapper));
        }


        // 2. ???????????????: pid == null  ??????title?????????????????????title??????????????????????????????
        List<SummaryMenuDto> parentMenuList = title == null ?
                allMenus.stream()
                        .filter((t) -> t.getPid() == null)
                        .collect(Collectors.toList())
                :
                allMenus.stream()
                        .filter((t) -> t.getTitle().contains(title))
                        .collect(Collectors.toList())
                ;
        parentMenuList.sort(Comparator.comparing(SummaryMenuDto::getOrder));
        List<SummaryMenuDto> res = new ArrayList<>(parentMenuList);

        // 3. ???????????????
        for (SummaryMenuDto parentMenu : parentMenuList) {
            // ?????????????????????0
            if (!parentMenu.getSubCount().equals(0)){
                List<SummaryMenuDto> childSummaryMenu = getChildSummaryMenu(parentMenu.getId(), allMenus);
                childSummaryMenu.sort(Comparator.comparing(SummaryMenuDto::getOrder));
                parentMenu.setChildren(childSummaryMenu);
            }
        }
        // 4. ??????
        return new PageUtils<>(res,res.size(),pageVo.getPageSize() == null ? 99999: pageVo.getPageSize(),1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveMenu(CreateMenuVo createMenuVo, UserDetailsDto currentUser) {
        // 0. ??????name title????????????
        String name = createMenuVo.getName();
        String title = createMenuVo.getTitle();
        if (name != null && !list(new QueryWrapper<SysMenu>().eq(SysMenu.COL_NAME,name)).isEmpty()){
            throw new BadRequestException(
                    BizCodeEnum.Client_Error_CRUD.getCode(),
                    "??????????????????"
            );
        }
        if (title != null && !list(new QueryWrapper<SysMenu>().eq(SysMenu.COL_TITLE,title)).isEmpty()){
            throw new BadRequestException(
                    BizCodeEnum.Client_Error_CRUD.getCode(),
                    "?????????????????????"
            );
        }

        // 1. ??????????????????
        Long type = Long.valueOf(createMenuVo.getType());
        Long pid = createMenuVo.getPid();
        SysMenu parentSysMenu = getOne(new QueryWrapper<SysMenu>().eq(SysMenu.COL_MENU_ID, pid));
        Long parentSysMenuType = parentSysMenu == null ? -1 : parentSysMenu.getType();
        // 1.1 <??????>: pid???????????? || pid???????????????????????????<??????>
        if (type.equals(EhAdminConstant.MenuType.CATALOG.getValue())){
            if (!(pid == null || parentSysMenuType.equals(EhAdminConstant.MenuType.CATALOG.getValue()))){
               throw new BadRequestException(
                       BizCodeEnum.Client_Error_CRUD.getCode(),
                       "??????????????????<??????>??????pid???????????? || pid???????????????????????????<??????>"
               );
            }
        }
        // 1.2 <??????>: pid???????????? && pid????????????????????????<??????>
        if (type.equals(EhAdminConstant.MenuType.MENU.getValue())){
            if (!(pid != null && parentSysMenuType.equals(EhAdminConstant.MenuType.CATALOG.getValue()))){
                throw new BadRequestException(
                        BizCodeEnum.Client_Error_CRUD.getCode(),
                        "??????????????????<??????>???: pid???????????? && pid????????????????????????<??????>"
                );
            }
        }
        // 1.3 <??????>: pid???????????? && pid????????????????????????<??????>
        if (type.equals(EhAdminConstant.MenuType.BUTTON.getValue())){
            if (!(pid != null && parentSysMenuType.equals(EhAdminConstant.MenuType.MENU.getValue()))){
                throw new BadRequestException(
                        BizCodeEnum.Client_Error_CRUD.getCode(),
                        "??????????????????<??????>???: pid???????????? && pid????????????????????????<??????>"
                );
            }
        }
        // 2. ?????????????????????????????????
        SysMenu sysMenu = menuMapper.toSysMenu(createMenuVo,currentUser.getUsername());
        // 3.??????
        save(sysMenu);
        // 4.??????????????????subCount
        if (pid != null){
            update(new UpdateWrapper<SysMenu>().eq(SysMenu.COL_MENU_ID,pid).setSql(
               SysMenu.COL_SUB_COUNT + " = " + SysMenu.COL_SUB_COUNT + " + 1"
            ));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void editMenu(ValidList<UpdateMenuVo> updateMenuVoList, List<BaseErrDto> errDtoList, UserDetailsDto currentUser) {
        List<Long> toRemoveIds = new ArrayList<>();
        List<SysMenu> toUpdateMenuList = new ArrayList<>();

        for (UpdateMenuVo updateMenuVo : updateMenuVoList) {
            String title = updateMenuVo.getTitle();
            String name = updateMenuVo.getName();
            Long id = updateMenuVo.getId();
            // 1. ??????name???title???????????????,?????????????????????id,??????????????????
            if (name != null && !list(new QueryWrapper<SysMenu>().eq(SysMenu.COL_NAME,name)).isEmpty()){
                toRemoveIds.add(id);

                BaseErrDto baseErrDto = new BaseErrDto();
                baseErrDto.setId(id);
                baseErrDto.setErrorMsg("?????????????????????");
                baseErrDto.setErrorVal(name);
                baseErrDto.setErrorField("name");
                errDtoList.add(baseErrDto);
            }
            if (title != null && !list(new QueryWrapper<SysMenu>().eq(SysMenu.COL_TITLE,title)).isEmpty()){
                toRemoveIds.add(id);

                BaseErrDto baseErrDto = new BaseErrDto();
                baseErrDto.setId(id);
                baseErrDto.setErrorMsg("?????????????????????");
                baseErrDto.setErrorVal(title);
                baseErrDto.setErrorField("title");
                errDtoList.add(baseErrDto);
            }
            // 3. ??????????????????
            toUpdateMenuList.add(menuMapper.toSysMenu(updateMenuVo,currentUser.getUsername()));
        }

        // 2. ??????????????????id
        for (Long toRemoveId : toRemoveIds) {
            updateMenuVoList.removeIf((t) -> toRemoveId.equals(t.getId()));
        }

        // 4. ??????
        if (!toUpdateMenuList.isEmpty()){
            updateBatchById(toUpdateMenuList);
        }

        // 5.?????????????????????????????????????????????
        List<Long> menuIdList = toUpdateMenuList
                .stream()
                .map(SysMenu::getMenuId)
                .collect(Collectors.toList());
        List<Long> roleIdList = sysRolesMenusService.list(new QueryWrapper<SysRolesMenus>().in(SysRolesMenus.COL_MENU_ID, menuIdList))
                .stream()
                .map(SysRolesMenus::getRoleId)
                .collect(Collectors.toList());
        cacheManager.cleanCacheByRoleIds(roleIdList);
    }

    @Override
    public void removeMenu(Set<Long> menuIds) {
        // 1.?????????????????????
        if (!sysRolesMenusService.list(new QueryWrapper<SysRolesMenus>().in(SysRolesMenus.COL_MENU_ID,menuIds)).isEmpty()) {
            throw new BadRequestException(
                    BizCodeEnum.Client_Error_CRUD.getCode(),
                    "??????????????????????????????????????????????????????"
            );
        }
        // 2.??????
        removeBatchByIds(menuIds);
    }

    @Override
    public void download(HttpServletResponse response) {
        List<SysMenu> sysMenuList = list();
        ExcelUtils.export(response,"??????",sysMenuList,SysMenu.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void generateMenu(String tableName) {
        if (StringUtils.isBlank(tableName)) throw new BadRequestException("tableName????????????");
        CodeGenConfig codeGenConfig = codeGenConfigService.getOne(new QueryWrapper<CodeGenConfig>()
                .eq(CodeGenConfig.COL_TABLE_NAME, tableName));
        if (codeGenConfig == null) throw new BadRequestException("????????????[step2]");
        // eg. codeGenConfig.getApiAlias() = "???????????????????????????" -> zhNames = {"????????????","????????????"}
        // eg. codeGenConfig.getApiPath() = "test/student" -> enNames = {"test","student"}
        String[] zhNames = codeGenConfig.getApiAlias().split("[:???]");
        String[] enNames = codeGenConfig.getApiPath().split("/");
        String className = StringUtils.toCamelCase(codeGenConfig.getTableName());


        // 1. ????????????,???????????????????????????
        // ??????
        SysMenu dir = this.getOne(new QueryWrapper<SysMenu>().eq(SysMenu.COL_TITLE, zhNames[0]));

        if (dir == null){
            dir = new SysMenu();
            dir.setSubCount(1);
            dir.setType(EhAdminConstant.MenuType.CATALOG.getValue());
            dir.setTitle(zhNames[0]);
            dir.setName(StringUtils.capitalize(enNames[0]));
            dir.setLocale("menu."+enNames[0]);
            dir.setOrder(999);
            dir.setIcon("icon-heart-fill");
            dir.setIFrame(false);
            dir.setCache(false);
            dir.setHidden(false);
            dir.setCreateBy("SYSTEM");
            dir.setCreateTime(new Date());
            this.save(dir);
        }else {
            SysMenu update = new SysMenu();
            update.setMenuId(dir.getMenuId());
            update.setSubCount(dir.getSubCount()+1);
            this.updateById(update);
        }
        // ??????
        SysMenu menu = this.getOne(new QueryWrapper<SysMenu>().eq(SysMenu.COL_TITLE, zhNames[1]));
        if (menu == null || !menu.getPid().equals(dir.getMenuId())){
            boolean isNull =  menu == null;
            menu = new SysMenu();
            menu.setPid(dir.getMenuId());
            menu.setSubCount(3);
            menu.setType(EhAdminConstant.MenuType.MENU.getValue());
            menu.setTitle(zhNames[1]+ (!isNull ? "[??????????????????pid?????????,?????????]" : ""));
            menu.setName(StringUtils.capitalize(enNames[1]));
            menu.setLocale("menu."+enNames[0]+ "." + enNames[1]);
            menu.setPermission(className+":list");
            menu.setOrder(0);
            menu.setIcon("icon-thumb-up-fill");
            menu.setIFrame(false);
            menu.setCache(false);
            menu.setHidden(false);
            menu.setCreateBy("SYSTEM");
            menu.setCreateTime(new Date());
            this.save(menu);
        }else {
            SysMenu update = new SysMenu();
            update.setMenuId(menu.getMenuId());
            update.setSubCount(menu.getSubCount() + 3);
            this.updateById(update);
        }

        // ??????
        SysMenu addButton = this.getOne(new QueryWrapper<SysMenu>().eq(SysMenu.COL_TITLE, "??????" + zhNames[1]));

        if (addButton == null || !addButton.getPid().equals(menu.getMenuId())){
            boolean isNull =  addButton == null;
            addButton = new SysMenu();
            addButton.setPid(menu.getMenuId());
            addButton.setSubCount(0);
            addButton.setType(EhAdminConstant.MenuType.BUTTON.getValue());
            addButton.setTitle("??????" + zhNames[1]  + (!isNull ? "[??????????????????pid?????????,?????????]" : ""));
            addButton.setOrder(0);
            addButton.setIFrame(false);
            addButton.setCache(false);
            addButton.setHidden(false);
            addButton.setPermission(className+":add");
            addButton.setCreateBy("SYSTEM");
            addButton.setCreateTime(new Date());
            this.save(addButton);
        }

        SysMenu editButton = this.getOne(new QueryWrapper<SysMenu>().eq(SysMenu.COL_TITLE, "??????" + zhNames[1]));
        if (editButton == null || !editButton.getPid().equals(menu.getMenuId())){
            boolean isNull =  editButton == null;
            editButton = new SysMenu();
            editButton.setPid(menu.getMenuId());
            editButton.setSubCount(0);
            editButton.setType(EhAdminConstant.MenuType.BUTTON.getValue());
            editButton.setTitle("??????" + zhNames[1]  + (!isNull ? "[??????????????????pid?????????,?????????]" : ""));
            editButton.setOrder(0);
            editButton.setIFrame(false);
            editButton.setCache(false);
            editButton.setHidden(false);
            editButton.setPermission(className+":edit");
            editButton.setCreateBy("SYSTEM");
            editButton.setCreateTime(new Date());
            this.save(editButton);
        }


        SysMenu delButton = this.getOne(new QueryWrapper<SysMenu>().eq(SysMenu.COL_TITLE, "??????" + zhNames[1]));
        if (delButton == null || !delButton.getPid().equals(menu.getMenuId())){
            boolean isNull =  delButton == null;
            delButton = new SysMenu();
            delButton.setPid(menu.getMenuId());
            delButton.setSubCount(0);
            delButton.setType(EhAdminConstant.MenuType.BUTTON.getValue());
            delButton.setTitle("??????" + zhNames[1]  + (!isNull ? "[??????????????????pid?????????,?????????]" : ""));
            delButton.setOrder(0);
            delButton.setIFrame(false);
            delButton.setCache(false);
            delButton.setHidden(false);
            delButton.setPermission(className+":del");
            delButton.setCreateBy("SYSTEM");
            delButton.setCreateTime(new Date());
            this.save(delButton);
        }


        // 2. ??????????????????????????????,roleId 1??????????????????
        Set<Long> menuIds = sysRolesMenusService.list(new QueryWrapper<SysRolesMenus>().eq(SysRolesMenus.COL_ROLE_ID, 1))
                .stream()
                .map(SysRolesMenus::getMenuId)
                .collect(Collectors.toSet());
        menuIds.add(dir.getMenuId());
        menuIds.add(menu.getMenuId());
        menuIds.add(addButton.getMenuId());
        menuIds.add(editButton.getMenuId());
        menuIds.add(delButton.getMenuId());
        UpdateRoleMenuVo updateRoleMenuVo = new UpdateRoleMenuVo();
        updateRoleMenuVo.setMenuIdList(new ArrayList<>(menuIds));
        updateRoleMenuVo.setRoleId(1L);
        sysRolesMenusService.updateRoleMenu(updateRoleMenuVo);
    }

    private List<SummaryMenuDto> getChildSummaryMenu(Long id, List<SummaryMenuDto> allMenus) {
        // ????????????
        List<SummaryMenuDto> res = allMenus
                .stream()
                .filter(t -> id.equals(t.getPid()))
                .collect(Collectors.toList());
        // ???????????????????????????
        for (SummaryMenuDto summaryMenuDto : res) {
            // ?????????????????????0
            if (!summaryMenuDto.getSubCount().equals(0)){
                List<SummaryMenuDto> childSummaryMenu = getChildSummaryMenu(summaryMenuDto.getId(), allMenus);
                childSummaryMenu.sort(Comparator.comparing(SummaryMenuDto::getOrder));
                summaryMenuDto.setChildren(childSummaryMenu);
            }
        }
        return  res;
    }

    private List<MenuDto> getChildMenu(Long menuId, List<SysMenu> sysMenuList) {
        List<MenuDto> childMenuDto = new ArrayList<>();

        for (SysMenu sysMenu : sysMenuList) {
            // 1.??????pid??????menuId ??? ??????????????????button???????????????
            if (menuId.equals(sysMenu.getPid()) && !sysMenu.getType().equals(EhAdminConstant.MenuType.BUTTON.getValue())){
                MenuDto menuDto = menuMapper.toMenuDto(sysMenu);
                // 2.??????????????????????????????
                List<MenuDto> childMenu = getChildMenu(sysMenu.getMenuId(), sysMenuList);
                childMenu.sort(Comparator.comparing((t) -> t.getMeta().getOrder()));
                menuDto.setChildren(childMenu);
                childMenuDto.add(menuDto);
            }

        }


        return childMenuDto;
    }


}
