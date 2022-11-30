package ${package}.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import com.baomidou.mybatisplus.extension.service.IService;
import ${package}.domain.${className};
import ${package}.domain.vo.Create${className}Vo;
import ${package}.domain.vo.Update${className}Vo;
import ${package}.domain.vo.Retrieve${className}Vo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/** generated by EH-Admin
* @author ${author}
* @date ${date}
**/
public interface ${className}Service extends IService<${className}>{

    PageUtils<${className}> list(Retrieve${className}Vo retrieve${className}Vo, Query.PageVo pageVo);

    void save(Create${className}Vo create${className}Vo);

    void edit(ValidList<Update${className}Vo> update${className}VoList, List<BaseErrDto> errDtoList);

    void remove(Set<Long> ids);

    void download(HttpServletResponse response);

}