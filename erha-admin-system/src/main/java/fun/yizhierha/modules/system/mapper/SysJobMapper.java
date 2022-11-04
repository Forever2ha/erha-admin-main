package fun.yizhierha.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.yizhierha.modules.system.domain.SysJob;
import fun.yizhierha.modules.system.service.dto.JobDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface SysJobMapper extends BaseMapper<SysJob> {
    Set<SysJob> selectSysJobByUserId(@Param("userId") Long userId);

    Set<JobDto> selectJobDtoSetByUserIds(@Param("userIds") List<Long> userIds);
}