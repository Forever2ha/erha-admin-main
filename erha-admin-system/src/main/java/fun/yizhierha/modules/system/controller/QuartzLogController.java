package fun.yizhierha.modules.system.controller;


import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.R;
import fun.yizhierha.modules.system.domain.SysQuartzLog;
import fun.yizhierha.modules.system.domain.vo.RetrieveQuartzLogVo;
import fun.yizhierha.modules.system.service.SysQuartzJobService;
import fun.yizhierha.modules.system.service.SysQuartzLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "系统:任务执行日志")
@RequestMapping("/api/system/quartzLog")
public class QuartzLogController {

    @Autowired
    SysQuartzLogService sysQuartzLogService;

    @ApiOperation("任务日志列表")
    @Log("任务日志列表")
    @GetMapping
    @PreAuthorize("@eh.check('system:quartzLog:list')")
    public R<PageUtils<SysQuartzLog>> list(RetrieveQuartzLogVo retrieveQuartzLogVo, Query.PageVo pageVo){
        return R.<PageUtils<SysQuartzLog>>ok().setData(sysQuartzLogService.listQuartzLog(retrieveQuartzLogVo,pageVo));
    }


    @ApiOperation("导出数据")
    @Log("导出数据")
    @GetMapping("/download")
    @PreAuthorize("@eh.check('system:quartzLog:list')")
    public void download(HttpServletResponse response){
        sysQuartzLogService.download(response);
    }

}
