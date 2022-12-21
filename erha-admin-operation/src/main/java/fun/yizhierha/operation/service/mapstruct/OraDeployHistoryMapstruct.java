package fun.yizhierha.operation.service.mapstruct;

import fun.yizhierha.operation.domain.vo.CreateOraDeployHistoryVo;
import fun.yizhierha.operation.domain.vo.UpdateOraDeployHistoryVo;
import org.mapstruct.Mapper;
import fun.yizhierha.operation.domain.OraDeployHistory;


/** generated by EH-Admin
* @author xaopohi
* @date Wed Dec 21 15:55:14 CST 2022
**/
@Mapper(componentModel = "spring")
public interface OraDeployHistoryMapstruct{

    OraDeployHistory toOraDeployHistory(UpdateOraDeployHistoryVo updateOraDeployHistoryVo);

    OraDeployHistory toOraDeployHistory(CreateOraDeployHistoryVo createOraDeployHistoryVo);

}