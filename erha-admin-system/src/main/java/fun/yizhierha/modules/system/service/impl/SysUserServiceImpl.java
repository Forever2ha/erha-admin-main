package fun.yizhierha.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.config.FileProperties;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.common.utils.file.ExcelUtils;
import fun.yizhierha.common.utils.file.FileUtil;
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
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.modules.system.mapper.SysUserMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    FileProperties fileProperties;

    @Override
    public UserDetailsDto selectUserDetailDtoByUsername(String username) {
        UserDto userDto = baseMapper.selectUserDtoByUsername(username);
        if (userDto == null) return null;
        // ????????????
        Set<SysRole> roles =  sysRoleService.selectSysRoleByUserId(userDto.getUserId());
        Set<SysJob> jobs = sysJobService.selectSysJobByUserId(userDto.getUserId());
        List<GrantedAuthority> authorities;

        //????????????????????????
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
            // roleId == 1?????????????????????
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
        // ???jobs
        Set<JobDto> jobDtoSet = sysJobService.getJobDtoSetByUserIds(userIds);
        // ????????????jobDtoSet ??????userId?????????
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

        // ????????????user???role
        for (SummaryUserDto dto : summaryDtoList) {
            Set<SummaryUserDto.Job> jobs = jobMapper.toSummaryUserDtoJobSet(tempMap_.get(dto.getId()));
            dto.setJobs(jobs);
        }


        // ???roles
        Set<RoleDto> roleDtoSet = sysRoleService.getRoleDtoSetByUserIds(userIds);
        // ????????????roleDtoSet ??????userId?????????
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

        // ????????????user???role
        for (SummaryUserDto dto : summaryDtoList) {
            Set<SummaryUserDto.Role> roles = roleMapper.toSummaryUserDtoSet(tempMap.get(dto.getId()));
            dto.setRoles(roles);
        }

        return new PageUtils<>(summaryUserDtoPage);
    }

    @Override
    @Transactional
    public void save(CreateUserVo createUserVo) {
        // ?????????username phone email ????????????????????????
        List<SysUser> list = this.list(new QueryWrapper<SysUser>()
                .eq(SysUser.COL_USERNAME, createUserVo.getUsername()));
        if (!list.isEmpty())throw new BadRequestException(BizCodeEnum.Client_Error_CRUD.getCode(), "??????????????????");

        list = this.list(new QueryWrapper<SysUser>()
                .eq(SysUser.COL_PHONE, createUserVo.getPhone()));
        if (!list.isEmpty())throw new BadRequestException(BizCodeEnum.Client_Error_CRUD.getCode(), "??????????????????");

        list = this.list(new QueryWrapper<SysUser>()
                .eq(SysUser.COL_EMAIL, createUserVo.getEmail()));
        if (!list.isEmpty())throw new BadRequestException(BizCodeEnum.Client_Error_CRUD.getCode(), "???????????????");
        // 1.sys_user
        SysUser sysUser = userMapper.toSysUser(createUserVo);

        // ???????????? nowRoleId  updateTime createTime pwdResetTime updateBy isAdmin avatarPath avatarName
        sysUser.setNowRoleId(createUserVo.getRoleIds().get(0));
        sysUser.setCreateTime(new Date());
        sysUser.setIsAdmin(createUserVo.getRoleIds().contains(1L)); // userId = 1???admin??????
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
                    dto.setErrorMsg("?????????????????????");
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
                    dto.setErrorMsg("???????????????");
                    res.add(dto);
                    break;
                }else{
                    toUpdateSysUser.setEmail(email);
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
                //?????????????????????
                sysUsersJobsService.remove(new QueryWrapper<SysUsersJobs>().eq(SysUsersJobs.COL_USER_ID, updateUserVo.getId()));
                //??????????????????
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
                // ?????????????????????
                sysUsersRolesService.remove(new QueryWrapper<SysUsersRoles>().eq(SysUsersRoles.COL_USER_ID, updateUserVo.getId()));
                // ????????????????????????id
                toUpdateSysUser.setNowRoleId(roleIds.get(0));
                // ??????????????????
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
            //????????????
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
        ExcelUtils.export(response,"???????????????",list,SummaryUserDto.class);
    }

    @Override
    public Map<String, String> updateAvatar(MultipartFile multipartFile) {
        // ??????????????????
        FileUtil.checkSize(fileProperties.getAvatarMaxSize(), multipartFile.getSize());
        // ???????????????????????????
        String image = "gif jpg png jpeg";
        String fileType = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        if(fileType != null && !image.contains(fileType)){
            throw new BadRequestException("?????????????????????, ????????? " + image +" ??????");
        }

        UserDto user = baseMapper.selectUserDtoByUsername(SecurityUtils.getCurrentUser().getUsername());
        SysUser sysUser = new SysUser();
        sysUser.setUserId(user.getUserId());
        String oldPath = user.getAvatarPath();
        File file = FileUtil.upload(multipartFile, fileProperties.getPath().getAvatar());
        sysUser.setAvatarPath(Objects.requireNonNull(file).getPath());
        sysUser.setAvatarName(file.getName());

        baseMapper.updateById(sysUser);

        if (StringUtils.isNotBlank(oldPath)) {
            FileUtil.del(oldPath);
        }

        // ????????????
        cacheManager.cleanCacheByUsername(user.getUsername());
        return new HashMap<String, String>(1) {{
            put("avatar", file.getName());
        }};
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeByIds(Collection<?> list) {
        // ??????role,job
        Set<Long> userIds = (Set<Long>) list;
        sysUsersRolesService.remove(new QueryWrapper<SysUsersRoles>().in(SysUsersRoles.COL_USER_ID,userIds));
        sysUsersJobsService.remove(new QueryWrapper<SysUsersJobs>().in(SysUsersJobs.COL_USER_ID,userIds));
        return super.removeByIds(list);
    }

}
