package fun.yizhierha.tools.other.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.EncryptUtils;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.common.utils.file.ExcelUtils;
import fun.yizhierha.tools.other.domain.ToolQiniuConfig;
import fun.yizhierha.tools.other.domain.ToolQiniuContent;
import fun.yizhierha.tools.other.domain.vo.CreateToolQiniuConfigVo;
import fun.yizhierha.tools.other.domain.vo.UpdateToolQiniuConfigVo;
import fun.yizhierha.tools.other.domain.vo.RetrieveToolQiniuConfigVo;
import fun.yizhierha.tools.other.mapper.ToolQiniuConfigMapper;
import fun.yizhierha.tools.other.service.mapstruct.ToolQiniuConfigMapstruct;
import fun.yizhierha.tools.other.service.ToolQiniuConfigService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

/** generated by EH-Admin
* @author erha
* @date Sun Jan 08 13:57:58 CST 2023
**/
@Service
@RequiredArgsConstructor
public class ToolQiniuConfigServiceImpl extends ServiceImpl<ToolQiniuConfigMapper, ToolQiniuConfig> implements ToolQiniuConfigService{

    private final ToolQiniuConfigMapstruct toolQiniuConfigMapstruct;

    @Override
    public PageUtils<ToolQiniuConfig> list(RetrieveToolQiniuConfigVo retrieveToolQiniuConfigVo, Query.PageVo pageVo) {
        QueryWrapper<ToolQiniuConfig> wrapper = new QueryWrapper<>();
        Long configId = retrieveToolQiniuConfigVo.getConfigId();
        Boolean active = retrieveToolQiniuConfigVo.getActive();
        if (configId != null){
            wrapper.eq(ToolQiniuConfig.COL_CONFIG_ID,configId);
        }
        if (active != null){
            wrapper.eq(ToolQiniuConfig.COL_ACTIVE,active);
        }

        IPage<ToolQiniuConfig> iPage = baseMapper.selectPage(new Query<ToolQiniuConfig>().getPage(pageVo), wrapper);
        return new PageUtils<>(iPage);
    }

    @Override
    public synchronized void save(CreateToolQiniuConfigVo createToolQiniuConfigVo) {
        // 1.字段为UNI，需要不重复
        // 2.映射数据
        ToolQiniuConfig toolQiniuConfig = toolQiniuConfigMapstruct.toToolQiniuConfig(createToolQiniuConfigVo);
        toolQiniuConfig.setActive(false);
        // 加密保存
        toolQiniuConfig.setAccessKey(EncryptUtils.desEncrypt(toolQiniuConfig.getAccessKey()));
        toolQiniuConfig.setSecretKey(EncryptUtils.desEncrypt(toolQiniuConfig.getSecretKey()));
        // 3.保存
        this.save(toolQiniuConfig);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized void edit(ValidList<UpdateToolQiniuConfigVo> updateToolQiniuConfigVoList, List<BaseErrDto> errDtoList) {
        List<ToolQiniuConfig> toUpdateToolQiniuConfigList = new ArrayList<>();

        for (UpdateToolQiniuConfigVo updateToolQiniuConfigVo : updateToolQiniuConfigVoList) {
            Long id = updateToolQiniuConfigVo.getId();
            // 1.字段为UNI，需要不重复

            ToolQiniuConfig toolQiniuConfig = toolQiniuConfigMapstruct.toToolQiniuConfig(updateToolQiniuConfigVo);

            toUpdateToolQiniuConfigList.add(toolQiniuConfig);
        }

        // 2.更新
        this.updateBatchById(toUpdateToolQiniuConfigList);
    }

    @Override
    public void remove(Set<Long> ids) {
        this.removeByIds(ids);
    }

    @Override
    public void download(HttpServletResponse response) {
        ExcelUtils.export(response,"七牛云信息表",this.list(), ToolQiniuConfig.class);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void activeConfig(Long id) {

        if (id == null) throw new BadRequestException("id不能为空");
        // 把激活状态的改为未激活
        this.update(
                new UpdateWrapper<ToolQiniuConfig>()
                        .eq(ToolQiniuConfig.COL_ACTIVE,true)
                        .set(ToolQiniuConfig.COL_ACTIVE,false)
        );
        // 修改当前id对应的active为激活
        if (!this.update(new UpdateWrapper<ToolQiniuConfig>()
                .eq(ToolQiniuConfig.COL_CONFIG_ID,id)
                .set(ToolQiniuConfig.COL_ACTIVE,true))){
            throw new BadRequestException("不存在此ID的配置");
        }

    }

    @Override
    public List<ToolQiniuConfig> getActiveConfig() {
        List<ToolQiniuConfig> toolQiniuConfigList = list(new QueryWrapper<ToolQiniuConfig>().eq(ToolQiniuConfig.COL_ACTIVE, true));
        for (ToolQiniuConfig cfg : toolQiniuConfigList) {
            // 解密
            try {
                cfg.setAccessKey(EncryptUtils.desDecrypt(cfg.getAccessKey()));
                cfg.setSecretKey(EncryptUtils.desDecrypt(cfg.getSecretKey()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return toolQiniuConfigList;
    }

}