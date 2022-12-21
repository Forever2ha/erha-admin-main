package fun.yizhierha.modules.system.controller;


import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysDictDetail;
import fun.yizhierha.modules.system.domain.vo.CreateDictDetailVo;
import fun.yizhierha.modules.system.domain.vo.UpdateDictDetailVo;
import fun.yizhierha.modules.system.service.SysDictDetailService;
import fun.yizhierha.modules.system.service.dto.DictDetailDto;
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
import java.util.Set;

@Api(tags = "系统:字典详情")
@RestController
@RequestMapping("/api/dictDetail")
public class DictDetailController {

    @Autowired
    SysDictDetailService sysDictDetailService;

    @ApiOperation("字典详情列表")
    @Log("字典详情列表")
    @GetMapping()
    public R list(@Param("dictName") String dictName, @Param("dictId") Long dictId, Query.PageVo pageVo){
        if (dictId != null){
            return R.<PageUtils<DictDetailDto>>ok().setData(sysDictDetailService.listDictDetail(dictId,pageVo));
        }
        if (StringUtils.isBlank(dictName)){
            return R.<List<SysDictDetail>>ok().setData(sysDictDetailService.list());
        }
        return R.<List<SysDictDetail>>ok().setData(sysDictDetailService.listByDictName(dictName));
    }

    @ApiOperation("添加字典详情")
    @Log("添加字典详情")
    @PostMapping
    @PreAuthorize("@eh.check('system:dictDetail:add')")
    public R<List<BaseErrDto>> addDictDetail(@Validated @RequestBody CreateDictDetailVo createDictDetailVo, BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(bindingResult);
        if (errDtoList.isEmpty()) {
            sysDictDetailService.saveDictDetail(createDictDetailVo, ((UserDetailsDto) SecurityUtils.getCurrentUser()));
            return R.ok();
        }

        return R.<List<BaseErrDto>>error(
                BizCodeEnum.Client_Error_CRUD.getCode(),
                BizCodeEnum.Client_Error_CRUD.getMsg()
        );
    }

    @ApiOperation("修改字典详情")
    @Log("修改字典详情")
    @PutMapping
    @PreAuthorize("@eh.check('system:dictDetail:edit')")
    public R<List<BaseErrDto>> editDictDetail(@Validated @RequestBody ValidList<UpdateDictDetailVo> updateDictDetailVos,
                                              BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(updateDictDetailVos, bindingResult);
        if (errDtoList.isEmpty()) {
          sysDictDetailService.updateDictDetail(updateDictDetailVos, errDtoList, ((UserDetailsDto) SecurityUtils.getCurrentUser()));
            if (errDtoList.isEmpty()) {
                return R.ok();
            }else {
                return R.<List<BaseErrDto>>error(
                        BizCodeEnum.Client_Error_CRUD.getCode(),
                        BizCodeEnum.Client_Error_CRUD.getMsg()
                ).setData(errDtoList);
            }
        }
        return R.<List<BaseErrDto>>error(
                BizCodeEnum.Client_Error_CRUD.getCode(),
                BizCodeEnum.Client_Error_CRUD.getMsg()
        ).setData(errDtoList);
    }

    @ApiOperation("删除字典详情")
    @Log("删除字典详情")
    @DeleteMapping
    @PreAuthorize("@eh.check('system:dictDetail:del')")
    public R delDictDetail(@RequestBody Set<Long> dictDetailIds){
        sysDictDetailService.removeDictDetail(dictDetailIds);
        return R.ok();
    }

}

