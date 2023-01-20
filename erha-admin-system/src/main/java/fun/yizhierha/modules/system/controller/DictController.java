package fun.yizhierha.modules.system.controller;


import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.vo.CreateDictVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveDictVo;
import fun.yizhierha.modules.system.domain.vo.UpdateDictVo;
import fun.yizhierha.modules.system.service.SysDictService;
import fun.yizhierha.modules.system.service.dto.DictDto;
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

@Api(tags = "系统:字典")
@RestController
@RequestMapping("/api/system/dict")
public class DictController {

    @Autowired
    SysDictService sysDictService;

    @ApiOperation("获取字典")
    @Log("获取字典")
    @GetMapping
    @PreAuthorize("@eh.check('system:dict:list')")
    public R<PageUtils<DictDto>> list(RetrieveDictVo retrieveDictVo, Query.PageVo pageVo){
        return R.<PageUtils<DictDto>>ok().setData(sysDictService.listDict(retrieveDictVo,pageVo));
    }

    @ApiOperation("添加字典")
    @Log("添加字典")
    @PostMapping
    @PreAuthorize("@eh.check('system:dict:add')")
    public R<List<BaseErrDto>> addDict(@Validated @RequestBody CreateDictVo createDictVo, BindingResult bindingResult){
        List<BaseErrDto> baseErrDtoList = ValidUtils.getBaseErrDtoByBindingRes(bindingResult);
        if (baseErrDtoList.isEmpty()) {
            sysDictService.saveDict(createDictVo, ((UserDetailsDto) SecurityUtils.getCurrentUser()));
            return R.ok();
        }
        return R.<List<BaseErrDto>>error(BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg())
                .setData(baseErrDtoList);

    }

    @ApiOperation("修改字典")
    @Log("修改字典")
    @PutMapping
    @PreAuthorize("@eh.check('system:dict:edit')")
    public R<List<BaseErrDto>> editDict(@Validated @RequestBody ValidList<UpdateDictVo> updateDictVos,BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(updateDictVos, bindingResult);
        if (errDtoList.isEmpty()) {
            sysDictService.editDict(updateDictVos,errDtoList, ((UserDetailsDto) SecurityUtils.getCurrentUser()));
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


    @ApiOperation("删除字典")
    @Log("删除字典")
    @DeleteMapping
    @PreAuthorize("@eh.check('system:dict:del')")
    public R delDict(@RequestBody Set<Long> dictIds){
       throw new BadRequestException("演示环境无法删除！");
        /* sysDictService.removeDict(dictIds);
        return R.ok();*/
    }

    @ApiOperation("导出数据")
    @Log("导出数据")
    @GetMapping("/download")
    @PreAuthorize("@eh.check('system:dict:list')")
    public void download(HttpServletResponse response){
        sysDictService.download(response);
    }
}
