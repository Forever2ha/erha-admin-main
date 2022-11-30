package fun.yizhierha.test.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.test.domain.TestStudent;
import fun.yizhierha.test.domain.vo.CreateTestStudentVo;
import fun.yizhierha.test.domain.vo.UpdateTestStudentVo;
import fun.yizhierha.test.domain.vo.RetrieveTestStudentVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/** generated by EH-Admin
* @author 二哈
* @date Sat Nov 26 15:55:33 CST 2022
**/
public interface TestStudentService extends IService<TestStudent>{

    PageUtils<TestStudent> list(RetrieveTestStudentVo retrieveTestStudentVo, Query.PageVo pageVo);

    void save(CreateTestStudentVo createTestStudentVo);

    void edit(ValidList<UpdateTestStudentVo> updateTestStudentVoList, List<BaseErrDto> errDtoList);

    void remove(Set<Long> ids);

    void download(HttpServletResponse response);

}