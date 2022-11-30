package fun.yizhierha.tools.generate.service.impl;

import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.utils.StringUtils;
import fun.yizhierha.tools.generate.domain.CodeGenConfig;
import fun.yizhierha.tools.generate.domain.vo.UpdateOrSaveCodeGenConfigVo;
import fun.yizhierha.tools.generate.mapper.CodeGenConfigMapper;
import fun.yizhierha.tools.generate.service.CodeGenConfigService;
import fun.yizhierha.tools.generate.service.mapstruct.CodeGenConfigMapstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
@RequiredArgsConstructor
public class CodeGenConfigServiceImpl extends ServiceImpl<CodeGenConfigMapper, CodeGenConfig> implements CodeGenConfigService {

     private final CodeGenConfigMapstruct codeGenConfigMapstruct;

    @Override
    public void saveOrUpdate(UpdateOrSaveCodeGenConfigVo updateOrSaveCodeGenConfigVo) {
        String tableName = updateOrSaveCodeGenConfigVo.getTableName();
        Long configId = updateOrSaveCodeGenConfigVo.getConfigId();
        Boolean cover = updateOrSaveCodeGenConfigVo.getCover();
        if (configId == null){
            // save
            // tableName不能为空
            if (StringUtils.isBlank(tableName)){
                throw new BadRequestException("新增： tableName不能为空");
            }
            save(codeGenConfigMapstruct.toCodeGenConfig(updateOrSaveCodeGenConfigVo));

        }else {
            // update
            // 默认是否
            if (cover == null) cover = false;
            // 禁止更改tableName
            updateOrSaveCodeGenConfigVo.setTableName(null);
            updateById(codeGenConfigMapstruct.toCodeGenConfig(updateOrSaveCodeGenConfigVo));
        }
    }
}