package fun.yizhierha.modules.system.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysQuartzJob;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.modules.system.domain.vo.CreateQuartzJobVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveQuartzJobVo;
import fun.yizhierha.modules.system.domain.vo.UpdateQuartzJobVo;
import fun.yizhierha.modules.system.service.dto.SummaryQuartzJobDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public interface SysQuartzJobService extends IService<SysQuartzJob>{


    PageUtils<SummaryQuartzJobDto> listQuartzJob(RetrieveQuartzJobVo retrieveQuartzJobVo, Query.PageVo pageVo);

    void toggleIsPause(SysQuartzJob sysQuartzJob);

    void toggleIsPause(Long jobId);

    void saveQuartzJob(CreateQuartzJobVo createQuartzJobVo, UserDetailsDto currentUser);

    void editQuartzJob(ValidList<UpdateQuartzJobVo> updateQuartzJobList, List<BaseErrDto> errDtoList, UserDetailsDto currentUser);

    void removeJob(Set<Long> jobIds);

    void download(HttpServletResponse response);
}
