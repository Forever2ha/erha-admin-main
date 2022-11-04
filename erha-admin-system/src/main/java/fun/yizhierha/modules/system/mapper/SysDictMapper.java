package fun.yizhierha.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.yizhierha.modules.system.domain.SysDict;
import fun.yizhierha.modules.system.service.dto.SummaryDictDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {
    List<SummaryDictDto> selectSummaryDictDtoList();

}