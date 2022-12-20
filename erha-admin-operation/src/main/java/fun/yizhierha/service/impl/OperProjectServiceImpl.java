package fun.yizhierha.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.domain.OperProject;
import fun.yizhierha.domain.vo.RetrieveProject;
import fun.yizhierha.mapper.OperProjectMapper;
import fun.yizhierha.service.OperProjectService;
import fun.yizhierha.service.dto.SummaryProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperProjectServiceImpl extends ServiceImpl<OperProjectMapper, OperProject> implements OperProjectService {
    @Autowired(required = false)
    OperProjectMapper projectMapper;

    @Override
    public PageUtils<SummaryProjectDto> listProject(RetrieveProject project, Query.PageVo pageVo) {

//        IPage<SummaryProjectDto> page = projectMapper.selectList(new Query<SummaryProjectDto>().getPage(pageVo), project);
        return null;
    }
}
