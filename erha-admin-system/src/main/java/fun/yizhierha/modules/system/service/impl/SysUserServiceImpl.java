package fun.yizhierha.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.common.utils.file.ExcelUtils;
import fun.yizhierha.modules.system.domain.vo.UpdateUserVo;
import fun.yizhierha.modules.security.service.UserCacheManager;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.*;
import fun.yizhierha.modules.system.domain.vo.CreateUserVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveUserVo;
import fun.yizhierha.modules.system.service.*;
import fun.yizhierha.modules.system.service.dto.*;
import fun.yizhierha.modules.system.service.mapstruct.JobMapper;
import fun.yizhierha.modules.system.service.mapstruct.RoleMapper;
import fun.yizhierha.modules.system.service.mapstruct.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.modules.system.mapper.SysUserMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{

    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired(required = false)
    JobMapper jobMapper;
    @Autowired(required = false)
    RoleMapper roleMapper;

    @Autowired
    UserCacheManager cacheManager;
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    SysJobService sysJobService;
    @Autowired
    SysRolesDeptsService sysRolesDeptsService;
    @Autowired
    SysUsersRolesService sysUsersRolesService;
    @Autowired
    SysUsersJobsService sysUsersJobsService;

    @Override
    public UserDetailsDto selectUserDetailDtoByUsername(String username) {
        UserDto userDto = baseMapper.selectUserDtoByUsername(username);
        if (userDto == null) return null;
        // 所有角色
        Set<SysRole> roles =  sysRoleService.selectSysRoleByUserId(userDto.getUserId());
        Set<SysJob> jobs = sysJobService.selectSysJobByUserId(userDto.getUserId());
        List<GrantedAuthority> authorities;

        //找出当前角色权限
        SysRole nowRole = null;
        for (SysRole allRole : roles) {
            if (allRole.getRoleId().equals(userDto.getNowRoleId())){
                nowRole = allRole;
                break;
            }
        }

        if (nowRole == null){
            authorities = new ArrayList<GrantedAuthority>();
        }else {
            SysRole finalNowRole = nowRole;
            // roleId == 1的是超级管理员
            if (finalNowRole.getRoleId().equals(1L)){
                authorities = new ArrayList<GrantedAuthority>(){{add(new SimpleGrantedAuthority("admin"));}};
            }else {
                authorities = sysRoleService.mapRolesPermissionToAuthorities(new HashSet<SysRole>(){{add(finalNowRole);}},userDto.getUserId());
            }
        }
        Set<Long> dataScope = sysRolesDeptsService.getDataScopeByRoles(Collections.singletonList(nowRole),userDto);
        return new UserDetailsDto(userDto,dataScope,roles,jobs,nowRole,authorities);
    }




    @Override
    public PageUtils<SummaryUserDto> listUser(RetrieveUserVo retrieveUserVo, Query.PageVo pageVo) {
        if (retrieveUserVo.getDeptIds() != null && retrieveUserVo.getDeptIds().size() == 0){
            return new PageUtils<>(new ArrayList<>(),0,10,1);
        }

        IPage<UserDto> page = baseMapper.selectListByUserQueryVo(new Query<UserDto>().getPage(pageVo), retrieveUserVo);

        Page<SummaryUserDto> summaryUserDtoPage = userMapper.toSummaryUserDtoIPage(page);

        List<SummaryUserDto> summaryDtoList = summaryUserDtoPage.getRecords();

        if (summaryDtoList.isEmpty()) return new PageUtils<>(summaryUserDtoPage);

        List<Long> userIds = summaryDtoList
                .stream()
                .map(SummaryUserDto::getId)
                .collect(Collectors.toList());
        // 查jobs
        Set<JobDto> jobDtoSet = sysJobService.getJobDtoSetByUserIds(userIds);
        // 遍历一遍jobDtoSet 按照userId分类。
        HashMap<Long, Set<SysJob>> tempMap_ = new HashMap<>();
        for (JobDto jobDto : jobDtoSet) {
            Set<SysJob> set = tempMap_.get(jobDto.getUserId());
            if (set == null){
                HashSet<SysJob> sysJobs = new HashSet<>();
                sysJobs.add(jobDto);
                tempMap_.put(jobDto.getUserId(),sysJobs);
            }else {
                set.add(jobDto);
            }
        }

        // 注入对应user的role
        for (SummaryUserDto dto : summaryDtoList) {
            Set<SummaryUserDto.Job> jobs = jobMapper.toSummaryUserDtoJobSet(tempMap_.get(dto.getId()));
            dto.setJobs(jobs);
        }


        // 查roles
        Set<RoleDto> roleDtoSet = sysRoleService.getRoleDtoSetByUserIds(userIds);
        // 遍历一遍roleDtoSet 按照userId分类。
        HashMap<Long, Set<SysRole>> tempMap = new HashMap<>();
        for (RoleDto roleDto : roleDtoSet) {
            Set<SysRole> set = tempMap.get(roleDto.getUserId());
            if (set == null){
                HashSet<SysRole> sysRoles = new HashSet<>();
                sysRoles.add(roleDto);
                tempMap.put(roleDto.getUserId(),sysRoles);
            }else {
                set.add(roleDto);
            }
        }

        // 注入对应user的role
        for (SummaryUserDto dto : summaryDtoList) {
            Set<SummaryUserDto.Role> roles = roleMapper.toSummaryUserDtoSet(tempMap.get(dto.getId()));
            dto.setRoles(roles);
        }

        return new PageUtils<>(summaryUserDtoPage);
    }

    @Override
    @Transactional
    public void save(CreateUserVo createUserVo) {
        // 先查查username phone email 这三个有没有重复
        List<SysUser> list = this.list(new QueryWrapper<SysUser>()
                .eq(SysUser.COL_USERNAME, createUserVo.getUsername()));
        if (!list.isEmpty())throw new BadRequestException(BizCodeEnum.Client_Error_CRUD.getCode(), "用户名重复！");

        list = this.list(new QueryWrapper<SysUser>()
                .eq(SysUser.COL_PHONE, createUserVo.getPhone()));
        if (!list.isEmpty())throw new BadRequestException(BizCodeEnum.Client_Error_CRUD.getCode(), "电话号重复！");

        list = this.list(new QueryWrapper<SysUser>()
                .eq(SysUser.COL_EMAIL, createUserVo.getEmail()));
        if (!list.isEmpty())throw new BadRequestException(BizCodeEnum.Client_Error_CRUD.getCode(), "邮箱重复！");
        // 1.sys_user
        SysUser sysUser = userMapper.toSysUser(createUserVo);

        // 剩余属性 nowRoleId  updateTime createTime pwdResetTime updateBy isAdmin avatarPath avatarName
        sysUser.setNowRoleId(createUserVo.getRoleIds().get(0));
        sysUser.setCreateTime(new Date());
        sysUser.setIsAdmin(createUserVo.getRoleIds().contains(1L)); // userId = 1是admin角色
        this.save(sysUser);
        SysUser user = getOne(new QueryWrapper<SysUser>().eq(SysUser.COL_USERNAME, createUserVo.getUsername()));

        // 2.sys_user_roles
        ArrayList<SysUsersRoles> sysUsersRoles1 = new ArrayList<>();
        for (Long roleId : createUserVo.getRoleIds()) {
            SysUsersRoles sysUsersRoles = new SysUsersRoles();
            sysUsersRoles.setUserId(user.getUserId());
            sysUsersRoles.setRoleId(roleId);
            sysUsersRoles1.add(sysUsersRoles);
        }
        sysUsersRolesService.saveBatch(sysUsersRoles1);

        // 3.sys_user_jobs
        ArrayList<SysUsersJobs> sysUsersJobs = new ArrayList<>();
        for (Long jobId : createUserVo.getJobIds()) {
            SysUsersJobs sysUsersJobs1 = new SysUsersJobs();
            sysUsersJobs1.setUserId(user.getUserId());
            sysUsersJobs1.setJobId(jobId);
            sysUsersJobs.add(sysUsersJobs1);
        }
        sysUsersJobsService.saveBatch(sysUsersJobs);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized void editUser(ValidList<UpdateUserVo> updateUserVoList, List<BaseErrDto> res, UserDetailsDto currentUser) {
        ArrayList<SysUser> sysUsers = new ArrayList<>();
        for (UpdateUserVo updateUserVo : updateUserVoList) {
            SysUser toUpdateSysUser = new SysUser();
            toUpdateSysUser.setUserId(updateUserVo.getId());
            // phone
            String phone = updateUserVo.getPhone();
            if (phone != null){
                if (list(new QueryWrapper<SysUser>().eq(SysUser.COL_PHONE,phone)).size() > 0 ){
                    BaseErrDto dto = new BaseErrDto();
                    dto.setId(updateUserVo.getId());
                    dto.setErrorField("phone");
                    dto.setErrorVal(phone);
                    dto.setErrorMsg("电话号码重复！");
                    res.add(dto);
                    break;
                }else{
                    toUpdateSysUser.setPhone(phone);
                }
            }
            // email
            String email = updateUserVo.getEmail();
            if (email != null){
                if (list(new QueryWrapper<SysUser>().eq(SysUser.COL_EMAIL,email)).size() > 0 ){
                    BaseErrDto dto = new BaseErrDto();
                    dto.setId(updateUserVo.getId());
                    dto.setErrorField("email");
                    dto.setErrorVal(email);
                    dto.setErrorMsg("邮箱重复！");
                    res.add(dto);
                    break;
                }else{
                    toUpdateSysUser.setPhone(phone);
                }
            }

            // nickName
            String nickName = updateUserVo.getNickName();
            if (nickName != null){
                toUpdateSysUser.setNickName(nickName);
            }
            // gender
            String gender = updateUserVo.getGender();
            if (gender != null){
                toUpdateSysUser.setGender(gender);
            }
            // deptId
            Long deptId = updateUserVo.getDeptId();
            if (deptId != null){
                toUpdateSysUser.setDeptId(deptId);
            }
            // enabled
            Boolean enabled = updateUserVo.getEnabled();
            if (enabled != null){
                toUpdateSysUser.setEnabled(enabled);
            }
            // jobIds
            List<Long> jobIds = updateUserVo.getJobIds();
            if ( jobIds != null){
                //删除以前的数据
                sysUsersJobsService.remove(new QueryWrapper<SysUsersJobs>().eq(SysUsersJobs.COL_USER_ID, updateUserVo.getId()));
                //插入新的数据
                ArrayList<SysUsersJobs> usersJobs = new ArrayList<>();
                for (Long jobId : jobIds) {
                    SysUsersJobs sysUsersJobs = new SysUsersJobs();
                    sysUsersJobs.setUserId(updateUserVo.getId());
                    sysUsersJobs.setJobId(jobId);
                    usersJobs.add(sysUsersJobs);
                }
                sysUsersJobsService.saveBatch(usersJobs);

            }
            // roleIds
            List<Long> roleIds = updateUserVo.getRoleIds();
            if (roleIds != null){
                // 删除以前的数据
                sysUsersRolesService.remove(new QueryWrapper<SysUsersRoles>().eq(SysUsersRoles.COL_USER_ID, updateUserVo.getId()));
                // 修改用户当前角色id
                toUpdateSysUser.setNowRoleId(roleIds.get(0));
                // 插入新的数据
                ArrayList<SysUsersRoles> usersRoles = new ArrayList<>();
                for (Long roleId : roleIds) {
                    SysUsersRoles sysUsersRoles = new SysUsersRoles();
                    sysUsersRoles.setUserId(updateUserVo.getId());
                    sysUsersRoles.setRoleId(roleId);
                    usersRoles.add(sysUsersRoles);
                }
                sysUsersRolesService.saveBatch(usersRoles);

            }
            //updateBy,updateTime
            toUpdateSysUser.setUpdateBy(currentUser.getUsername());
            toUpdateSysUser.setUpdateTime(new Date());

            sysUsers.add(toUpdateSysUser);
            //清除缓存
            SysUser sysUser = getById(updateUserVo.getId());
            cacheManager.cleanCacheByUsername(sysUser.getUsername());
        }
        updateBatchById(sysUsers);
    }

    @Override
    public void download(HttpServletResponse response) throws IOException {
        Query.PageVo pageVo = new Query.PageVo();
        pageVo.setPageSize(500);
        List<SummaryUserDto> list = listUser(null, pageVo).getList();
        ExcelUtils.export(response,"用户信息表",list,SummaryUserDto.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeByIds(Collection<?> list) {
        // 删除role,job
        Set<Long> userIds = (Set<Long>) list;
        sysUsersRolesService.remove(new QueryWrapper<SysUsersRoles>().in(SysUsersRoles.COL_USER_ID,userIds));
        sysUsersJobsService.remove(new QueryWrapper<SysUsersJobs>().in(SysUsersJobs.COL_USER_ID,userIds));
        return super.removeByIds(list);
    }

}
