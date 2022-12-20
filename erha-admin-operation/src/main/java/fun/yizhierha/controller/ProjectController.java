package fun.yizhierha.controller;

import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.R;
import fun.yizhierha.domain.vo.RetrieveProject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目控制器
 *
 * @author wxf
 * @date 2022/12/20
 */
@Api(tags = "运营管理：项目管理")
@RestController
@RequestMapping("/api/operation/project")
@RequiredArgsConstructor
public class ProjectController {


    @ApiOperation("项目列表")
    @Log("项目列表")
    @GetMapping()
    @PreAuthorize("@eh.check('operation:project:list')")
    public R list(RetrieveProject project, Query.PageVo pageVo) {

        return null;
    }
}
