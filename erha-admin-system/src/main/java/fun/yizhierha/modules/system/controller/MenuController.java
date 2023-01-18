package fun.yizhierha.modules.system.controller;

import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.vo.CreateMenuVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveMenuVo;
import fun.yizhierha.modules.system.domain.vo.UpdateMenuVo;
import fun.yizhierha.modules.system.service.SysMenuService;
import fun.yizhierha.modules.system.service.dto.MenuDto;
import fun.yizhierha.modules.system.service.dto.SummaryMenuDto;
import fun.yizhierha.common.utils.ValidUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@Api(tags = "系统:菜单")
@RestController
@RequestMapping("/api/system/menu")
public class MenuController {

    @Autowired
    SysMenuService sysMenuService;

    @ApiOperation("获取当前角色的菜单")
    @Log("获取当前角色的菜单")
    @GetMapping("/nowUser")
    public R<List<MenuDto>> retrieve(){
        List<MenuDto> menuDto = sysMenuService.listMenu(((UserDetailsDto) SecurityUtils.getCurrentUser()));
        return R.<List<MenuDto>>ok().setData(menuDto);
    }

    @ApiOperation("获取菜单")
    @Log("获取菜单")
    @GetMapping
    @PreAuthorize("@eh.check('system:menu:list')")
    public R<PageUtils<SummaryMenuDto>> list(RetrieveMenuVo retrieveMenuVo, Query.PageVo pageVo){
        return R.<PageUtils<SummaryMenuDto>>ok().setData(sysMenuService.listTree(retrieveMenuVo,pageVo));
    }

    @ApiOperation("添加菜单")
    @Log("添加菜单")
    @PostMapping
    @PreAuthorize("@eh.check('system:menu:add')")
    public R<List<BaseErrDto>> addMenu(@Validated @RequestBody CreateMenuVo createMenuVo, BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(bindingResult);
        if (errDtoList.isEmpty()){
            sysMenuService.saveMenu(createMenuVo, ((UserDetailsDto) SecurityUtils.getCurrentUser()));
            return R.ok();
        }
        return R.<List<BaseErrDto>>error(
                BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg()
        ).setData(errDtoList);
    }

    @ApiOperation("修改菜单")
    @Log("修改菜单")
    @PutMapping
    @PreAuthorize("@eh.check('system:menu:edit')")
    public R<List<BaseErrDto>> editMenu(@Validated @RequestBody ValidList<UpdateMenuVo> updateMenuVoList,BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(updateMenuVoList, bindingResult);
        if (errDtoList.isEmpty()) {
            sysMenuService.editMenu(updateMenuVoList,errDtoList, ((UserDetailsDto) SecurityUtils.getCurrentUser()));
            if (errDtoList.isEmpty()){
                return R.ok();
            }else {
                return  R.<List<BaseErrDto>>error(
                        BizCodeEnum.Client_Error_CRUD.getCode(),
                        BizCodeEnum.Client_Error_CRUD.getMsg()
                ).setData(errDtoList);
            }
        }
        return  R.<List<BaseErrDto>>error(
                BizCodeEnum.Client_Error_CRUD.getCode(),
                BizCodeEnum.Client_Error_CRUD.getMsg()
        ).setData(errDtoList);
    }

    @ApiOperation("删除菜单")
    @Log("删除菜单")
    @DeleteMapping
    @PreAuthorize("@eh.check('system:menu:del')")
    public R delDict(@RequestBody Set<Long> menuIds){
        sysMenuService.removeMenu(menuIds);
        return R.ok();
    }

    @ApiOperation("导出数据")
    @Log("导出数据")
    @GetMapping("/download")
    @PreAuthorize("@eh.check('system:menu:list')")
    public void download(HttpServletResponse response){
        sysMenuService.download(response);
    }

    @ApiOperation("代码生成自动生成菜单")
    @Log("代码生成自动生成菜单")
    @PostMapping("/generate/{tableName}")
    @PreAuthorize("@eh.check('system:menu:edit')")
    public R generateMenu(@PathVariable String tableName){
        sysMenuService.generateMenu(tableName);
        return R.ok();
    }

}
