package fun.yizhierha.operation.service.mapstruct;

import fun.yizhierha.operation.domain.vo.CreateOraServerVo;
import fun.yizhierha.operation.domain.vo.UpdateOraServerVo;
import org.mapstruct.Mapper;
import fun.yizhierha.operation.domain.OraServer;


/** generated by EH-Admin
* @author xaopohi
* @date Wed Dec 21 13:25:19 CST 2022
**/
@Mapper(componentModel = "spring")
public interface OraServerMapstruct{

    OraServer toOraServer(UpdateOraServerVo updateOraServerVo);

    OraServer toOraServer(CreateOraServerVo createOraServerVo);

}