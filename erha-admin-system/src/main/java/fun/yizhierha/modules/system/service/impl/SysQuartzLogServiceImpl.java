package fun.yizhierha.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.StringUtils;
import fun.yizhierha.common.utils.file.ExcelUtils;
import fun.yizhierha.modules.system.domain.vo.RetrieveQuartzLogVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.modules.system.mapper.SysQuartzLogMapper;
import fun.yizhierha.modules.system.domain.SysQuartzLog;
import fun.yizhierha.modules.system.service.SysQuartzLogService;
@Service
public class SysQuartzLogServiceImpl extends ServiceImpl<SysQuartzLogMapper, SysQuartzLog> implements SysQuartzLogService{

    @Override
    public PageUtils<SysQuartzLog> listQuartzLog(RetrieveQuartzLogVo retrieveQuartzLogVo, Query.PageVo pageVo) {
        QueryWrapper<SysQuartzLog> wr = new QueryWrapper<>();
        String name = retrieveQuartzLogVo.getName();
        Boolean isSuccess = retrieveQuartzLogVo.getIsSuccess();
        Timestamp startExecTime = retrieveQuartzLogVo.getStartExecTime();
        Timestamp endExecTime = retrieveQuartzLogVo.getEndExecTime();

        wr.like(StringUtils.isNotBlank(name),SysQuartzLog.COL_JOB_NAME,name);
        wr.between(startExecTime != null && endExecTime != null
                , SysQuartzLog.COL_EXEC_TIME, startExecTime, endExecTime);
        wr.eq(isSuccess != null,SysQuartzLog.COL_IS_SUCCESS,isSuccess);
        wr.orderBy(true,false,SysQuartzLog.COL_EXEC_TIME);

        IPage<SysQuartzLog> sysQuartzLogIPage = baseMapper.selectPage(new Query<SysQuartzLog>().getPage(pageVo), wr);
        return new PageUtils<>(sysQuartzLogIPage);
    }

    @Override
    public void download(HttpServletResponse response) {
        List<SysQuartzLog> list = list();
        ExcelUtils.export(response,"定时任务执行日志",list,SysQuartzLog.class);
    }
}
