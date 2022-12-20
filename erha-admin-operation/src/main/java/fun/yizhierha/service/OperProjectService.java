package fun.yizhierha.service;

import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.domain.vo.RetrieveProject;
import fun.yizhierha.service.dto.SummaryProjectDto;

public interface OperProjectService {

    PageUtils<SummaryProjectDto> listProject(RetrieveProject project, Query.PageVo pageVo);
}
