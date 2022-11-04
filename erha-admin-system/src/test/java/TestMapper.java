import fun.yizhierha.modules.system.domain.SysUser;
import fun.yizhierha.modules.system.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TestMapper{

    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);

    UserDto sysUserToUserDto(SysUser sysUser);
}