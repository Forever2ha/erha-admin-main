package fun.yizhierha.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.yizhierha.modules.system.domain.SysRolesDepts;
import fun.yizhierha.modules.system.service.dto.UntreatedDataScopeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRolesDeptsMapper extends BaseMapper<SysRolesDepts> {

    List<UntreatedDataScopeDto> selectUntreatedDataScopeByRolesId(@Param("roleIds") List<Long> roleIds);
}