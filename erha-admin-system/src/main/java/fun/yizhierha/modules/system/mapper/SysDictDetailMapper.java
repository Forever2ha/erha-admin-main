package fun.yizhierha.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.yizhierha.modules.system.domain.SysDictDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysDictDetailMapper extends BaseMapper<SysDictDetail> {
    List<SysDictDetail> selectByDictName(@Param("dictName") String dictName);
}