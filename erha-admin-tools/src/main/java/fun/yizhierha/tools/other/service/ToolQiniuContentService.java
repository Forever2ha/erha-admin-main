package fun.yizhierha.tools.other.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.tools.other.domain.ToolQiniuContent;
import fun.yizhierha.tools.other.domain.vo.UpdateToolQiniuContentVo;
import fun.yizhierha.tools.other.domain.vo.RetrieveToolQiniuContentVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** generated by EH-Admin
* @author erha
* @date Sun Jan 08 14:05:28 CST 2023
**/
public interface ToolQiniuContentService extends IService<ToolQiniuContent>{

    PageUtils<ToolQiniuContent> list(RetrieveToolQiniuContentVo retrieveToolQiniuContentVo, Query.PageVo pageVo);


    void edit(ValidList<UpdateToolQiniuContentVo> updateToolQiniuContentVoList, List<BaseErrDto> errDtoList);

    Map<String, Object> remove(Set<Long> ids);

    void download(HttpServletResponse response);

    ToolQiniuContent save(MultipartFile file);
}