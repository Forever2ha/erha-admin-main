package fun.yizhierha.modules.system.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.modules.system.domain.SysQuartzJob;
import fun.yizhierha.modules.system.domain.vo.CreateQuartzJobVo;
import fun.yizhierha.modules.system.domain.vo.UpdateQuartzJobVo;
import fun.yizhierha.modules.system.service.dto.SummaryQuartzJobDto;
import org.mapstruct.*;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuartzJobMapper {

    @Mapping(source = "jobId",target = "id")
    SummaryQuartzJobDto toSummaryQuartzJobDto(SysQuartzJob sysJob);

    List<SummaryQuartzJobDto> toSummaryQuartzJobDtos(List<SysQuartzJob> SysQuartzJobs);

    Page<SummaryQuartzJobDto> toBeSummaryQuartzJobDto(IPage<SysQuartzJob> sysJobIPage);

    default Page<SummaryQuartzJobDto> toSummaryQuartzJobDto(IPage<SysQuartzJob> sysJobIPage){
        Page<SummaryQuartzJobDto> summaryQuartzJobDtoPage = toBeSummaryQuartzJobDto(sysJobIPage);
        summaryQuartzJobDtoPage.setRecords(toSummaryQuartzJobDtos(sysJobIPage.getRecords()));
        return  summaryQuartzJobDtoPage;
    }


    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "updateBy", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "createBy", ignore = true)
    @Mapping(target = "jobId", ignore = true)
    SysQuartzJob toSysQuartzJob(CreateQuartzJobVo createQuartzJobVo);


    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "updateBy", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "createBy", ignore = true)

    @Mapping(target = "jobId", source = "id")
    SysQuartzJob toSysQuartzJob(UpdateQuartzJobVo updateQuartzJobVo);

    List<SysQuartzJob> toSysQuartzJobList(List<UpdateQuartzJobVo> updateQuartzJobList);

    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "updateBy", ignore = true)
    @Mapping(target = "jobId", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "createBy", ignore = true)
    SysQuartzJob updateSysQuartzJob(UpdateQuartzJobVo updateQuartzJobVo, @MappingTarget SysQuartzJob sysQuartzJobTarget);


}
