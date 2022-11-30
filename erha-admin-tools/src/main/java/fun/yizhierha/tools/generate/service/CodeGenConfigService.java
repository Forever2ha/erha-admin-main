package fun.yizhierha.tools.generate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.tools.generate.domain.CodeGenConfig;
import fun.yizhierha.tools.generate.domain.vo.UpdateOrSaveCodeGenConfigVo;

public interface CodeGenConfigService extends IService<CodeGenConfig>{


    void saveOrUpdate(UpdateOrSaveCodeGenConfigVo updateOrSaveCodeGenConfigVo);
}
