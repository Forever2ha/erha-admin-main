package fun.yizhierha.modules.system.service.mapstruct;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.yizhierha.modules.system.domain.SysDict;
import fun.yizhierha.modules.system.service.dto.DictDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DictMapper {

    @Mapping(source = "dictId",target = "id")
    DictDto toDictDto(SysDict sysDict);

    List<DictDto> toDictDtos(List<SysDict> SysDicts);

    Page<DictDto> toBeDictDto(IPage<SysDict> sysDictIPage);

    default Page<DictDto> toDictDto(IPage<SysDict> sysDictIPage){
        Page<DictDto> DictDtoPage = toBeDictDto(sysDictIPage);
        DictDtoPage.setRecords(toDictDtos(sysDictIPage.getRecords()));
        return DictDtoPage;
    }
}
