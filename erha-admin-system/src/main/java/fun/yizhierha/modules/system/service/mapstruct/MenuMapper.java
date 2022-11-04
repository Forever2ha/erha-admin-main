package fun.yizhierha.modules.system.service.mapstruct;

import fun.yizhierha.modules.system.domain.SysMenu;
import fun.yizhierha.modules.system.domain.vo.CreateMenuVo;
import fun.yizhierha.modules.system.domain.vo.UpdateMenuVo;
import fun.yizhierha.modules.system.service.dto.MenuDto;
import fun.yizhierha.modules.system.service.dto.SummaryMenuDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring",imports = {Collections.class})
public interface MenuMapper {

    @Mapping(target = "meta", ignore = true)
    @Mapping(target = "children", ignore = true)
    MenuDto toBeMenuDto(SysMenu sysMenu);

    @Mapping(target = "requiresAuth", ignore = true)
    @Mapping(target = "ignoreCache", expression = "java(!sysMenu.getCache())")
    @Mapping(target = "hideInMenu", source = "hidden")
    @Mapping(target = "roles", expression = "java(Collections.singletonList(sysMenu.getPermission() != null ? sysMenu.getPermission() : \"*\"))")
    MenuDto.Meta toMenuDtoMeta(SysMenu sysMenu);



    default MenuDto toMenuDto(SysMenu sysMenu){

        MenuDto menuDto = toBeMenuDto(sysMenu);
        MenuDto.Meta meta = toMenuDtoMeta(sysMenu);
        menuDto.setMeta(meta);
        return menuDto;
    }

    @Mapping(target = "id", source = "menuId")
    SummaryMenuDto toSummaryMenuDto(SysMenu sysMenu);

    List<SummaryMenuDto> toSummaryMenuDtoList(List<SysMenu> sysMenu);


    @Mapping(target = "createTime", expression = "java(new java.util.Date())")
    @Mapping(target = "createBy", expression = "java(username)")

    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "updateBy", ignore = true)
    @Mapping(target = "subCount", ignore = true)
    @Mapping(target = "menuId", ignore = true)
    SysMenu toSysMenu(CreateMenuVo createMenuVo, String username);

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "subCount", ignore = true)
    @Mapping(target = "pid", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "createBy", ignore = true)

    @Mapping(target = "updateTime", expression = "java(new java.util.Date())")
    @Mapping(target = "updateBy", expression = "java(username)")
    @Mapping(target = "menuId", source = "updateMenuVo.id")
    SysMenu toSysMenu(UpdateMenuVo updateMenuVo, String username);
}
