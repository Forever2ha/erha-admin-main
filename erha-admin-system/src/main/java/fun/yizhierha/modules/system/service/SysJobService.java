package fun.yizhierha.modules.system.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysJob;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.modules.system.domain.vo.CreateJobVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveJobVo;
import fun.yizhierha.modules.system.domain.vo.UpdateJobVo;
import fun.yizhierha.modules.system.service.dto.JobDto;
import fun.yizhierha.modules.system.service.dto.SummaryJobDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public interface SysJobService extends IService<SysJob>{


    Set<SysJob> selectSysJobByUserId(Long userId);

    Set<JobDto> getJobDtoSetByUserIds(List<Long> userIds);

    PageUtils<SummaryJobDto> listJob(RetrieveJobVo retrieveJobVo, Query.PageVo pageVo);

    void save(CreateJobVo createJobVo, UserDetailsDto currentUser);

    void editJob(ValidList<UpdateJobVo> updateJobVoValidList, List<BaseErrDto> errDtos, UserDetailsDto currentUser);

    void removeJob(Set<Long> jobIds);

    void download(HttpServletResponse response);
}
