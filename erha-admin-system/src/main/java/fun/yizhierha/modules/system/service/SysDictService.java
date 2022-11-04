package fun.yizhierha.modules.system.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.modules.system.domain.vo.CreateDictVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveDictVo;
import fun.yizhierha.modules.system.domain.vo.UpdateDictVo;
import fun.yizhierha.modules.system.service.dto.DictDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public interface SysDictService extends IService<SysDict>{


    PageUtils<DictDto> listDict(RetrieveDictVo retrieveDictVo, Query.PageVo pageVo);

    void saveDict(CreateDictVo createDictVo, UserDetailsDto currentUser);

    void editDict(ValidList<UpdateDictVo> updateDictVos, List<BaseErrDto> errDtoList, UserDetailsDto currentUser);

    void removeDict(Set<Long> dictIds);

    void download(HttpServletResponse response);

}
