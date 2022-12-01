package fun.yizhierha.tools.generate.service.impl;

import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.utils.file.FileUtil;
import fun.yizhierha.tools.generate.domain.CodeColumnConfig;
import fun.yizhierha.tools.generate.domain.CodeGenConfig;
import fun.yizhierha.tools.generate.service.CodeColumnConfigService;
import fun.yizhierha.tools.generate.service.CodeGenConfigService;
import fun.yizhierha.tools.generate.service.GenerateService;
import fun.yizhierha.tools.generate.utils.ColUtil;
import fun.yizhierha.tools.generate.utils.GenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class GenerateServiceImpl implements GenerateService {

    @Autowired
    CodeGenConfigService codeGenConfigService;
    @Autowired
    CodeColumnConfigService codeColumnConfigService;

    @Override
    public List<Map<String, Object>> preview(String tableName) {
        List<CodeColumnConfig> codeColumnConfigList = getColumnConfig(tableName);
        CodeGenConfig codeGenConfig = getGenConfig(tableName);
        // 生成
        return GenUtil.preview(codeColumnConfigList,codeGenConfig);
    }

    private CodeGenConfig getGenConfig(String tableName) {
        CodeGenConfig one = codeGenConfigService.getOne(new QueryWrapper<CodeGenConfig>()
                .eq(CodeGenConfig.COL_TABLE_NAME, tableName));
        if (one == null) throw new BadRequestException("请先配置生成信息[step2]");
        return one;
    }

    private List<CodeColumnConfig> getColumnConfig(String tableName) {
        List<CodeColumnConfig> list = codeColumnConfigService.list(new QueryWrapper<CodeColumnConfig>()
                .eq(CodeColumnConfig.COL_TABLE_NAME, tableName
                ));
        if (list.isEmpty()) throw new BadRequestException("请先配置生成信息[step1]");
        return list;
    }

    @Override
    public void download(String tableName, HttpServletRequest request, HttpServletResponse response) {
        List<CodeColumnConfig> columnConfig = getColumnConfig(tableName);
        CodeGenConfig genConfig = getGenConfig(tableName);
        try {
            File file = new File(GenUtil.download(columnConfig, genConfig));
            String zipPath = file.getPath() + ".zip";
            ZipUtil.zip(file.getPath(), zipPath);
            FileUtil.downloadFile(request, response, new File(zipPath), true);
        } catch (IOException e) {
            throw new BadRequestException("打包失败");
        }
    }

    @Override
    public void generate(String tableName) {
        List<CodeColumnConfig> columnConfigList = getColumnConfig(tableName);
        CodeGenConfig genConfig = getGenConfig(tableName);
        try {
            GenUtil.generatorCode(columnConfigList,genConfig);
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

}
