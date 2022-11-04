package fun.yizhierha.modules.system.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.modules.system.domain.vo.CreateDeptVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveDeptVo;
import fun.yizhierha.modules.system.domain.vo.UpdateDeptVo;
import fun.yizhierha.modules.system.service.dto.DeptDto;
import fun.yizhierha.modules.system.service.dto.SummaryDeptDto;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SysDeptService extends IService<SysDept>{


    List<Object> listForTree();

    DeptDto listForDeptDtoByDeptId(Long deptId);

    Set<Long> listForDeptIdsByDeptId(Long deptId);

    String[] getNameByRoleId(Long id);

    PageUtils<SummaryDeptDto> listDept(RetrieveDeptVo retrieveDeptVo, Query.PageVo pageVo);

    void save(CreateDeptVo createDeptVo, UserDetailsDto currentUser);

    void editDept(ValidList<UpdateDeptVo> updateDeptVos, List<BaseErrDto> errDtos, UserDetailsDto currentUser);

    void removeByIds(Set<Long> deptIds);

    void download(HttpServletResponse response);
}
