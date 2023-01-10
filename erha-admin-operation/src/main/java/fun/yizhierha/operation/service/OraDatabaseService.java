package fun.yizhierha.operation.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.operation.domain.OraDatabase;
import fun.yizhierha.operation.domain.vo.CreateOraDatabaseVo;
import fun.yizhierha.operation.domain.vo.UpdateOraDatabaseVo;
import fun.yizhierha.operation.domain.vo.RetrieveOraDatabaseVo;
import net.sf.jsqlparser.schema.Database;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/** generated by EH-Admin
* @author xaopohi
* @date Wed Dec 21 16:08:00 CST 2022
**/
public interface OraDatabaseService extends IService<OraDatabase>{

    PageUtils<OraDatabase> list(RetrieveOraDatabaseVo retrieveOraDatabaseVo, Query.PageVo pageVo);

    void save(CreateOraDatabaseVo createOraDatabaseVo);

    void edit(ValidList<UpdateOraDatabaseVo> updateOraDatabaseVoList, List<BaseErrDto> errDtoList);

    void remove(Set<Long> ids);

    void download(HttpServletResponse response);

    Object testConnection(OraDatabase resources);
}