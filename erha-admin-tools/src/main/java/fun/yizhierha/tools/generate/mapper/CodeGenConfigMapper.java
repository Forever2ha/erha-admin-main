package fun.yizhierha.tools.generate.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.yizhierha.tools.generate.domain.CodeGenConfig;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodeGenConfigMapper extends BaseMapper<CodeGenConfig> {
}