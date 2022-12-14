package fun.yizhierha.tools.generate.service;

import fun.yizhierha.tools.generate.domain.CodeGenConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface GenerateService {
    List<Map<String, Object>> preview(String tableName);


    void download(String tableName, HttpServletRequest request, HttpServletResponse response);

    void generate(String tableName);

}
