package fun.yizhierha.tools.generate.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.annotation.rest.AnonymousAccess;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.tools.generate.domain.CodeColumnConfig;
import fun.yizhierha.tools.generate.domain.CodeGenConfig;
import fun.yizhierha.tools.generate.domain.TableInfo;
import fun.yizhierha.tools.generate.domain.vo.UpdateOrSaveCodeGenConfigVo;
import fun.yizhierha.tools.generate.domain.vo.UpdateTableColVo;
import fun.yizhierha.tools.generate.service.CodeGenConfigService;
import fun.yizhierha.tools.generate.service.TableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "工具：代码生成: 表格信息")
@RequestMapping("/api/generate/table")
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;
    private final CodeGenConfigService codeGenConfigService;

    @PreAuthorize("@eh.check('generate:edit')")
    @ApiOperation("修改或保存表生成配置的信息")
    @Log("修改或保存表生成配置的信息")
    @PutMapping("/genConfig")
    public R<List<BaseErrDto>> editOrSaveTableGenConfig(@RequestBody UpdateOrSaveCodeGenConfigVo updateOrSaveCodeGenConfigVo){
        codeGenConfigService.saveOrUpdate(updateOrSaveCodeGenConfigVo);
        return R.ok();
    }


    @PreAuthorize("@eh.check('generate:list')")
    @ApiOperation("查看最近表生成配置的信息")
    @Log("查看最近表生成配置的信息")
    @GetMapping("/genConfig")
    public R listTableGenConfig(@Param("tableName") String tableName){
        if (tableName !=  null){
            return R.ok().setData(codeGenConfigService.getOne(new QueryWrapper<CodeGenConfig>()
                    .eq(CodeGenConfig.COL_TABLE_NAME,tableName)));
        }
        return R.ok().setData(codeGenConfigService.list());
    }

    @PreAuthorize("@eh.check('generate:list')")
    @ApiOperation("查询表信息")
    @Log("查询表信息")
    @GetMapping
    public R<PageUtils<TableInfo>> listTables(@Param("tableName") String tableName, Query.PageVo pageVo){
        if (tableName == null) tableName = "";
        PageUtils<TableInfo> tableInfoList =  tableService.list(tableName,pageVo);
        return R.<PageUtils<TableInfo>>ok().setData(tableInfoList);
    }

    @PreAuthorize("@eh.check('generate:list')")
    @ApiOperation("查询表的字段信息")
    @Log("查询表的字段信息")
    @GetMapping("/columns")
    public R<List<CodeColumnConfig>> listTableColumns(@Param("table_name") String tableName){
        if (StringUtils.isBlank(tableName)){
            return R.error(BizCodeEnum.Client_Error_CRUD.getCode(), "tableName不能为空");
        }
        return R.<List<CodeColumnConfig>>ok().setData(tableService.listTableCols(tableName));
    }

    @PreAuthorize("@eh.check('generate:edit')")
    @ApiOperation("修改表的字段信息")
    @Log("修改表的字段信息")
    @PutMapping("/columns")
    public R<List<BaseErrDto>> updateTableColumns(@RequestBody @Validated ValidList<UpdateTableColVo> updateTableColVoValidList,
                                BindingResult bindingResult){
        List<BaseErrDto> baseErrDtoList = ValidUtils.getBaseErrDtoByBindingRes(updateTableColVoValidList, bindingResult);
        tableService.updateTableCol(updateTableColVoValidList,baseErrDtoList);
        if (baseErrDtoList.size() > 0){
            return R.<List<BaseErrDto>>error(BizCodeEnum.Client_Error_CRUD.getCode(),
                    BizCodeEnum.Client_Error_BadRequest.getMsg()).setData(baseErrDtoList);
        }
        return R.ok();
    }

    @PreAuthorize("@eh.check('generate:edit')")
    @ApiOperation("同步表的字段信息")
    @Log("同步表的字段信息")
    @PutMapping("/columns/sync/{tableName}")
    public R syncTableColumns(@PathVariable String tableName){
        tableService.syncTableColumns(tableName);
        return  R.ok();
    }

}
