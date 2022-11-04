package fun.yizhierha.modules.system.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.yizhierha.common.base.BaseMapper;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysUser;
import fun.yizhierha.modules.system.domain.vo.CreateUserVo;
import fun.yizhierha.modules.system.service.dto.SummaryUserDto;
import fun.yizhierha.modules.system.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserDto, SysUser> {

    Page<SummaryUserDto> toBeSummaryUserDtoIPage(IPage<UserDto> sysUserIPage);

    List<SummaryUserDto> toSummaryUserDto(List<UserDto> userDtoList);

    @Mapping(target = "id",source = "userId")
    SummaryUserDto toSummaryUserDto(UserDto userDto);

    /**
     * 将Page中record的内容从UserDto换成SummaryUserDto,拷贝Page的基本属性
     * @param sysUserIPage
     * @return
     */
    default Page<SummaryUserDto> toSummaryUserDtoIPage(IPage<UserDto> sysUserIPage){
        Page<SummaryUserDto> userDetailsDtoPage = toBeSummaryUserDtoIPage(sysUserIPage);
        userDetailsDtoPage.setRecords(toSummaryUserDto(sysUserIPage.getRecords()));
        return userDetailsDtoPage;
    }

    SysUser toSysUser(CreateUserVo createUserVo);
}

