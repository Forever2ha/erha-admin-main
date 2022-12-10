package fun.yizhierha.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.R;
import fun.yizhierha.modules.system.domain.SysRolesMenus;
import fun.yizhierha.modules.system.domain.vo.UpdateRoleMenuVo;
import fun.yizhierha.modules.system.service.SysRolesMenusService;
import fun.yizhierha.common.utils.ValidUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "系统：角色菜单")
@RequestMapping("/api/roleMenu")
public class RoleMenuController {

    @Autowired
    SysRolesMenusService sysRolesMenusService;

    @GetMapping("/menuIds")
    @PreAuthorize("@eh.check('role:list')")
    @ApiOperation("根据角色id查询菜单id")
    @Log("根据角色id查询菜单id")
    public R<List<Long>> getMenuIdsByRoleId(@Param("roleId") Long roleId){
        if (roleId == null){
            return R.error(
                    BizCodeEnum.Client_Error_CRUD.getCode(),
                    "roleId不能为空!"
            );
        }
        List<Long> collect = sysRolesMenusService.list(new QueryWrapper<SysRolesMenus>().eq(SysRolesMenus.COL_ROLE_ID, roleId))
                .stream()
                .map(SysRolesMenus::getMenuId)
                .collect(Collectors.toList());
        return R.<List<Long>>ok().setData(collect);
    }

    @PutMapping
    @PreAuthorize("@eh.check('role:edit')")
    @ApiOperation("修改角色菜单")
    @Log("修改角色菜单")
    public R editRoleMenu(@RequestBody @Validated UpdateRoleMenuVo updateRoleMenuVo, BindingResult bindingResult){
        List<BaseErrDto> errDtos = ValidUtils.getBaseErrDtoByBindingRes(bindingResult);
        if (!errDtos.isEmpty()){
            return R.<List<BaseErrDto>>error(
                    BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg())
                    .setData(errDtos)
                    ;
        }
        sysRolesMenusService.updateRoleMenu(updateRoleMenuVo);
        return  R.ok();
    }
}
