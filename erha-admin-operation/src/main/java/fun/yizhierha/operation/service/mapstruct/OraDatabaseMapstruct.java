package fun.yizhierha.operation.service.mapstruct;

import fun.yizhierha.operation.domain.vo.CreateOraDatabaseVo;
import fun.yizhierha.operation.domain.vo.UpdateOraDatabaseVo;
import org.mapstruct.Mapper;
import fun.yizhierha.operation.domain.OraDatabase;


/** generated by EH-Admin
* @author xaopohi
* @date Wed Dec 21 16:08:00 CST 2022
**/
@Mapper(componentModel = "spring")
public interface OraDatabaseMapstruct{

    OraDatabase toOraDatabase(UpdateOraDatabaseVo updateOraDatabaseVo);

    OraDatabase toOraDatabase(CreateOraDatabaseVo createOraDatabaseVo);

}