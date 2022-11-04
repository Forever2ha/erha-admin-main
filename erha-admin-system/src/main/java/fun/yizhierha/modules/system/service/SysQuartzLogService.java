package fun.yizhierha.modules.system.service;

import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.modules.system.domain.SysQuartzLog;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.modules.system.domain.vo.RetrieveQuartzLogVo;

import javax.servlet.http.HttpServletResponse;

public interface SysQuartzLogService extends IService<SysQuartzLog>{


    PageUtils<SysQuartzLog> listQuartzLog(RetrieveQuartzLogVo retrieveQuartzLogVo, Query.PageVo pageVo);

    void download(HttpServletResponse response);
}
