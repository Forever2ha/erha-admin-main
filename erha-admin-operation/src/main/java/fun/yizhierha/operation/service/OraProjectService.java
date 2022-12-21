package fun.yizhierha.operation.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.operation.domain.OraProject;
import fun.yizhierha.operation.domain.vo.CreateOraProjectVo;
import fun.yizhierha.operation.domain.vo.UpdateOraProjectVo;
import fun.yizhierha.operation.domain.vo.RetrieveOraProjectVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/** generated by EH-Admin
* @author xaopohi
* @date Wed Dec 21 12:40:38 CST 2022
**/
public interface OraProjectService extends IService<OraProject>{

    PageUtils<OraProject> list(RetrieveOraProjectVo retrieveOraProjectVo, Query.PageVo pageVo);

    void save(CreateOraProjectVo createOraProjectVo);

    void edit(ValidList<UpdateOraProjectVo> updateOraProjectVoList, List<BaseErrDto> errDtoList);

    void remove(Set<Long> ids);

    void download(HttpServletResponse response);

}