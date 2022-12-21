package fun.yizhierha.operation.controller;

import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.ValidUtils;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.operation.domain.OraServer;
import fun.yizhierha.operation.domain.vo.CreateOraServerVo;
import fun.yizhierha.operation.domain.vo.UpdateOraServerVo;
import fun.yizhierha.operation.domain.vo.RetrieveOraServerVo;

import fun.yizhierha.operation.service.OraServerService;
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
* @date Wed Dec 21 13:25:19 CST 2022
**/
@Api(tags = "运维管理:服务器")
@RestController
@RequestMapping("/api/operation/server")
@RequiredArgsConstructor
public class OraServerController{

    private final OraServerService oraServerService;

    @ApiOperation("获取服务器")
    @Log("获取服务器")
    @GetMapping
    @PreAuthorize("@eh.check('oraServer:list')")
    public R<PageUtils<OraServer>> list(RetrieveOraServerVo retrieveOraServerVo, Query.PageVo pageVo){
        PageUtils<OraServer> res = oraServerService.list(retrieveOraServerVo,pageVo);
        return R.<PageUtils<OraServer>>ok().setData(res);
    }

    @ApiOperation("新增服务器")
    @Log("新增服务器")
    @PostMapping
    @PreAuthorize("@eh.check('oraServer:add')")
    public R<List<BaseErrDto>> add(@Validated @RequestBody CreateOraServerVo createOraServerVo,
        BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(bindingResult);
        if (errDtoList.isEmpty()){
            oraServerService.save(createOraServerVo);
            return R.ok();
        }
        return R.<List<BaseErrDto>>error(
                BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg()
        ).setData(errDtoList);
    }

    @ApiOperation("修改服务器")
    @Log("修改服务器")
    @PutMapping
    @PreAuthorize("@eh.check('oraServer:edit')")
    public R<List<BaseErrDto>> edit(@Validated @RequestBody ValidList<UpdateOraServerVo> updateOraServerList,
                    BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(updateOraServerList, bindingResult);
        if (errDtoList.isEmpty()) {
            oraServerService.edit(updateOraServerList,errDtoList);
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

    @ApiOperation("删除服务器")
    @Log("删除服务器")
    @DeleteMapping
    @PreAuthorize("@eh.check('oraServer:del')")
    public R del(@RequestBody Set<Long> ids){
        oraServerService.remove(ids);
        return R.ok();
    }

    @ApiOperation("导出数据")
    @Log("导出数据")
    @GetMapping("/download")
    @PreAuthorize("@eh.check('oraServer:list')")
    public void download(HttpServletResponse response){
        oraServerService.download(response);
    }

}