package fun.yizhierha.tools.other.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.tools.other.domain.ToolEmailConfig;
import fun.yizhierha.tools.other.domain.vo.CreateToolEmailConfigVo;
import fun.yizhierha.tools.other.domain.vo.UpdateToolEmailConfigVo;
import fun.yizhierha.tools.other.domain.vo.RetrieveToolEmailConfigVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/** generated by EH-Admin
* @author 二哈
* @date Fri Jan 13 20:37:24 CST 2023
**/
public interface ToolEmailConfigService extends IService<ToolEmailConfig>{

    PageUtils<ToolEmailConfig> list(RetrieveToolEmailConfigVo retrieveToolEmailConfigVo, Query.PageVo pageVo);

    void save(CreateToolEmailConfigVo createToolEmailConfigVo);

    void edit(ValidList<UpdateToolEmailConfigVo> updateToolEmailConfigVoList, List<BaseErrDto> errDtoList);

    void remove(Set<Long> ids);

    void download(HttpServletResponse response);

}