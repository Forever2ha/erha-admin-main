package fun.yizhierha.modules.system.controller;

import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysDept;
import fun.yizhierha.modules.system.domain.vo.CreateDeptVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveDeptVo;
import fun.yizhierha.modules.system.domain.vo.UpdateDeptVo;
import fun.yizhierha.modules.system.service.SysDeptService;
import fun.yizhierha.modules.system.service.dto.SummaryDeptDto;
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

@Api(tags = "系统:部门")
@RestController
@RequestMapping("/api/dept")
public class DeptController {

    @Autowired
    SysDeptService sysDeptService;

    @ApiOperation("部门树")
    @Log("部门树")
    @GetMapping("/listTree")
    @PreAuthorize("@eh.check('dept:list')")
    public R<List<Object>> list(){
        List<Object> res = sysDeptService.listForTree();
        return R.<List<Object>>ok().setData(res);
    }

    @ApiOperation("获取部门")
    @Log("获取部门")
    @GetMapping
    @PreAuthorize("@eh.check('dept:list')")
    public R<PageUtils<SummaryDeptDto>> getDept(RetrieveDeptVo retrieveDeptVo, Query.PageVo pageVo){

        PageUtils<SummaryDeptDto> pageUtils = sysDeptService.listDept(retrieveDeptVo,pageVo);
        return R.<PageUtils<SummaryDeptDto>>ok().setData(pageUtils);
    }

    @ApiOperation("添加部门")
    @Log("添加部门")
    @PostMapping
    @PreAuthorize("@eh.check('dept:add')")
    public R<List<BaseErrDto>> addDept(@RequestBody @Validated CreateDeptVo createDeptVo, BindingResult bindingResult){
        List<BaseErrDto> errDtos = ValidUtils.getBaseErrDtoByBindingRes(bindingResult);
        if (errDtos.isEmpty()){
            UserDetailsDto currentUser = (UserDetailsDto) SecurityUtils.getCurrentUser();
            sysDeptService.save(createDeptVo,currentUser);
            return R.ok();
        }
        return R.<List<BaseErrDto>>error(BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg())
                .setData(errDtos);

    }

    @ApiOperation("修改部门")
    @Log("修改部门")
    @PutMapping
    @PreAuthorize("@eh.check('dept:edit')")
    public R<List<BaseErrDto>> editDept(@RequestBody @Validated ValidList<UpdateDeptVo> updateDeptVos,BindingResult bindingResult){
        List<BaseErrDto> errDtos = ValidUtils.getBaseErrDtoByBindingRes(updateDeptVos, bindingResult);

        sysDeptService.editDept(updateDeptVos,errDtos, ((UserDetailsDto) SecurityUtils.getCurrentUser()));

        if (errDtos.isEmpty()){
            return R.ok();
        }
        return R
                .<List<BaseErrDto>>error(BizCodeEnum.Client_Error_CRUD.getCode(),BizCodeEnum.Client_Error_CRUD.getMsg())
                .setData(errDtos);
    }

    @ApiOperation("删除部门")
    @Log("删除部门")
    @DeleteMapping
    @PreAuthorize("@eh.check('dept:del')")
    public R deleteDept(@RequestBody Set<Long> deptIds){
        sysDeptService.removeByIds(deptIds);
        return  R.ok();
    }

    @ApiOperation("全部部门")
    @Log("全部部门")
    @GetMapping("/all")
    public R<List<SysDept>> getAll(){
        return R.<List<SysDept>>ok().setData(sysDeptService.list());
    }

    @ApiOperation("导出数据")
    @Log("导出数据")
    @GetMapping("/download")
    @PreAuthorize("@eh.check('dept:list')")
    public void download(HttpServletResponse response){
        sysDeptService.download(response);
    }



}
