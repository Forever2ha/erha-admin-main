package fun.yizhierha.operation.controller;

import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.ValidUtils;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.operation.domain.OraDeployHistory;
import fun.yizhierha.operation.domain.vo.CreateOraDeployHistoryVo;
import fun.yizhierha.operation.domain.vo.UpdateOraDeployHistoryVo;
import fun.yizhierha.operation.domain.vo.RetrieveOraDeployHistoryVo;

import fun.yizhierha.operation.service.OraDeployHistoryService;
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
* @date Wed Dec 21 15:55:14 CST 2022
**/
@Api(tags = "运维管理:部署管理")
@RestController
@RequestMapping("/api/operation/history")
@RequiredArgsConstructor
public class OraDeployHistoryController{

    private final OraDeployHistoryService oraDeployHistoryService;

    @ApiOperation("获取部署管理")
    @Log("获取部署管理")
    @GetMapping
    @PreAuthorize("@eh.check('operation:oraDeployHistory:list')")
    public R<PageUtils<OraDeployHistory>> list(RetrieveOraDeployHistoryVo retrieveOraDeployHistoryVo, Query.PageVo pageVo){
        PageUtils<OraDeployHistory> res = oraDeployHistoryService.list(retrieveOraDeployHistoryVo,pageVo);
        return R.<PageUtils<OraDeployHistory>>ok().setData(res);
    }

    // 用户不能新增
    /*@ApiOperation("新增部署管理")
    @Log("新增部署管理")
    @PostMapping
    @PreAuthorize("@eh.check('operation:oraDeployHistory:add')")
    public R<List<BaseErrDto>> add(@Validated @RequestBody CreateOraDeployHistoryVo createOraDeployHistoryVo,
        BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(bindingResult);
        if (errDtoList.isEmpty()){
            oraDeployHistoryService.save(createOraDeployHistoryVo);
            return R.ok();
        }
        return R.<List<BaseErrDto>>error(
                BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg()
        ).setData(errDtoList);
    }*/

    // 用户不能修改
    /*@ApiOperation("修改部署管理")
    @Log("修改部署管理")
    @PutMapping
    @PreAuthorize("@eh.check('operation:oraDeployHistory:edit')")
    public R<List<BaseErrDto>> edit(@Validated @RequestBody ValidList<UpdateOraDeployHistoryVo> updateOraDeployHistoryList,
                    BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(updateOraDeployHistoryList, bindingResult);
        if (errDtoList.isEmpty()) {
            oraDeployHistoryService.edit(updateOraDeployHistoryList,errDtoList);
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
    }*/

    @ApiOperation("删除部署管理")
    @Log("删除部署管理")
    @DeleteMapping
    @PreAuthorize("@eh.check('operation:oraDeployHistory:del')")
    public R del(@RequestBody Set<Long> ids){
        oraDeployHistoryService.remove(ids);
        return R.ok();
    }

    @ApiOperation("导出数据")
    @Log("导出数据")
    @GetMapping("/download")
    @PreAuthorize("@eh.check('operation:oraDeployHistory:list')")
    public void download(HttpServletResponse response){
        oraDeployHistoryService.download(response);
    }

}