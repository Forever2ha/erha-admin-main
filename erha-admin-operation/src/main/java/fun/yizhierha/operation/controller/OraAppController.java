package fun.yizhierha.operation.controller;

import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.ValidUtils;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.operation.domain.OraApp;
import fun.yizhierha.operation.domain.vo.CreateOraAppVo;
import fun.yizhierha.operation.domain.vo.UpdateOraAppVo;
import fun.yizhierha.operation.domain.vo.RetrieveOraAppVo;

import fun.yizhierha.operation.service.OraAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/** generated by EH-Admin
* @author xaopohi
* @date Wed Dec 21 15:15:54 CST 2022
**/
@Api(tags = "运维管理:应用管理")
@RestController
@RequestMapping("/api/operation/app")
@RequiredArgsConstructor
public class OraAppController{

    private final OraAppService oraAppService;

    @ApiOperation("获取应用管理")
    @Log("获取应用管理")
    @GetMapping
    @PreAuthorize("@eh.check('operation:oraApp:list')")
    public R<PageUtils<OraApp>> list(RetrieveOraAppVo retrieveOraAppVo, Query.PageVo pageVo){
        PageUtils<OraApp> res = oraAppService.list(retrieveOraAppVo,pageVo);
        return R.<PageUtils<OraApp>>ok().setData(res);
    }

    @ApiOperation("新增应用管理")
    @Log("新增应用管理")
    @PostMapping
    @PreAuthorize("@eh.check('operation:oraApp:add')")
    public R<List<BaseErrDto>> add(@Validated @RequestBody CreateOraAppVo createOraAppVo,
        BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(bindingResult);
        if (errDtoList.isEmpty()){
            oraAppService.save(createOraAppVo);
            return R.ok();
        }
        return R.<List<BaseErrDto>>error(
                BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg()
        ).setData(errDtoList);
    }

    @ApiOperation("修改应用管理")
    @Log("修改应用管理")
    @PutMapping
    @PreAuthorize("@eh.check('operation:oraApp:edit')")
    public R<List<BaseErrDto>> edit(@Validated @RequestBody ValidList<UpdateOraAppVo> updateOraAppList,
                    BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(updateOraAppList, bindingResult);
        if (errDtoList.isEmpty()) {
            oraAppService.edit(updateOraAppList,errDtoList);
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

    @ApiOperation("删除应用管理")
    @Log("删除应用管理")
    @DeleteMapping
    @PreAuthorize("@eh.check('operation:oraApp:del')")
    public R del(@RequestBody Set<Long> ids){
        oraAppService.remove(ids);
        return R.ok();
    }

    @ApiOperation("导出数据")
    @Log("导出数据")
    @GetMapping("/download")
    @PreAuthorize("@eh.check('operation:oraApp:list')")
    public void download(HttpServletResponse response){
        oraAppService.download(response);
    }

}