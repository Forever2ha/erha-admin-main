package fun.yizhierha.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.StringUtils;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.common.utils.file.ExcelUtils;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysJob;
import fun.yizhierha.modules.system.domain.vo.CreateQuartzJobVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveQuartzJobVo;
import fun.yizhierha.modules.system.domain.vo.UpdateQuartzJobVo;
import fun.yizhierha.modules.system.quartz.QuartzManage;
import fun.yizhierha.modules.system.service.dto.SummaryQuartzJobDto;
import fun.yizhierha.modules.system.service.mapstruct.QuartzJobMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.modules.system.domain.SysQuartzJob;
import fun.yizhierha.modules.system.mapper.SysQuartzJobMapper;
import fun.yizhierha.modules.system.service.SysQuartzJobService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysQuartzJobServiceImpl extends ServiceImpl<SysQuartzJobMapper, SysQuartzJob> implements SysQuartzJobService{

    @Resource
    QuartzJobMapper quartzJobMapper;
    @Resource
    QuartzManage quartzManage;

    @Override
    public PageUtils<SummaryQuartzJobDto> listQuartzJob(RetrieveQuartzJobVo retrieveQuartzJobVo, Query.PageVo pageVo) {
        QueryWrapper<SysQuartzJob> wrapper = new QueryWrapper<>();
        String name = retrieveQuartzJobVo.getName();
        Timestamp endCreateTime = retrieveQuartzJobVo.getEndCreateTime();
        Timestamp startCreateTime = retrieveQuartzJobVo.getStartCreateTime();
        if (name != null){
            wrapper.and((w)->{
                w.like(SysQuartzJob.COL_JOB_NAME,name)
                        .or()
                        .like(SysQuartzJob.COL_BEAN_NAME,name)
                        .or()
                        .like(SysQuartzJob.COL_DESCRIPTION,name);
            });
        }
        if (endCreateTime != null && startCreateTime != null){
            wrapper.between(SysJob.COL_CREATE_TIME,startCreateTime,endCreateTime);
        }

        IPage<SysQuartzJob> iPage = baseMapper.selectPage(new Query<SysQuartzJob>().getPage(pageVo), wrapper);
        return new PageUtils<SummaryQuartzJobDto>(quartzJobMapper.toSummaryQuartzJobDto(iPage));
    }

    @Override
    public void toggleIsPause(SysQuartzJob sysQuartzJob) {

        try {
            if (sysQuartzJob.getIsPause()){
                // 恢复一个job
                quartzManage.resumeJob(sysQuartzJob);

                sysQuartzJob.setIsPause(false);
            }else {
                // 暂停一个job
                quartzManage.pauseJob(sysQuartzJob.getJobId());
                sysQuartzJob.setIsPause(true);
            }
        } catch (Exception e) {
            log.error("转换job状态失败: [jobId: "+sysQuartzJob.getJobId()+"] "+e.getMessage());
        }

        // 更新数据库
        SysQuartzJob job = new SysQuartzJob();
        job.setJobId(sysQuartzJob.getJobId());
        job.setIsPause(sysQuartzJob.getIsPause());
        this.updateById(job);
    }

    @Override
    public void toggleIsPause(Long jobId) {
        SysQuartzJob sysQuartzJob = this.getById(jobId);
        if (sysQuartzJob == null){
            throw new BadRequestException(
              BizCodeEnum.Client_Error_CRUD.getCode(),
              "此jobId("+jobId+")不存在！"
            );
        }
        toggleIsPause(sysQuartzJob);

    }

    @Override
    public void saveQuartzJob(CreateQuartzJobVo createQuartzJobVo, UserDetailsDto currentUser) {
        SysQuartzJob sysQuartzJob = quartzJobMapper.toSysQuartzJob(createQuartzJobVo);
        sysQuartzJob.setCreateBy(currentUser.getUsername());
        sysQuartzJob.setCreateTime(new Date());
        try {
            if (createQuartzJobVo.getRunJobNow()) {
                quartzManage.runJobNow(sysQuartzJob);
                sysQuartzJob.setIsPause(false);
            }else {
                quartzManage.addJob(sysQuartzJob);
            }
        } catch (Exception e) {
            log.error("创建定时任务失败", e);
            throw new BadRequestException("创建定时任务失败");
        }
        this.save(sysQuartzJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editQuartzJob(ValidList<UpdateQuartzJobVo> updateQuartzJobList, List<BaseErrDto> errDtoList, UserDetailsDto currentUser) {
        String username = currentUser.getUsername();
        List<SysQuartzJob> toUpdateSysQuartzJobList = new ArrayList<>();

        for (UpdateQuartzJobVo updateQuartzJobVo : updateQuartzJobList) {
            String subTask = updateQuartzJobVo.getSubTask();
            Long id = updateQuartzJobVo.getId();
            // 1. 修改的job的子任务id不能包含本身
            if(StringUtils.isNoneBlank(subTask)){
                try {
                    List<String> subTaskIds = Arrays.asList(subTask.split("[,，]"));
                    if (subTaskIds.contains(id.toString())){
                        BaseErrDto baseErrDto = new BaseErrDto();
                        baseErrDto.setId(id);
                        baseErrDto.setErrorMsg("该任务的子任务id不能包含此任务的id");
                        baseErrDto.setErrorField(SysQuartzJob.COL_SUB_TASK);
                        baseErrDto.setErrorVal(subTask);
                        errDtoList.add(baseErrDto);
                    }
                }catch (Exception e){

                    BaseErrDto baseErrDto = new BaseErrDto();
                    baseErrDto.setId(id);
                    baseErrDto.setErrorMsg("子任务表达方式有误:"+e.getMessage());
                    baseErrDto.setErrorField(SysQuartzJob.COL_SUB_TASK);
                    baseErrDto.setErrorVal(subTask);
                    errDtoList.add(baseErrDto);
                }
            }
            // 2. 通过schedule修改
            SysQuartzJob sysQuartzJob = quartzJobMapper.toSysQuartzJob(updateQuartzJobVo);

            try {
                //  sysQuartzJob只有修改的信息，所以需要查出所有的信息
                quartzManage.updateJob(quartzJobMapper.updateSysQuartzJob(updateQuartzJobVo,
                        baseMapper.selectById(sysQuartzJob.getJobId())
                        ));
                // 3. 添加修改时间和修改人信息
                sysQuartzJob.setUpdateBy(username);
                sysQuartzJob.setUpdateTime(new Date());
                toUpdateSysQuartzJobList.add(sysQuartzJob);
            } catch (Exception e) {
                log.error("更新任务删除失败：[jobId:"+id+"]");

                BaseErrDto baseErrDto = new BaseErrDto();
                baseErrDto.setId(id);
                baseErrDto.setErrorMsg("更新任务删除失败");
                baseErrDto.setErrorField("");
                baseErrDto.setErrorVal(e.getMessage());
                errDtoList.add(baseErrDto);
            }


        }


        // 2. 更新
        this.updateBatchById(toUpdateSysQuartzJobList);

    }

    @Override
    public void removeJob(Set<Long> jobIds) {
        StringBuilder errInfo = new StringBuilder();

        for (Long jobId : jobIds) {
            try {
                quartzManage.deleteJob(jobId);
                this.removeById(jobId);
            } catch (Exception e) {
                errInfo.append("[删除")
                        .append(jobId)
                        .append("失败 : "+e.getMessage()+"] ");
                log.error("定时任务删除失败：[jobId:"+jobId+"]");
            }
        }
        if (errInfo.length() != 0){
            throw new BadRequestException(
                    BizCodeEnum.Client_Error_CRUD.getCode(),
                    errInfo.toString()
            );
        }

    }

    @Override
    public void download(HttpServletResponse response) {
        List<SummaryQuartzJobDto> summaryQuartzJobDtoList = quartzJobMapper.toSummaryQuartzJobDtos(list());
        ExcelUtils.export(response,"定时任务",summaryQuartzJobDtoList, SummaryQuartzJobDto.class);
    }

    @Async
    @Override
    public void executionSubJob(String[] taskIds) {
        for (String taskId : taskIds) {
            if (StringUtils.isBlank(taskId)) continue;
            SysQuartzJob quartzJob = this.getById(taskId);
            if (quartzJob == null)continue;
            try {
                quartzManage.runJobNow(quartzJob);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
