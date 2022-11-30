package fun.yizhierha.tools.generate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.StringUtils;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.tools.generate.domain.CodeColumnConfig;
import fun.yizhierha.tools.generate.domain.TableInfo;
import fun.yizhierha.tools.generate.domain.vo.UpdateTableColVo;
import fun.yizhierha.tools.generate.mapper.TableMapper;
import fun.yizhierha.tools.generate.service.CodeColumnConfigService;
import fun.yizhierha.tools.generate.service.TableService;
import fun.yizhierha.tools.generate.service.mapstruct.TableColMapstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableMapper tableMapper;
    private final TableColMapstruct tableColMapstruct;


    @Autowired
    private CodeColumnConfigService codeColumnConfigService;

    @Override
    public PageUtils<TableInfo> list(String tableName, Query.PageVo pageVo) {
        int tableCounts = tableMapper.selectTableCounts();
        int l1 = (pageVo.getCurrentPage()-1) * pageVo.getPageSize();
        List<TableInfo> tableInfoList = tableMapper.selectTables(tableName,l1,pageVo.getPageSize());

        return new PageUtils<>(tableInfoList,tableCounts, pageVo.getPageSize(), pageVo.getCurrentPage());
    }

    @Override
    public List<CodeColumnConfig> listTableCols(String tableName) {
        List<CodeColumnConfig> codeColumnConfigList = codeColumnConfigService.list(new QueryWrapper<CodeColumnConfig>().eq(CodeColumnConfig.COL_TABLE_NAME, tableName));
        if (codeColumnConfigList.isEmpty()){
            codeColumnConfigList = tableMapper.selectTableCols(tableName);
            codeColumnConfigService.saveBatch(codeColumnConfigList);
        }
        return codeColumnConfigList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateTableCol(ValidList<UpdateTableColVo> updateTableColVoValidList, List<BaseErrDto> baseErrDtoList) {
        ArrayList<CodeColumnConfig> toUpdateList = new ArrayList<>(updateTableColVoValidList.size());

        for (UpdateTableColVo updateTableColVo : updateTableColVoValidList) {
            CodeColumnConfig columnConfig = codeColumnConfigService.getById(updateTableColVo.getId());
            CodeColumnConfig mergeConfig = tableColMapstruct.mergeUpdateVoToEntity(columnConfig,updateTableColVo);

            // 1. 表单类型是 文本框、文本域、日期框 当且仅当 关联字典!=null
            String formType = mergeConfig.getFormType();
            String dictName = mergeConfig.getDictName();
            if ((StringUtils.isNoneBlank(formType) && !"文本框".equals(formType) && !"文本域".equals(formType) && !"日期框".equals(formType)
                    && StringUtils.isBlank(dictName))
                 ||
                 (StringUtils.isNoneBlank(dictName) && (("文本框".equals(formType) || "文本域".equals(formType) || "日期框".equals(formType))
                 ))
            ){
                BaseErrDto baseErrDto = new BaseErrDto();
                baseErrDto.setId(updateTableColVo.getId());
                baseErrDto.setErrorField(CodeColumnConfig.COL_DICT_NAME);
                baseErrDto.setErrorVal("-");
                baseErrDto.setErrorMsg("表单类型是 文本框、文本域、日期框 当且仅当 关联字典!=null");
                baseErrDtoList.add(baseErrDto);
                continue;
            }
            // 2. formType === '开关[仅两个值]' 时 columnType === 'bit' && dict.length == 2
            String columnType = mergeConfig.getColumnType();
            if ("开关[仅两个值]".equals(formType) && ! "bit".equals(columnType)){
                BaseErrDto baseErrDto = new BaseErrDto();
                baseErrDto.setId(updateTableColVo.getId());
                baseErrDto.setErrorField(CodeColumnConfig.COL_FORM_TYPE);
                baseErrDto.setErrorVal(formType);
                baseErrDto.setErrorMsg("formType === '开关[仅两个值]' 时 columnType === 'bit' && dict.length == 2");
                baseErrDtoList.add(baseErrDto);
                continue;
            }
            // 3.formType == '下拉框[多选]' 时 数据库字段类型必须是字符串类型
            if ("下拉框[多选]".equals(formType) &&
                    !("char".equals(columnType) || "varchar".equals(columnType) || "tinytext".equals(columnType)
            || "text".equals(columnType) || "mediumtext".equals(columnType) || "longtext".equals(columnType))
            ){
                BaseErrDto baseErrDto = new BaseErrDto();
                baseErrDto.setId(updateTableColVo.getId());
                baseErrDto.setErrorField(CodeColumnConfig.COL_FORM_TYPE);
                baseErrDto.setErrorVal(formType);
                baseErrDto.setErrorMsg("formType == '下拉框[多选]' 时 数据库字段类型必须是字符串类型");
                baseErrDtoList.add(baseErrDto);
                continue;
            }


            toUpdateList.add(tableColMapstruct.toCodeColumnConfig(updateTableColVo));
        }
        codeColumnConfigService.updateBatchById(toUpdateList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncTableColumns(String tableName) {
        // 1. 获取原有信息,新信息
        List<CodeColumnConfig> codeColumnConfigList = codeColumnConfigService.list(new QueryWrapper<CodeColumnConfig>().eq(CodeColumnConfig.COL_TABLE_NAME, tableName));
        List<CodeColumnConfig> newCodeColumnConfigList = tableMapper.selectTableCols(tableName);
        List<CodeColumnConfig> finalList = new ArrayList<>();
        if (codeColumnConfigList.size() == 0){
            throw new BadRequestException("无法同步"+tableName+": 该表信息不存在");
        }
        // 2. 合并
        for (CodeColumnConfig newConfig : newCodeColumnConfigList) {
            CodeColumnConfig consistConfig = findConsistConfig(newConfig,codeColumnConfigList);
            if (consistConfig == null){
                finalList.add(newConfig);
            }else {
                consistConfig.setColumnId(null);
                finalList.add(consistConfig);
            }
        }
        // 3. 删除原有信息
        codeColumnConfigService.remove(new QueryWrapper<CodeColumnConfig>().eq(CodeColumnConfig.COL_TABLE_NAME, tableName));
        // 4. 保存
        codeColumnConfigService.saveBatch(finalList);
    }

    private CodeColumnConfig findConsistConfig(CodeColumnConfig newConfig, List<CodeColumnConfig> list) {
        // 以下5项如果相同则认为此列信息未改变
        String columnName = newConfig.getColumnName();
        String columnType = newConfig.getColumnType();
        String extra = newConfig.getExtra();
        String keyType = newConfig.getKeyType();
        Boolean notNull = newConfig.getNotNull();
        for (CodeColumnConfig o : list) {
            if (columnName.equals(o.getColumnName())){
                if (
                       columnType.equals(o.getColumnType()) &&
                       extra.equals(o.getExtra()) &&
                       keyType.equals(o.getKeyType()) &&
                       notNull.equals(o.getNotNull())
                ){
                    return o;
                }else {
                    return null;
                }
            }
        }
        return null;
    }


}
