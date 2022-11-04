package fun.yizhierha.modules.system.service;

import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.modules.system.domain.vo.UpdateUserVo;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.modules.system.domain.vo.CreateUserVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveUserVo;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.modules.system.service.dto.SummaryUserDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface SysUserService extends IService<SysUser>{

    /**
     * 根据用户username查找
     * @param username 账号
     * @return UserDetailsDto
     */
    UserDetailsDto selectUserDetailDtoByUsername(String username);

    /**
     * 获取所有用户的列表
     * @param retrieveUserVo 查询的参数
     * @param pageVo 分页信息
     * @return 有分页信息和user列表的PageUtils封装类
     */
    PageUtils<SummaryUserDto> listUser(RetrieveUserVo retrieveUserVo, Query.PageVo pageVo);

    /**
     * 新增用户
     * @param createUserVo
     */
    void save(CreateUserVo createUserVo);

    /**
     * 修改用户
     * @param updateUserVoList 要修改用户的vo
     * @param res 修改结果
     * @param userDetailsDto 当前用户信息
     */
    void editUser(ValidList<UpdateUserVo> updateUserVoList, List<BaseErrDto> res, UserDetailsDto userDetailsDto);

    /**
     * 导出所有用户数据
     * @param response
     */
    void download(HttpServletResponse response) throws IOException;
}
