package fun.yizhierha.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import fun.yizhierha.modules.system.domain.SysUser;
import fun.yizhierha.modules.system.domain.vo.RetrieveUserVo;
import fun.yizhierha.modules.system.service.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    UserDto selectUserDtoByUsername(@Param("username") String username);


    IPage<UserDto> selectListByUserQueryVo(IPage<UserDto> page, @Param("retrieveUserVo") RetrieveUserVo retrieveUserVo);
}