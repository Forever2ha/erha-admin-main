package fun.yizhierha.tools.generate.service;

import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.tools.generate.domain.CodeColumnConfig;
import fun.yizhierha.tools.generate.domain.TableInfo;
import fun.yizhierha.tools.generate.domain.vo.UpdateTableColVo;

import java.util.List;

public interface TableService {
    PageUtils<TableInfo> list(String tableName, Query.PageVo pageVo);

    List<CodeColumnConfig> listTableCols(String tableName);

    void updateTableCol(ValidList<UpdateTableColVo> updateTableColVoValidList, List<BaseErrDto> baseErrDtoList);

    void syncTableColumns(String tableName);
}
