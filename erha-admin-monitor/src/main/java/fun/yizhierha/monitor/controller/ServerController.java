package fun.yizhierha.monitor.controller;

import fun.yizhierha.common.annotation.rest.AnonymousAccess;
import fun.yizhierha.common.utils.R;
import fun.yizhierha.monitor.service.ServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "系统监控：服务监控")
@RestController
@RequestMapping("/api/monitor/server")
@RequiredArgsConstructor
public class ServerController {
    private final ServerService serverService;

    @ApiOperation("获取系统信息")
    @GetMapping
    @PreAuthorize("@eh.check('monitor:server:list')")
    public R list(){
        return R.ok().setData(serverService.getServerInfo());
    }
}
