package fun.yizhierha.operation.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.operation.domain.OraDeployHistory;
import fun.yizhierha.operation.domain.vo.CreateOraDeployHistoryVo;
import fun.yizhierha.operation.domain.vo.UpdateOraDeployHistoryVo;
import fun.yizhierha.operation.domain.vo.RetrieveOraDeployHistoryVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/** generated by EH-Admin
* @author xaopohi
* @date Wed Dec 21 15:55:14 CST 2022
**/
public interface OraDeployHistoryService extends IService<OraDeployHistory>{

    PageUtils<OraDeployHistory> list(RetrieveOraDeployHistoryVo retrieveOraDeployHistoryVo, Query.PageVo pageVo);

    void save(CreateOraDeployHistoryVo createOraDeployHistoryVo);

    void edit(ValidList<UpdateOraDeployHistoryVo> updateOraDeployHistoryVoList, List<BaseErrDto> errDtoList);

    void remove(Set<Long> ids);

    void download(HttpServletResponse response);

}