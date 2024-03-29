package fun.yizhierha.tools.other.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.tools.other.domain.ToolLocalStorage;
import fun.yizhierha.tools.other.domain.vo.CreateToolLocalStorageVo;
import fun.yizhierha.tools.other.domain.vo.UpdateToolLocalStorageVo;
import fun.yizhierha.tools.other.domain.vo.RetrieveToolLocalStorageVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/** generated by EH-Admin
* @author erha
* @date Sun Jan 01 17:11:43 CST 2023
**/
public interface ToolLocalStorageService extends IService<ToolLocalStorage>{

    PageUtils<ToolLocalStorage> list(RetrieveToolLocalStorageVo retrieveToolLocalStorageVo, Query.PageVo pageVo);

    void save(CreateToolLocalStorageVo createToolLocalStorageVo, MultipartFile file);

    void edit(ValidList<UpdateToolLocalStorageVo> updateToolLocalStorageVoList, List<BaseErrDto> errDtoList);

    void remove(Set<Long> ids);

    void download(HttpServletResponse response);

}