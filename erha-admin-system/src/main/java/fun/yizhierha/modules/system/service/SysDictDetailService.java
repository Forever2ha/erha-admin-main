package fun.yizhierha.modules.system.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysDictDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.modules.system.domain.vo.CreateDictDetailVo;
import fun.yizhierha.modules.system.domain.vo.UpdateDictDetailVo;
import fun.yizhierha.modules.system.service.dto.DictDetailDto;

import java.util.List;
import java.util.Set;

public interface SysDictDetailService extends IService<SysDictDetail>{


    List<SysDictDetail> listByDictName(String dictName);

    PageUtils<DictDetailDto> listDictDetail(Long dictId, Query.PageVo pageVo);

    void saveDictDetail(CreateDictDetailVo createDictDetailVo, UserDetailsDto currentUser);

    void updateDictDetail(ValidList<UpdateDictDetailVo> updateDictDetailVos, List<BaseErrDto> errDtoList, UserDetailsDto currentUser);

    void removeDictDetail(Set<Long> dictDetailIds);

}
