package fun.yizhierha.modules.system.controller;


import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.vo.CreateQuartzJobVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveQuartzJobVo;
import fun.yizhierha.modules.system.domain.vo.UpdateQuartzJobVo;
import fun.yizhierha.modules.system.service.SysQuartzJobService;
import fun.yizhierha.modules.system.service.dto.SummaryQuartzJobDto;
import fun.yizhierha.utils.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@Api(tags = "系统:任务调度")
@RequestMapping("/api/quartz")
public class QuartzController {

    @Autowired
    SysQuartzJobService sysQuartzJobService;

    @ApiOperation("任务列表")
    @GetMapping()
    @PreAuthorize("@eh.check('quartz:list')")
    public R<PageUtils<SummaryQuartzJobDto>> list(RetrieveQuartzJobVo retrieveQuartzJobVo, Query.PageVo pageVo){
        PageUtils<SummaryQuartzJobDto> res = sysQuartzJobService.listQuartzJob(retrieveQuartzJobVo,pageVo);
        return R.<PageUtils<SummaryQuartzJobDto>>ok().setData(res);
    }

    @ApiOperation("添加一个任务")
    @PostMapping
    @PreAuthorize("@eh.check('quartz:add')")
    public R<List<BaseErrDto>> addQuartzJob(@Validated @RequestBody CreateQuartzJobVo createQuartzJobVo,
        BindingResult bindingResult){
        List<BaseErrDto> errDtoList = CommonUtil.getBaseErrDtoByBindingRes(bindingResult);
        if (errDtoList.isEmpty()){
            sysQuartzJobService.saveQuartzJob(createQuartzJobVo, ((UserDetailsDto) SecurityUtils.getCurrentUser()));
            return R.ok();
        }
        return R.<List<BaseErrDto>>error(
                BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg()
        ).setData(errDtoList);

    }

    @ApiOperation("修改定时任务")
    @PutMapping
    @PreAuthorize("@eh.check('quartz:edit')")
    public R<List<BaseErrDto>> editMenu(@Validated @RequestBody ValidList<UpdateQuartzJobVo> updateQuartzJobList, BindingResult bindingResult){

        List<BaseErrDto> errDtoList = CommonUtil.getBaseErrDtoByBindingRes(updateQuartzJobList, bindingResult);
        if (errDtoList.isEmpty()) {
            sysQuartzJobService.editQuartzJob(updateQuartzJobList,errDtoList,((UserDetailsDto) SecurityUtils.getCurrentUser()));
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

    @ApiOperation("删除定时任务")
    @DeleteMapping
    @PreAuthorize("@eh.check('quartz:del')")
    public R delJob(@RequestBody Set<Long> jobIds){
        sysQuartzJobService.removeJob(jobIds);
        return R.ok();
    }

    @ApiOperation("导出数据")
    @GetMapping("/download")
    @PreAuthorize("@eh.check('quartz:list')")
    public void download(HttpServletResponse response){
        sysQuartzJobService.download(response);
    }

    @ApiOperation("切换任务运行状态")
    @PutMapping("/switch")
    @PreAuthorize("@eh.check('quartz:edit')")
    public R switchStatus(@RequestBody Map<String,Long> params){
         sysQuartzJobService.toggleIsPause(params.get("jobId"));
        return R.ok();
    }

}
