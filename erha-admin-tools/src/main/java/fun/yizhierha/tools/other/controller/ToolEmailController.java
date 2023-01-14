package fun.yizhierha.tools.other.controller;

import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.ValidUtils;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.tools.other.domain.ToolEmailConfig;
import fun.yizhierha.tools.other.domain.vo.CreateToolEmailConfigVo;
import fun.yizhierha.tools.other.domain.vo.UpdateToolEmailConfigVo;
import fun.yizhierha.tools.other.domain.vo.RetrieveToolEmailConfigVo;

import fun.yizhierha.tools.other.service.ToolEmailConfigService;
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
* @author 二哈
* @date Fri Jan 13 20:37:24 CST 2023
**/
@Api(tags = "系统工具：邮件工具")
@RestController
@RequestMapping("/api/tools/email")
@RequiredArgsConstructor
public class ToolEmailController{

    private final ToolEmailConfigService toolEmailConfigService;

    @ApiOperation("获取邮件配置")
    @Log("获取邮件配置")
    @GetMapping("/config")
    @PreAuthorize("@eh.check('tools:email:config:list')")
    public R<PageUtils<ToolEmailConfig>> list(RetrieveToolEmailConfigVo retrieveToolEmailConfigVo, Query.PageVo pageVo){
        PageUtils<ToolEmailConfig> res = toolEmailConfigService.list(retrieveToolEmailConfigVo,pageVo);
        return R.<PageUtils<ToolEmailConfig>>ok().setData(res);
    }

    @ApiOperation("新增邮件配置")
    @Log("新增邮件配置")
    @PostMapping("/config")
    @PreAuthorize("@eh.check('tools:email:config:add')")
    public R<List<BaseErrDto>> add(@Validated @RequestBody CreateToolEmailConfigVo createToolEmailConfigVo,
        BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(bindingResult);
        if (errDtoList.isEmpty()){
            toolEmailConfigService.save(createToolEmailConfigVo);
            return R.ok();
        }
        return R.<List<BaseErrDto>>error(
                BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg()
        ).setData(errDtoList);
    }

    @ApiOperation("修改邮件配置")
    @Log("修改邮件配置")
    @PutMapping("/config")
    @PreAuthorize("@eh.check('tools:email:config:edit')")
    public R<List<BaseErrDto>> edit(@Validated @RequestBody ValidList<UpdateToolEmailConfigVo> updateToolEmailConfigList,
                    BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(updateToolEmailConfigList, bindingResult);
        if (errDtoList.isEmpty()) {
            toolEmailConfigService.edit(updateToolEmailConfigList,errDtoList);
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

    @ApiOperation("删除邮件配置")
    @Log("删除邮件配置")
    @DeleteMapping("/config")
    @PreAuthorize("@eh.check('tools:email:config:del')")
    public R del(@RequestBody Set<Long> ids){
        toolEmailConfigService.remove(ids);
        return R.ok();
    }

    @ApiOperation("导出数据")
    @Log("导出数据")
    @GetMapping("/config/download")
    @PreAuthorize("@eh.check('tools:email:config:list')")
    public void download(HttpServletResponse response){
        toolEmailConfigService.download(response);
    }

}