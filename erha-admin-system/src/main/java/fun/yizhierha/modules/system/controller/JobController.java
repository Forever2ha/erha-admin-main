package fun.yizhierha.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysJob;
import fun.yizhierha.modules.system.domain.vo.CreateJobVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveJobVo;
import fun.yizhierha.modules.system.domain.vo.UpdateJobVo;
import fun.yizhierha.modules.system.service.SysJobService;
import fun.yizhierha.modules.system.service.dto.SummaryJobDto;
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

@Api(tags = "系统:岗位接")
@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    SysJobService sysJobService;

    @ApiOperation("岗位列表")
    @Log("岗位列表")
    @GetMapping("/all")
    @PreAuthorize("@eh.check('job:list')")
    public R<List<SysJob>> list(){
        return R.<List<SysJob>>ok().setData(sysJobService.list(new QueryWrapper<SysJob>().eq(SysJob.COL_ENABLED,true)));
    }

    @ApiOperation("获取岗位")
    @Log("获取岗位")
    @GetMapping
    @PreAuthorize("@eh.check('job:list')")
    public R<PageUtils<SummaryJobDto>> getJob(RetrieveJobVo retrieveJobVo, Query.PageVo pageVo){
        PageUtils<SummaryJobDto> res = sysJobService.listJob(retrieveJobVo,pageVo);
        return R.<PageUtils<SummaryJobDto>>ok().setData(res);
    }

    @ApiOperation("添加岗位")
    @Log("添加岗位")
    @PostMapping
    @PreAuthorize("@eh.check('job:add')")
    public R<List<BaseErrDto>> addJob(@RequestBody @Validated CreateJobVo createJobVo, BindingResult bindingResult){
        List<BaseErrDto> errDtos = ValidUtils.getBaseErrDtoByBindingRes(bindingResult);
        if (errDtos.isEmpty() ){
            sysJobService.save(createJobVo, ((UserDetailsDto) SecurityUtils.getCurrentUser()));
            return R.ok();
        }
        return R.<List<BaseErrDto>>error(
                BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg()
        ).setData(errDtos);
    }

    @ApiOperation("修改部门")
    @Log("修改部门")
    @PutMapping
    @PreAuthorize("@eh.check('job:edit')")
    public R<List<BaseErrDto>> editJob(@RequestBody @Validated ValidList<UpdateJobVo> updateJobVoValidList,BindingResult bindingResult){
        List<BaseErrDto> errDtos = ValidUtils.getBaseErrDtoByBindingRes(updateJobVoValidList, bindingResult);
        if (errDtos.isEmpty()){
            sysJobService.editJob(updateJobVoValidList,errDtos, ((UserDetailsDto) SecurityUtils.getCurrentUser()));
            if (errDtos.isEmpty()){
                return R.ok();
            }else {
                return R.<List<BaseErrDto>>error(
                        BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg()
                ).setData(errDtos);
            }
        }
        return R.<List<BaseErrDto>>error(
                BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg()
        ).setData(errDtos);
    }

    @ApiOperation("删除岗位")
    @Log("删除岗位")
    @DeleteMapping
    @PreAuthorize("@eh.check('job:del')")
    public R delJob(@RequestBody Set<Long> jobIds){
        sysJobService.removeJob(jobIds);
        return R.ok();
    }

    @ApiOperation("导出数据")
    @Log("导出数据")
    @GetMapping("/download")
    @PreAuthorize("@eh.check('job:list')")
    public void download(HttpServletResponse response){
        sysJobService.download(response);
    }

}
