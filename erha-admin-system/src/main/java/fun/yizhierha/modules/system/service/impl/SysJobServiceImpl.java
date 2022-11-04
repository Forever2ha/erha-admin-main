package fun.yizhierha.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.common.utils.file.ExcelUtils;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysUsersJobs;
import fun.yizhierha.modules.system.domain.vo.CreateJobVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveJobVo;
import fun.yizhierha.modules.system.domain.vo.UpdateJobVo;
import fun.yizhierha.modules.system.service.SysUsersJobsService;
import fun.yizhierha.modules.system.service.dto.JobDto;
import fun.yizhierha.modules.system.service.dto.SummaryJobDto;
import fun.yizhierha.modules.system.service.mapstruct.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.modules.system.domain.SysJob;
import fun.yizhierha.modules.system.mapper.SysJobMapper;
import fun.yizhierha.modules.system.service.SysJobService;
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService{
    @Autowired
    JobMapper jobMapper;

    @Autowired
    SysUsersJobsService sysUsersJobsService;

    @Override
    public Set<SysJob> selectSysJobByUserId(Long userId) {
        return baseMapper.selectSysJobByUserId(userId);
    }

    @Override
    public Set<JobDto> getJobDtoSetByUserIds(List<Long> userIds) {
        return baseMapper.selectJobDtoSetByUserIds(userIds);
    }

    @Override
    public PageUtils<SummaryJobDto> listJob(RetrieveJobVo retrieveJobVo, Query.PageVo pageVo) {
        QueryWrapper<SysJob> queryWrapper = new QueryWrapper<>();
        Boolean jobStatus = retrieveJobVo.getJobStatus();
        String name = retrieveJobVo.getName();
        Timestamp endCreateTime = retrieveJobVo.getEndCreateTime();
        Timestamp startCreateTime = retrieveJobVo.getStartCreateTime();

        if (jobStatus != null){
            queryWrapper.eq(SysJob.COL_ENABLED, jobStatus);
        }
        if (name != null){
            queryWrapper.like(SysJob.COL_NAME,name);
        }
        if (endCreateTime != null && startCreateTime != null){
            queryWrapper.between(SysJob.COL_CREATE_TIME,startCreateTime,endCreateTime);
        }


        IPage<SysJob> sysJobIPage = baseMapper.selectPage(new Query<SysJob>().getPage(pageVo), queryWrapper);
        sysJobIPage.getRecords().sort(Comparator.comparing(SysJob::getJobSort));
        return new PageUtils<>(jobMapper.toSummaryJobDto(sysJobIPage));

    }

    @Override
    public void save(CreateJobVo createJobVo, UserDetailsDto currentUser) {
        // 1.检查名字是否重复
        if (!list(new QueryWrapper<SysJob>().eq(SysJob.COL_NAME,createJobVo.getName())).isEmpty()) {
            throw new BadRequestException(
                    BizCodeEnum.Client_Error_CRUD.getCode(),
                    "岗位名重复"
            );
        }
        // 2.修改基本属性
        SysJob sysJob = jobMapper.toSysJob(createJobVo);
        sysJob.setCreateBy(currentUser.getUsername());
        sysJob.setCreateTime(new Date());
        // 3.添加
        save(sysJob);
    }

    @Override
    public void editJob(ValidList<UpdateJobVo> updateJobVoValidList, List<BaseErrDto> errDtos, UserDetailsDto currentUser) {
        // 1.如果改名字则检查是否有重复
        List<Long> toDeleteIds = new ArrayList<>();
        for (UpdateJobVo updateJobVo : updateJobVoValidList) {
            String name = updateJobVo.getName();
            Long id = updateJobVo.getId();
            if (name != null){
                if (!list(new QueryWrapper<SysJob>().eq(SysJob.COL_NAME,name)).isEmpty()){
                    // 名称重复
                    toDeleteIds.add(id);

                    BaseErrDto baseErrDto = new BaseErrDto();
                    baseErrDto.setId(id);
                    baseErrDto.setErrorField("name");
                    baseErrDto.setErrorVal(name);
                    baseErrDto.setErrorMsg("岗位名重复");
                    errDtos.add(baseErrDto);
                }
            }
        }
        // 2.修改基本信息
        for (Long toDeleteId : toDeleteIds) {
            updateJobVoValidList.removeIf(t->toDeleteId.equals(t.getId()));
        }
        List<SysJob> sysJobList = jobMapper.toSysJobList(updateJobVoValidList);
        for (SysJob sysJob : sysJobList) {
            sysJob.setUpdateBy(currentUser.getUsername());
            sysJob.setUpdateTime(new Date());
        }

        // 3.修改
        updateBatchById(sysJobList);
    }

    @Override
    public void removeJob(Set<Long> jobIds) {
        //1. 检测user 与 job是否有关联
        if (!sysUsersJobsService.list(new QueryWrapper<SysUsersJobs>().in(SysUsersJobs.COL_JOB_ID,jobIds)).isEmpty()){
            throw new BadRequestException(
                    BizCodeEnum.Client_Error_CRUD.getCode(),
                    "user与job存在关联，请先解除关联"
            );
        }
        //2. 删除
        removeByIds(jobIds);
    }

    @Override
    public void download(HttpServletResponse response) {
        List<SummaryJobDto> summaryJobDtos = jobMapper.toSummaryJobDtos(list());
        ExcelUtils.export(response,"岗位信息",summaryJobDtos,SummaryJobDto.class);
    }
}
