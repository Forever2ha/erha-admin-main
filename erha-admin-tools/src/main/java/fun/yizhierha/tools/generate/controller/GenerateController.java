package fun.yizhierha.tools.generate.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.annotation.rest.AnonymousAccess;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.R;
import fun.yizhierha.common.utils.StringUtils;
import fun.yizhierha.common.utils.file.FileUtil;
import fun.yizhierha.tools.generate.domain.CodeColumnConfig;
import fun.yizhierha.tools.generate.domain.CodeGenConfig;
import fun.yizhierha.tools.generate.service.CodeColumnConfigService;
import fun.yizhierha.tools.generate.service.CodeGenConfigService;
import fun.yizhierha.tools.generate.service.GenerateService;
import fun.yizhierha.tools.generate.utils.GenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(tags = "工具：代码生成")
@RequestMapping("/api/tools/generate")
@RequiredArgsConstructor
public class GenerateController {

    private final GenerateService generateService;


    @Autowired
    CodeGenConfigService codeGenConfigService;
    @Autowired
    CodeColumnConfigService codeColumnConfigService;

    @GetMapping("/preview")
    @PreAuthorize("@eh.check('tools:generate:list')")
    @ApiOperation("预览代码")
    @Log("预览代码")
    public R preview(@Param("tableName") String tableName){
        if (StringUtils.isBlank(tableName)) return R.error("表名不能为空！");
        return R.ok().setData(generateService.preview(tableName));
    }

    @GetMapping("/download")
    @PreAuthorize("@eh.check('tools:generate:list')")
    @ApiOperation("预览代码")
    @Log("预览代码")
    public void download(@Param("tableName") String tableName,HttpServletResponse response,HttpServletRequest request){
        generateService.download(tableName,request,response);
    }

    @GetMapping
    @PreAuthorize("@eh.check('tools:generate:list')")
    @ApiOperation("生成代码")
    @Log("生成代码")
    public R generate(@Param("tableName") String tableName){
        throw new BadRequestException("演示环境下无法生成代码！");
    }


}
