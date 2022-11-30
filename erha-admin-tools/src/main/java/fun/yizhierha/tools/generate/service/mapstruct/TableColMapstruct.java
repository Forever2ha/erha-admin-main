package fun.yizhierha.tools.generate.service.mapstruct;

import fun.yizhierha.tools.generate.domain.CodeColumnConfig;
import fun.yizhierha.tools.generate.domain.vo.UpdateTableColVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface TableColMapstruct {

    @Mapping(target = "tableName", ignore = true)
    @Mapping(target = "keyType", ignore = true)
    @Mapping(target = "extra", ignore = true)
    @Mapping(target = "columnName", ignore = true)

    @Mapping(target = "columnId", source = "id")
    CodeColumnConfig toCodeColumnConfig(UpdateTableColVo updateTableColVo);

    @Mapping(target = "tableName", ignore = true)
    @Mapping(target = "keyType", ignore = true)
    @Mapping(target = "extra", ignore = true)
    @Mapping(target = "columnName", ignore = true)
    @Mapping(target = "columnId", ignore = true)
    CodeColumnConfig mergeUpdateVoToEntity(@MappingTarget CodeColumnConfig columnConfig, UpdateTableColVo updateTableColVo);
}
