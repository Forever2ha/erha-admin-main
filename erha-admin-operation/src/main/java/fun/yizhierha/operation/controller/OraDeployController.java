package fun.yizhierha.operation.controller;

import cn.hutool.core.date.DateUtil;
import fun.yizhierha.common.annotation.Log;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.ValidUtils;
import fun.yizhierha.common.utils.*;
import fun.yizhierha.common.utils.file.FileUtil;
import fun.yizhierha.operation.domain.OraDeploy;
import fun.yizhierha.operation.domain.OraDeployHistory;
import fun.yizhierha.operation.domain.vo.CreateOraDeployVo;
import fun.yizhierha.operation.domain.vo.UpdateOraDeployVo;
import fun.yizhierha.operation.domain.vo.RetrieveOraDeployVo;

import fun.yizhierha.operation.service.OraDeployService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/** generated by EH-Admin
* @author xaopohi
* @date Wed Dec 21 15:42:52 CST 2022
**/
@Api(tags = "运维管理:部署管理")
@RestController
@RequestMapping("/api/operation/deploy")
@RequiredArgsConstructor
public class OraDeployController{
    private final OraDeployService oraDeployService;

    @ApiOperation("获取部署管理")
    @Log("获取部署管理")
    @GetMapping
    @PreAuthorize("@eh.check('operation:oraDeploy:list')")
    public R<PageUtils<OraDeploy>> list(RetrieveOraDeployVo retrieveOraDeployVo, Query.PageVo pageVo){
        PageUtils<OraDeploy> res = oraDeployService.list(retrieveOraDeployVo,pageVo);
        return R.<PageUtils<OraDeploy>>ok().setData(res);
    }

    @ApiOperation("新增部署管理")
    @Log("新增部署管理")
    @PostMapping
    @PreAuthorize("@eh.check('operation:oraDeploy:add')")
    public R<List<BaseErrDto>> add(@Validated @RequestBody CreateOraDeployVo createOraDeployVo,
        BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(bindingResult);
        if (errDtoList.isEmpty()){
            oraDeployService.save(createOraDeployVo);
            return R.ok();
        }
        return R.<List<BaseErrDto>>error(
                BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg()
        ).setData(errDtoList);
    }

    @ApiOperation("修改部署管理")
    @Log("修改部署管理")
    @PutMapping
    @PreAuthorize("@eh.check('operation:oraDeploy:edit')")
    public R<List<BaseErrDto>> edit(@Validated @RequestBody ValidList<UpdateOraDeployVo> updateOraDeployList,
                    BindingResult bindingResult){
        List<BaseErrDto> errDtoList = ValidUtils.getBaseErrDtoByBindingRes(updateOraDeployList, bindingResult);
        if (errDtoList.isEmpty()) {
            oraDeployService.edit(updateOraDeployList,errDtoList);
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

    @ApiOperation("删除部署管理")
    @Log("删除部署管理")
    @DeleteMapping
    @PreAuthorize("@eh.check('operation:oraDeploy:del')")
    public R del(@RequestBody Set<Long> ids){
        oraDeployService.remove(ids);
        return R.ok();
    }

    @ApiOperation("导出数据")
    @Log("导出数据")
    @GetMapping("/download")
    @PreAuthorize("@eh.check('operation:oraDeploy:list')")
    public void download(HttpServletResponse response){
        oraDeployService.download(response);
    }

    @Log("上传文件部署")
    @ApiOperation(value = "上传文件部署")
    @PostMapping(value = "/upload/{deployId}")
    @PreAuthorize("@eh.check('operation:oraDeploy:edit')")
    public R uploadDeploy(@RequestBody MultipartFile file, HttpServletRequest request,@PathVariable Long deployId)throws Exception{
        String fileName = "";
        if(file != null){
            fileName = file.getOriginalFilename();
            File dir = new File(FileUtil.getFileDir());
            File deployFile = new File(FileUtil.getFileDir()+fileName);
            if (!dir.exists()&&!dir.isDirectory()) {
                dir.mkdirs();
            }
            FileUtil.del(deployFile);
            file.transferTo(deployFile);
            //文件下一步要根据文件名字来
            oraDeployService.deploy(FileUtil.getFileDir() +fileName ,deployId);
        }else{
            System.out.println("没有找到相对应的文件");
        }
        System.out.println("文件上传的原名称为:"+ Objects.requireNonNull(file).getOriginalFilename());
        Map<String,Object> map = new HashMap<>(1);
        map.put("id",fileName);
        return R.ok().setData(map);
    }

    @Log("系统还原")
    @ApiOperation(value = "系统还原")
    @PostMapping(value = "/serverReduction/{deployHisId}")
    @PreAuthorize("@eh.check('operation:oraDeploy:edit')")
    public R serverReduction(@PathVariable Long deployHisId){
        String result = oraDeployService.serverReduction(deployHisId);
        return R.ok().setData(result);
    }
    @Log("服务运行状态")
    @ApiOperation(value = "服务运行状态")
        @PostMapping(value = "/serverStatus/{deployId}")
    @PreAuthorize("@eh.check('operation:oraDeploy:edit')")
    public R serverStatus(@PathVariable Long deployId){
        String result = oraDeployService.serverStatus(deployId);
        return R.ok().setData(result);
    }
    @Log("启动服务")
    @ApiOperation(value = "启动服务")
    @PostMapping(value = "/startServer/{deployId}")
    @PreAuthorize("@eh.check('operation:oraDeploy:edit')")
    public R startServer(@PathVariable Long deployId){
        String result = oraDeployService.startServer(deployId);
        return R.ok().setData(result);
    }
    @Log("停止服务")
    @ApiOperation(value = "停止服务")
    @PostMapping(value = "/stopServer/{deployId}")
    @PreAuthorize("@eh.check('operation:oraDeploy:edit')")
    public R stopServer(@PathVariable Long deployId){
        String result = oraDeployService.stopServer(deployId);
        return R.ok().setData(result);
    }
}