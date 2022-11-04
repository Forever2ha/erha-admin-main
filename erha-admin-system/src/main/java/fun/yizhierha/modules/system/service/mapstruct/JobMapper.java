package fun.yizhierha.modules.system.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.yizhierha.modules.system.domain.SysJob;
import fun.yizhierha.modules.system.domain.SysJob;
import fun.yizhierha.modules.system.domain.vo.CreateJobVo;
import fun.yizhierha.modules.system.domain.vo.UpdateJobVo;
import fun.yizhierha.modules.system.service.dto.SummaryJobDto;
import fun.yizhierha.modules.system.service.dto.SummaryJobDto;
import fun.yizhierha.modules.system.service.dto.SummaryUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface JobMapper {

    Set<SummaryUserDto.Job> toSummaryUserDtoJobSet (Set<SysJob> sysJobs);

    default SummaryUserDto.Job map(SysJob sysJob){
        SummaryUserDto.Job job = new SummaryUserDto.Job();
        job.setId(sysJob.getJobId());
        job.setName(sysJob.getName());
        return job;
    }



    @Mapping(source = "jobId",target = "id")
    SummaryJobDto toSummaryJobDto(SysJob sysJob);

    List<SummaryJobDto> toSummaryJobDtos(List<SysJob> SysJobs);

    Page<SummaryJobDto> toBeSummaryJobDto(IPage<SysJob> sysJobIPage);

    default Page<SummaryJobDto> toSummaryJobDto(IPage<SysJob> sysJobIPage){
        Page<SummaryJobDto> summaryJobDtoPage = toBeSummaryJobDto(sysJobIPage);
        summaryJobDtoPage.setRecords(toSummaryJobDtos(sysJobIPage.getRecords()));
        return  summaryJobDtoPage;
    }

    SysJob toSysJob(CreateJobVo createJobVo);

    @Mapping(source = "id",target = "jobId")
    SysJob toSysJob(UpdateJobVo updateJobVo);

    List<SysJob> toSysJobList(List<UpdateJobVo> updateJobVoList);
}
