package fun.yizhierha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import fun.yizhierha.domain.OperProject;
import fun.yizhierha.domain.vo.RetrieveProject;
import fun.yizhierha.service.dto.SummaryProjectDto;
import org.apache.ibatis.annotations.Param;

public interface OperProjectMapper extends BaseMapper<OperProject> {
}
