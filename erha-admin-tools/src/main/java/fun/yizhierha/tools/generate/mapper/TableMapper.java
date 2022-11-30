package fun.yizhierha.tools.generate.mapper;

import fun.yizhierha.common.utils.Query;
import fun.yizhierha.tools.generate.domain.CodeColumnConfig;
import fun.yizhierha.tools.generate.domain.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TableMapper {

    List<TableInfo> selectTables(@Param("tableName") String tableName,@Param("l1")int l1, @Param("l2") Integer pageSize);

    int selectTableCounts();

    List<CodeColumnConfig> selectTableCols(@Param("tableName") String tableName);
}
