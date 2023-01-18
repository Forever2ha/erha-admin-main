package fun.yizhierha.modules.system.controller;

import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.modules.system.domain.vo.UpdateNowUserVo;
import fun.yizhierha.modules.system.domain.vo.UpdateUserVo;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysRole;
import fun.yizhierha.modules.system.domain.vo.CreateUserVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveUserVo;
import fun.yizhierha.modules.system.service.SysDeptService;
import fun.yizhierha.modules.system.service.SysRoleService;
import fun.yizhierha.modules.system.service.SysUserService;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.modules.system.service.dto.SummaryUserDto;
import fun.yizhierha.common.utils.ValidUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Api(tags = "系统:用户")
@RestController
@RequestMapping("/api/system/user")
public class
UserController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysRoleService roleService;
    @Autowired
    SysDeptService sysDeptService;

    @ApiOperation(value = "获取当前用户信息")
    @Log("获取当前用户信息")
    @GetMapping("/info")
    public R<UserDetailsDto> info() {
        return R.<UserDetailsDto>ok().setData((UserDetailsDto) SecurityUtils.getCurrentUser());
    }

    @ApiOperation(value = "修改当前用户头像")
    @Log("修改当前用户头像")
    @PostMapping("/updateAvatar")
    public R<Map<String,String>> updateAvatar(@RequestParam MultipartFile avatar){
        return R.<Map<String,String>>ok().setData(sysUserService.updateAvatar(avatar));
    }

    @ApiOperation(value = "修改当前用户信息")
    @Log("修改当前用户信息")
    @PutMapping("/update")
    public R updateNowUser(@RequestBody @Validated UpdateNowUserVo nowUserVo,BindingResult bindingResult){

        List<BaseErrDto> baseErrorList = ValidUtils.getBaseErrDtoByBindingRes(bindingResult);
        ValidList<UpdateUserVo> updateUserVos = new ValidList<>();
        UserDetailsDto currentUser = (UserDetailsDto) SecurityUtils.getCurrentUser();
        UpdateUserVo updateUserVo = new UpdateUserVo();
        updateUserVo.setId(currentUser.getUser().getUserId());
        updateUserVo.setEmail(nowUserVo.getEmail());
        updateUserVo.setNickName(nowUserVo.getNickName());
        updateUserVo.setPhone(nowUserVo.getPhone());

        updateUserVos.add(updateUserVo);
        sysUserService.editUser(updateUserVos,baseErrorList, currentUser);
        if (baseErrorList.isEmpty()){
            return R.ok();
        }else {
            return R.error(BizCodeEnum.Client_Error_CRUD.getCode(),
                    BizCodeEnum.Client_Error_CRUD.getMsg()
                    ).setData(baseErrorList);
        }

    }

    @ApiOperation("切换当前角色")
    @Log("切换当前角色")
    @PostMapping("/switchRoles")
    public R switchRoles(@RequestBody Map<String,Object> param){
        Object roleName = param.get("name");
        if (roleName == null) throw new BadRequestException("role名字不能为空");

        UserDetailsDto currentUser = (UserDetailsDto) SecurityUtils.getCurrentUser();
        Set<SysRole> roles = currentUser.getRoles()
                .stream()
                .filter((sysRole -> sysRole.getName().equals(roleName)))
                .collect(Collectors.toSet());
        if (roles.isEmpty()) throw new BadRequestException("你没有这个角色！");

        roleService.switchNowRole(currentUser,(SysRole) roles.toArray()[0]);

        return R.ok();
    }

    @ApiOperation("用户列表")
    @Log("用户列表")
    @GetMapping()
    @PreAuthorize("@eh.check('system:user:list')")
    public R<PageUtils<SummaryUserDto>> list(RetrieveUserVo retrieveUserVo, Query.PageVo pageVo){
        UserDetailsDto currentUser = (UserDetailsDto) SecurityUtils.getCurrentUser();
        Set<Long> scope = currentUser.getDataScope();

        // 处理前端传来的部门数据，需要查出该部门及其子部门的deptId
        if (retrieveUserVo.getDeptIds() != null){
            Set<Long> deptIds = sysDeptService.listForDeptIdsByDeptId(retrieveUserVo.getDeptIds().iterator().next());
            retrieveUserVo.getDeptIds().addAll(deptIds);
        }

        // 数据权限判断
        if (!scope.contains(0L) && retrieveUserVo.getDeptIds() != null){
            // 没有拥有全部的数据权限,但有deptId查询条件,取交集
            retrieveUserVo.setDeptIds(
                    retrieveUserVo.getDeptIds()
                            .stream()
                            .filter(scope::contains)
                            .collect(Collectors.toSet())
            );
        }else if (!scope.contains(0L) && retrieveUserVo.getDeptIds() == null){
            // 没有拥有全部的数据权限,但没有deptId查询条件,把数据权限中的deptId添加到查询条件中
            retrieveUserVo.setDeptIds(new HashSet<Long>(){{addAll(scope);}});
        }

        PageUtils<SummaryUserDto> page = sysUserService.listUser(retrieveUserVo,pageVo);
        return R.<PageUtils<SummaryUserDto>>ok().setData(page);
    }

    @PostMapping
    @PreAuthorize("@eh.check('system:user:add')")
    @ApiOperation("新增用户")
    @Log("新增用户")
    public R createUser(@Validated @RequestBody CreateUserVo createUserVo){
        UserDetailsDto currentUser = (UserDetailsDto) SecurityUtils.getCurrentUser();
        if (!checkLevel(createUserVo.getRoleIds(),currentUser)){
            return R.error(BizCodeEnum.Client_Error_CRUD.getCode(), "无法创建比自己角色等级高的用户");
        }

        createUserVo.setPassword(passwordEncoder.encode("123456"));
        createUserVo.setCreateBy(currentUser.getUsername());
        sysUserService.save(createUserVo);
        return R.ok();
    }

    @PutMapping
    @PreAuthorize("@eh.check('system:user:edit')")
    @ApiOperation("修改用户")
    @Log("修改用户")
    public R<List<BaseErrDto>> editUser(@Validated @RequestBody ValidList<UpdateUserVo> updateUserVoList, BindingResult bindingResult){
        UserDetailsDto currentUser = (UserDetailsDto) SecurityUtils.getCurrentUser();

        // 先处理一下validList检测出来的错误
        List<BaseErrDto> res = ValidUtils.getBaseErrDtoByBindingRes(updateUserVoList,bindingResult);

        // 增强for循环中不能删除集合中的元素，否则报错，所以复制一个列表
        ValidList<UpdateUserVo> tempUpdateUserVos = new ValidList<>();
        tempUpdateUserVos.addAll(updateUserVoList);

        // 1）检查role level 2) 检查角色不为空数组 3)检查职位不为空数组
        // 把editUserVo不符合的剔除掉
        // 收集所有roleIds;处理roleIds和jobsIds为空列表的情况
        List<Long> roleIds = new ArrayList<>();
        for (UpdateUserVo updateUserVo : tempUpdateUserVos) {
            List<Long> roleIds1 = updateUserVo.getRoleIds();
            List<Long> jobIds = updateUserVo.getJobIds();

            if (roleIds1 != null){
                // 2) 检查角色不为空数组
                if (roleIds1.isEmpty()){
                    // role列表为空，不能更改
                    // 1.去除editUserVoList的这个元素
                    updateUserVoList.removeIf((t) -> t.getId().equals(updateUserVo.getId()));
                    // 2.创建EditUserResDto放入结果集
                    BaseErrDto baseErrDto = new BaseErrDto();
                    baseErrDto.setId(updateUserVo.getId());
                    baseErrDto.setErrorVal("---");
                    baseErrDto.setErrorField("roles");
                    baseErrDto.setErrorMsg("角色不能为空");
                    res.add(baseErrDto);
                }else{
                    roleIds.addAll(roleIds1);
                }
            }

            // 3)检查职位不为空数组
            if (jobIds != null && jobIds.isEmpty()){

                // job列表为空，不能更改
                // 1.去除editUserVoList的这个元素
                updateUserVoList.removeIf((t) -> t.getId().equals(updateUserVo.getId()));
                // 2.创建EditUserResDto放入结果集
                BaseErrDto baseErrDto = new BaseErrDto();
                baseErrDto.setId(updateUserVo.getId());
                baseErrDto.setErrorVal("---");
                baseErrDto.setErrorField("jobs");
                baseErrDto.setErrorMsg("岗位不能为空");
                res.add(baseErrDto);
            }
        }
        // 处理不合法的role level
        if (!roleIds.isEmpty()){
            List<SysRole> sysRoles = roleService.listByIds(roleIds);
            for (UpdateUserVo updateUserVo : tempUpdateUserVos) {
                List<Long> roleIds1 = updateUserVo.getRoleIds();
                List<Long> jobIds = updateUserVo.getJobIds();
                // 1) 检查role level
                if (!roleIds1.isEmpty()){
                    if (!checkLevel(roleIds1,currentUser,sysRoles)){
                        // 等级异常，不能更改
                        // 1.去除editUserVoList的这个元素
                        updateUserVoList.removeIf((t) -> t.getId().equals(updateUserVo.getId()));
                        // 2.创建EditUserResDto放入结果集
                        BaseErrDto baseErrDto = new BaseErrDto();
                        baseErrDto.setId(updateUserVo.getId());
                        baseErrDto.setErrorVal("---");
                        baseErrDto.setErrorField("角色等级");
                        baseErrDto.setErrorMsg("权限不足，无法修改此用户的等级");
                        res.add(baseErrDto);
                    }
                }

            }
        }

        if (updateUserVoList.isEmpty()){
            return R.<List<BaseErrDto>>error(BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg()).setData(res);
        }

        // 去改
        sysUserService.editUser(updateUserVoList,res,currentUser);
        if (res.isEmpty()){
            return R.ok();
        }
        return R.<List<BaseErrDto>>error(BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg()).setData(res);
    }


    @DeleteMapping
    @PreAuthorize("@eh.check('system:user:del')")
    @ApiOperation("删除用户")
    @Log("删除用户")
    public R deleteUser(@RequestBody Set<Long> userIds){
        sysUserService.removeByIds(userIds);
        return R.ok();
    }

    @GetMapping("/download")
    @PreAuthorize("@eh.check('system:user:list')")
    @ApiOperation("导出数据")
    @Log("导出数据")
    public void download(HttpServletResponse response) throws IOException {
        sysUserService.download(response);
    }

    private boolean checkLevel(List<Long> roleIds, UserDetailsDto currentUser) {

        Integer nowLevel = currentUser.getNowRole().getLevel();
        List<SysRole> roles = roleService.listByIds(roleIds);
        for (SysRole role : roles) {
            if (role.getLevel() < nowLevel){
                return false;
            }
        }
        return true;
    }
    private boolean checkLevel(List<Long> roleIds, UserDetailsDto currentUser,List<SysRole> roles) {
        Integer nowLevel = currentUser.getNowRole().getLevel();
        for (SysRole role : roles) {
            if (role.getLevel() < nowLevel){
                return false;
            }
        }
        return true;
    }
}
