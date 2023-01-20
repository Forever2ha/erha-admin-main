package fun.yizhierha.modules.system.controller;

import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.common.utils.enums.DataScopeEnum;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysRole;
import fun.yizhierha.modules.system.domain.vo.CreateRoleVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveRoleVo;
import fun.yizhierha.modules.system.domain.vo.UpdateRoleVo;
import fun.yizhierha.modules.system.service.SysRoleService;
import fun.yizhierha.modules.system.service.dto.SummaryRoleDto;
import fun.yizhierha.common.utils.ValidUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Api(tags = "系统:角色")
@RestController
@RequestMapping("/api/system/role")
public class RoleController {

    @Autowired
    SysRoleService sysRoleService;

    @ApiOperation("角色列表")
    @Log("角色列表")
    @GetMapping
    @PreAuthorize("@eh.check('system:role:list')")
    public R<PageUtils<SummaryRoleDto>> list(RetrieveRoleVo retrieveRoleVo, Query.PageVo pageVo){
        return R.<PageUtils<SummaryRoleDto>>ok().setData(sysRoleService.list(retrieveRoleVo,pageVo));

    }

    @ApiOperation("全部角色")
    @Log("全部角色")
    @GetMapping("/all")
    @PreAuthorize("@eh.check('system:role:list')")
    public R<PageUtils<SysRole>> listAll(){
        List<SysRole> list = sysRoleService.list();
        PageUtils<SysRole> pageUtils = new PageUtils<SysRole>(list,list.size(),9999,1);
        return R.<PageUtils<SysRole>>ok().setData(pageUtils);
    }

    @ApiOperation("添加角色")
    @Log("添加角色")
    @PostMapping
    @PreAuthorize("@eh.check('system:role:add')")
    public R<List<BaseErrDto>> addRole(@RequestBody @Validated CreateRoleVo createRoleVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.<List<BaseErrDto>>error().setData(ValidUtils.getBaseErrDtoByBindingRes(bindingResult));
        }
        DataScopeEnum dataScopeEnum = DataScopeEnum.find(createRoleVo.getDataScope());
        if (dataScopeEnum == null){
            // 检测：dataScope是否存在
            return R.error("没有对应的dataScope");
        }else {
            // 检测：dataScope为"自定义" 则必须存在deptIds
            if (dataScopeEnum == DataScopeEnum.CUSTOMIZE){
                if (createRoleVo.getDeptIds() == null || createRoleVo.getDeptIds().isEmpty()){
                    return R.error(BizCodeEnum.Client_Error_CRUD.getCode(),"dataScope为'自定义时deptIds必须不为空!'");
                }
            }
        }

        // 检测当前用户是否有权限创建此level的角色
        UserDetailsDto currentUser = (UserDetailsDto) SecurityUtils.getCurrentUser();
        if (currentUser.getNowRole().getLevel() > createRoleVo.getLevel()){
            return R.error(BizCodeEnum.Client_Error_CRUD.getCode(), "您当前的角色等级不足以创建此等级的角色!");
        }

        // 添加角色
       sysRoleService.save(createRoleVo,currentUser);
       return R.ok();

    }

    @ApiOperation("修改角色")
    @Log("修改角色")
    @PutMapping
    @PreAuthorize("@eh.check('system:role:edit')")
    public R<List<BaseErrDto>> editRole(@RequestBody @Validated ValidList<UpdateRoleVo> updateRoleVos,BindingResult bindingResult){
        UserDetailsDto currentUser = (UserDetailsDto) SecurityUtils.getCurrentUser();

        // 1.获取Valid校验的错误
        List<BaseErrDto> errDtos = ValidUtils.getBaseErrDtoByBindingRes(updateRoleVos, bindingResult);
        // 2.如果有角色等级，判断是否有权限更改此角色等级 && 判断DataScope是否存在
        List<Long> longList = new ArrayList<>();
        for (UpdateRoleVo roleVo : updateRoleVos) {
            if (roleVo.getLevel() != null && currentUser.getNowRole().getLevel() > roleVo.getLevel()){
                // (1)记录不合法的roleId,等下移除
                longList.add(roleVo.getId());
                // (2)写入errDtos
                BaseErrDto baseErrDto = new BaseErrDto();
                baseErrDto.setErrorVal(roleVo.getLevel());
                baseErrDto.setErrorMsg("当前用户无权限修改此角色的等级");
                baseErrDto.setId(roleVo.getId());
                baseErrDto.setErrorField(SysRole.COL_LEVEL);
                errDtos.add(baseErrDto);
            }

            if (roleVo.getDataScope() != null) {
                if (DataScopeEnum.find(roleVo.getDataScope()) == null){
                    // (1)记录不合法的roleId,等下移除
                    longList.add(roleVo.getId());
                    // (2)写入errDtos
                    BaseErrDto baseErrDto = new BaseErrDto();
                    baseErrDto.setErrorVal(roleVo.getDataScope());
                    baseErrDto.setErrorMsg("无此种数据权限");
                    baseErrDto.setErrorField(SysRole.COL_DATA_SCOPE);
                    errDtos.add(baseErrDto);
                }
            }

            if (roleVo.getId().equals(1L)){
                // (1)记录不合法的roleId,等下移除
                longList.add(roleVo.getId());
                // (2)写入errDtos
                BaseErrDto baseErrDto = new BaseErrDto();
                baseErrDto.setErrorVal("---");
                baseErrDto.setErrorMsg("演示情况无法修改超级管理员");
                baseErrDto.setErrorField("id");
                baseErrDto.setId(roleVo.getId());
                errDtos.add(baseErrDto);
            }
            if (roleVo.getId().equals(2L)){
                // (1)记录不合法的roleId,等下移除
                longList.add(roleVo.getId());
                // (2)写入errDtos
                BaseErrDto baseErrDto = new BaseErrDto();
                baseErrDto.setId(roleVo.getId());
                baseErrDto.setErrorVal("---");
                baseErrDto.setErrorMsg("演示情况无法修改普通用户");
                baseErrDto.setErrorField("id");
                errDtos.add(baseErrDto);
            }
        }
        // (3)去除不合法的roleVo
        for (Long roleId : longList) {
            updateRoleVos.removeIf((t) -> roleId.equals(t.getId()));
        }

        // 3.交给roleService，判断角色名重复修改数据
        sysRoleService.editRole(updateRoleVos,errDtos, currentUser);

        // 4.无错返回ok
        if (errDtos.isEmpty()){
            return R.ok();
        }
        // 5. 有错返回错误信息
        return R.<List<BaseErrDto>>error
                (BizCodeEnum.Client_Error_CRUD.getCode(),BizCodeEnum.Client_Error_CRUD.getMsg()).setData(errDtos);
    }

    @ApiOperation("删除角色")
    @Log("删除角色")
    @DeleteMapping
    @PreAuthorize("@eh.check('system:role:del')")
    public R deleteRole(@RequestBody Set<Long> roleIds){
        sysRoleService.removeByIds(roleIds);
        return R.ok();
    }

    @ApiOperation("导出数据")
    @Log("导出数据")
    @GetMapping("/download")
    @PreAuthorize("@eh.check('system:role:list')")
    public void download(HttpServletResponse response){
        sysRoleService.download(response);
    }
}
