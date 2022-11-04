package fun.yizhierha.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.StringUtils;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.common.utils.enums.DataScopeEnum;
import fun.yizhierha.common.utils.file.ExcelUtils;
import fun.yizhierha.modules.security.service.UserCacheManager;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.*;
import fun.yizhierha.modules.system.domain.vo.CreateRoleVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveRoleVo;
import fun.yizhierha.modules.system.domain.vo.UpdateRoleVo;
import fun.yizhierha.modules.system.domain.vo.UpdateVo;
import fun.yizhierha.modules.system.service.*;
import fun.yizhierha.modules.system.service.dto.RoleDto;
import fun.yizhierha.modules.system.service.dto.SummaryRoleDto;
import fun.yizhierha.modules.system.service.mapstruct.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.modules.system.mapper.SysRoleMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService{



    @Autowired
    RoleMapper roleMapper;

    @Autowired
    SysMenuService menuService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysRolesDeptsService sysRolesDeptsService;
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    SysUsersRolesService sysUsersRolesService;
    @Autowired
    SysRolesMenusService sysRolesMenusService;
    @Autowired
    SysDeptService sysDeptService;


    @Autowired
    UserCacheManager userCacheManager;


    @Override
    public Set<SysRole> selectSysRoleByUserId(Long userId) {
        return baseMapper.selectSysRoleByUserId(userId);
    }

    //@Cacheable(key = "'auth'+#p1")
    @Override
    public List<GrantedAuthority> mapRolesPermissionToAuthorities(Set<SysRole> roles, Long userId) {
        if (roles == null) return null;

        List<Long> roleIds = roles.stream().map(SysRole::getRoleId).collect(Collectors.toList());
        List<SysMenu> menus = menuService.listByRoleIds(roleIds);

        if (menus == null) return null;

        Set<String> permission = menus.stream().map(SysMenu::getPermission)
                .filter((StringUtils::isNotBlank))
                .collect(Collectors.toSet());

        return permission.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void switchNowRole(UserDetailsDto currentUser, SysRole roleToBeChanged) {
        SysUser user = new SysUser();
        user.setNowRoleId(roleToBeChanged.getRoleId());
        // 数据库更改now_role_id
        sysUserService.update(user,new UpdateWrapper<SysUser>().eq(SysUser.COL_USER_ID,currentUser.getUser().getUserId()));

        // 删除缓存更改nowRole
        userCacheManager.cleanCacheByUsername(currentUser.getUsername());


        // 设置permission
        List<GrantedAuthority> authorities = sysRoleService.mapRolesPermissionToAuthorities(new HashSet<SysRole>() {{
            add(roleToBeChanged);
        }}, currentUser.getUser().getUserId());
        currentUser.setAuthorities(authorities);
    }

    @Override
    public Set<RoleDto> getRoleDtoSetByUserIds(List<Long> userIds) {
        return baseMapper.selectRoleDtoListByUserIds(userIds);
    }

    @Override
    public PageUtils<SummaryRoleDto> list(RetrieveRoleVo retrieveRoleVo, Query.PageVo pageVo) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();

        String name = retrieveRoleVo.getName();
        Timestamp startCreateTime = retrieveRoleVo.getStartCreateTime();
        Timestamp endCreateTime = retrieveRoleVo.getEndCreateTime();

        wrapper.nested(StringUtils.isNotBlank(name),(w)-> {
                w.like(SysRole.COL_NAME,name)
                        .or()
                        .like(SysRole.COL_DESCRIPTION,name);
        });

        wrapper.between(startCreateTime != null && endCreateTime != null
                ,SysRole.COL_CREATE_TIME,startCreateTime,endCreateTime);



        IPage<SysRole> iPage = baseMapper.selectPage(new Query<SysRole>().getPage(pageVo), wrapper);
        Page<SummaryRoleDto> res = roleMapper.toSummaryRoleDtoIPage(iPage);
        // 查自定义数据权限的部门id
        for (SummaryRoleDto record : res.getRecords()) {
            if (DataScopeEnum.find(record.getDataScope()) == DataScopeEnum.CUSTOMIZE){
                List<Long> collect = sysRolesDeptsService.list(new QueryWrapper<SysRolesDepts>().eq(SysRolesDepts.COL_ROLE_ID, record.getId()))
                        .stream()
                        .map(SysRolesDepts::getDeptId)
                        .collect(Collectors.toList());
                record.setDataPerm(collect);
            }
        }

        return new PageUtils<>(res);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(CreateRoleVo createRoleVo, UserDetailsDto currentUser) {
        // 查询有没有同名的角色
        SysRole sysRole = baseMapper.selectOne(new QueryWrapper<SysRole>().eq(SysRole.COL_NAME, createRoleVo.getName()));
        if (sysRole != null){
           throw new BadRequestException(BizCodeEnum.Client_Error_CRUD.getCode(),"存在同名的角色！");
        }

        SysRole toCreateRole = roleMapper.toSysRole(createRoleVo);
        toCreateRole.setCreateBy(currentUser.getUsername());
        toCreateRole.setCreateTime(new Date());

        // 1.存SysRole
        baseMapper.insert(toCreateRole);

        // 2.有deptIds则存
        if(createRoleVo.getDeptIds() != null){
            ArrayList<SysRolesDepts> rolesDepts = new ArrayList<>();
            for (Long deptId : createRoleVo.getDeptIds()) {
                SysRolesDepts sysRolesDepts = new SysRolesDepts();
                sysRolesDepts.setRoleId(toCreateRole.getRoleId());
                sysRolesDepts.setDeptId(deptId);
                rolesDepts.add(sysRolesDepts);
            }
            sysRolesDeptsService.saveBatch(rolesDepts);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized void editRole(ValidList<UpdateRoleVo> updateRoleVos, List<BaseErrDto> errDtos, UserDetailsDto currentUser) {
        // 1.过滤用户名重复的角色
        List<Long> list = new ArrayList<>();
        for (UpdateRoleVo updateRoleVo : updateRoleVos) {
            String roleName = updateRoleVo.getName();
            if (roleName == null) continue;

            SysRole sysRole = baseMapper.selectOne(new QueryWrapper<SysRole>().eq(SysRole.COL_NAME, roleName));
            if (sysRole != null){
                // (1)记录需要清除的roleId
               list.add(updateRoleVo.getId());
                // (2)写入错误原因
                BaseErrDto baseErrDto = new BaseErrDto();
                baseErrDto.setErrorField(SysRole.COL_NAME);
                baseErrDto.setErrorMsg("用户名重复");
                baseErrDto.setErrorVal(updateRoleVo.getName());
                baseErrDto.setId(updateRoleVo.getId());
                errDtos.add(baseErrDto);
            }
        }
        // 去除重名的roleVo
        for (Long l : list) {
            updateRoleVos.removeIf((t) -> t.getId().equals(l));
        }


        // 2. 修改
        List<UpdateRoleVo> customizeDataScopeVos = new ArrayList<>();
        List<Long> toRemoveRoleIdsInRoleDept = new ArrayList<>();
        List<SysRole> toUpdateSysRoles = new ArrayList<>();
        for (UpdateRoleVo updateRoleVo : updateRoleVos) {
            String dataScope = updateRoleVo.getDataScope();
            if (dataScope != null) {
                toRemoveRoleIdsInRoleDept.add(updateRoleVo.getId());
                DataScopeEnum dataScopeEnum = DataScopeEnum.find(dataScope);
                if (dataScopeEnum == DataScopeEnum.CUSTOMIZE) {
                    customizeDataScopeVos.add(updateRoleVo);
                }
            }else if (updateRoleVo.getDeptIds() != null){
                toRemoveRoleIdsInRoleDept.add(updateRoleVo.getId());
                customizeDataScopeVos.add(updateRoleVo);
            }

            SysRole toSysRole = roleMapper.toSysRole(updateRoleVo);
            toSysRole.setUpdateBy(currentUser.getUsername());
            toSysRole.setUpdateTime(new Date());
            toUpdateSysRoles.add(toSysRole);
        }

        // 2.1 如果修改数据权限:清除sys_roles_depts关于此角色对应部门的记录
        if (!toRemoveRoleIdsInRoleDept.isEmpty()){
            sysRolesDeptsService.remove(new QueryWrapper<SysRolesDepts>().in(SysRolesDepts.COL_ROLE_ID,toRemoveRoleIdsInRoleDept));
        }
        // 2.1.1 如果修改为:自定义 -> 添加sys_roles_depts新的记录
        List<SysRolesDepts> toInsertRolesDepts = new ArrayList<>();
        for (UpdateRoleVo roleVo : customizeDataScopeVos) {
            for (Long deptId : roleVo.getDeptIds()) {
                SysRolesDepts sysRolesDepts = new SysRolesDepts();
                sysRolesDepts.setDeptId(deptId);
                sysRolesDepts.setRoleId(roleVo.getId());
                toInsertRolesDepts.add(sysRolesDepts);
            }
        }
        if (!toInsertRolesDepts.isEmpty()) {
            sysRolesDeptsService.saveBatch(toInsertRolesDepts);
        }

        // 2.2 修改sys_role的基本信息
        if (!toUpdateSysRoles.isEmpty()) {
            updateBatchById(toUpdateSysRoles);
        }


        // 2.3 清除缓存中当前角色为修改中角色的用户缓存
        if (!updateRoleVos.isEmpty()) {
            List<UserDetailsDto> cacheUserDetailsDtos = userCacheManager.getAllCache();
            List<Long> changedRoleIds = updateRoleVos.stream()
                    .map(UpdateVo::getId)
                    .collect(Collectors.toList());
            for (UserDetailsDto userDetailsDto : cacheUserDetailsDtos) {
                if (changedRoleIds.contains(userDetailsDto.getNowRole().getRoleId())){
                    userCacheManager.cleanCacheByUsername(userDetailsDto.getUsername());
                }
            }
        }

    }


    @Transactional(rollbackFor = Exception.class)
    public void removeByIds(Set<Long> list) {
        // 1.判断sys_user_roles是否有要删除的roleId,如果有则抛出异常
        List<SysUsersRoles> usersRoles = sysUsersRolesService.list(new QueryWrapper<SysUsersRoles>().in(SysUsersRoles.COL_ROLE_ID, list));
        if (!usersRoles.isEmpty()){
            SysRole role = getOne(new QueryWrapper<SysRole>().eq(SysRole.COL_ROLE_ID, usersRoles.get(0).getRoleId()));
            throw new BadRequestException(BizCodeEnum.Client_Error_CRUD.getCode(),"角色["+role.getName()+"]正在被用户使用，请先解除角色绑定。");
        }
        // 2.删除
        // 2.1 删除sys_role
        remove(new QueryWrapper<SysRole>().in(SysRole.COL_ROLE_ID,list));
        // 2.2 删除sys_roles_depts
        sysRolesDeptsService.remove(new QueryWrapper<SysRolesDepts>().in(SysRolesDepts.COL_ROLE_ID,list));
        // 2.3 删除sys_roles_menus
        sysRolesMenusService.remove(new QueryWrapper<SysRolesMenus>().in(SysRolesMenus.COL_ROLE_ID,list));
    }

    @Override
    public void download(HttpServletResponse response) {
        List<SummaryRoleDto> summaryRoleDtos = roleMapper.toSummaryRoleDto(list());
        // 查对应部门
        for (SummaryRoleDto roleDto : summaryRoleDtos) {
            DataScopeEnum dataScope = DataScopeEnum.find(roleDto.getDataScope());
            if (dataScope == DataScopeEnum.CUSTOMIZE){
                String[] deptNames = sysDeptService.getNameByRoleId(roleDto.getId());
                StringBuilder sb = new StringBuilder("");
                for (String deptName : deptNames) {
                    sb.append(deptName).append(" ");
                }
                roleDto.setDataPermStr(sb.toString());
            }else {
                roleDto.setDataPermStr("-");
            }
        }
        ExcelUtils.export(response,"角色信息表",summaryRoleDtos,SummaryRoleDto.class);
    }
}
