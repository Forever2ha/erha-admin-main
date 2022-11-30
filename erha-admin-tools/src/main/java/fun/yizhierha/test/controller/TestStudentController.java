package fun.yizhierha.test.controller;

import cn.hutool.Hutool;
import cn.hutool.core.util.ArrayUtil;
import fun.yizhierha.common.annotation.rest.AnonymousAccess;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.ValidUtils;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.test.domain.TestStudent;
import fun.yizhierha.test.domain.vo.CreateTestStudentVo;
import fun.yizhierha.test.domain.vo.UpdateTestStudentVo;
import fun.yizhierha.test.domain.vo.RetrieveTestStudentVo;

import fun.yizhierha.test.service.TestStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/** generated by EH-Admin
 * @author 二哈
 * @date Sun Nov 27 15:59:39 CST 2022
 **/
@Api(tags = "测试：学生")
@RestController
@RequestMapping("/api/test/student")
@RequiredArgsConstructor
public class TestStudentController{

    private final TestStudentService testStudentService;

    @ApiOperation("获取学生")
    @GetMapping
    @PreAuthorize("@eh.check('testStudent:list')")
    public R<PageUtils<TestStudent>> list(RetrieveTestStudentVo retrieveTestStudentVo, Query.PageVo pageVo){
        PageUtils<TestStudent> res = testStudentService.list(retrieveTestStudentVo,pageVo);
        return R.<PageUtils<TestStudent>>ok().setData(res);
    }

    @ApiOperation("新增学生")
    @PostMapping
    @PreAuthorize("@eh.check('testStudent:add')")
    public R<List<BaseErrDto>> add(@Validated @RequestBody CreateTestStudentVo createTestStudentVo,
                                   BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(bindingResult);
        if (errDtoList.isEmpty()){
            testStudentService.save(createTestStudentVo);
            return R.ok();
        }
        return R.<List<BaseErrDto>>error(
                BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg()
        ).setData(errDtoList);
    }

    @ApiOperation("修改学生")
    @PutMapping
    @PreAuthorize("@eh.check('testStudent:edit')")
    public R<List<BaseErrDto>> edit(@Validated @RequestBody ValidList<UpdateTestStudentVo> updateTestStudentList,
                                    BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(updateTestStudentList, bindingResult);
        if (errDtoList.isEmpty()) {
            testStudentService.edit(updateTestStudentList,errDtoList);
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

    @ApiOperation("删除学生")
    @DeleteMapping
    @PreAuthorize("@eh.check('testStudent:del')")
    public R delJob(@RequestBody Set<Long> ids){
        testStudentService.remove(ids);
        return R.ok();
    }

    @ApiOperation("导出数据")
    @GetMapping("/download")
    @PreAuthorize("@eh.check('testStudent:list')")
    public void download(HttpServletResponse response){
        testStudentService.download(response);
    }


    @Data
    public static class Vo{
        private String[] names;
    }

    @PostMapping("/test")
    @AnonymousAccess
    public R test(@RequestBody Vo vo){
        String[] arr;
        return R.ok().setData(Arrays.toString(vo.getNames()));
    }


}