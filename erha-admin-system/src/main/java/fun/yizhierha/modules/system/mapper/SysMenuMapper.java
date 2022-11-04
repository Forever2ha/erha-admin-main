package fun.yizhierha.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import fun.yizhierha.modules.system.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> selectByRoleIds(@Param("roleIds") List<Long> roleIds);

    IPage<SysMenu> test(IPage<SysMenu> page, @Param("id") Integer id);
}