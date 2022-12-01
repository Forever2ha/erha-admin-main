/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package fun.yizhierha.tools.generate.utils;

import cn.hutool.Hutool;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.*;
import fun.yizhierha.common.utils.StringUtils;
import fun.yizhierha.common.utils.file.FileUtil;
import fun.yizhierha.tools.generate.domain.CodeColumnConfig;
import fun.yizhierha.tools.generate.domain.CodeGenConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.*;

import static fun.yizhierha.common.utils.file.FileUtil.SYS_TEM_DIR;

/**
 * 代码生成
 *
 * @author Zheng Jie
 * @date 2019-01-02
 */
@Slf4j
@SuppressWarnings({"unchecked", "all"})
public class GenUtil {

    private static final String TIMESTAMP = "Timestamp";

    private static final String BIGDECIMAL = "BigDecimal";

    public static final String PK = "PRI";

    public static final String EXTRA = "auto_increment";

    /**
     * 获取后端代码模板名称
     *
     * @return List
     */
    private static List<String> getAdminTemplateNames() {
        List<String> templateNames = new ArrayList<>();
        templateNames.add("Controller");
        templateNames.add("Service");
        templateNames.add("ServiceImpl");
        templateNames.add("Mapstruct");
        templateNames.add("CreateVo");
        templateNames.add("UpdateVo");
        templateNames.add("RetrieveVo");
        templateNames.add("Mapper");
        templateNames.add("MapperXml");
        templateNames.add("Entity");

        return templateNames;
    }

    /**
     * 获取前端代码模板名称
     *
     * @return List
     */
    private static List<String> getFrontTemplateNames() {
        List<String> templateNames = new ArrayList<>();
        templateNames.add("index");
        templateNames.add("api");
        templateNames.add("en-US");
        templateNames.add("zh-CN");
        templateNames.add("router");
        return templateNames;
    }

    public static List<Map<String, Object>> preview(List<CodeColumnConfig> columns, CodeGenConfig genConfig) {
        Map<String, Object> genMap = getGenMap(columns, genConfig);

        List<Map<String, Object>> genList = new ArrayList<>();
        // 获取后端模版
        List<String> templates = getAdminTemplateNames();
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        for (String templateName : templates) {
            Map<String, Object> map = new HashMap<>(1);
            Template template = engine.getTemplate("generator/admin/" + templateName + ".ftl");
            map.put("content", template.render(genMap));
            map.put("name", templateName);
            genList.add(map);
        }

        // 获取前端模版
        templates = getFrontTemplateNames();
        for (String templateName : templates) {
            Map<String, Object> map = new HashMap<>(1);
            Template template = engine.getTemplate("generator/web/" + templateName + ".ftl");
            map.put(templateName, template.render(genMap));
            map.put("content", template.render(genMap));
            map.put("name", templateName);
            genList.add(map);
        }
        return genList;
    }

    public static String download(List<CodeColumnConfig> columns, CodeGenConfig genConfig) throws IOException {
        // 拼接的路径：/tmpehadmin-gen-temp/，这个路径在Linux下需要root用户才有权限创建,非root用户会权限错误而失败，更改为： /tmp/ehadmin-gen-temp/
        // String tempPath =SYS_TEM_DIR + "ehadmin-gen-temp" + File.separator + genConfig.getTableName() + File.separator;
        String tempPath = SYS_TEM_DIR + "ehadmin-gen-temp" + File.separator + genConfig.getTableName() + File.separator;
        Map<String, Object> genMap = getGenMap(columns, genConfig);
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        // 生成后端代码
        List<String> templates = getAdminTemplateNames();
        for (String templateName : templates) {
            Template template = engine.getTemplate("generator/admin/" + templateName + ".ftl");
            String filePath = getAdminFilePath(templateName, genConfig, genMap.get("className").toString(), tempPath + "ehadmin" + File.separator);
            assert filePath != null;
            File file = new File(filePath);

            // 生成代码
            genFile(file, template, genMap);
        }
        // 生成前端代码
        templates = getFrontTemplateNames();
        for (String templateName : templates) {
            Template template = engine.getTemplate("generator/web/" + templateName + ".ftl");
            String path = tempPath + "ehadmin-web" + File.separator;
            String apiPath = path + "src" + File.separator + "api" + File.separator;
            String srcPath = path + "src" + File.separator;
            String filePath = getFrontFilePath(templateName, apiPath, srcPath, genMap);
            assert filePath != null;
            File file = new File(filePath);

            // 生成代码
            genFile(file, template, genMap);
        }
        return tempPath;
    }

    public static void generatorCode(List<CodeColumnConfig> columnInfos, CodeGenConfig genConfig) throws IOException {
        Map<String, Object> genMap = getGenMap(columnInfos, genConfig);
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        // 生成后端代码
        List<String> templates = getAdminTemplateNames();
        for (String templateName : templates) {
            Template template = engine.getTemplate("generator/admin/" + templateName + ".ftl");
            String rootPath = System.getProperty("user.dir");
            String filePath = getAdminFilePath(templateName, genConfig, genMap.get("className").toString(), rootPath);

            assert filePath != null;
            File file = new File(filePath);

            // 如果非覆盖生成
            if (!genConfig.getCover() && FileUtil.exist(file)) {
                continue;
            }
            // 生成代码
            genFile(file, template, genMap);
        }

        // 生成前端代码
        templates = getFrontTemplateNames();
        for (String templateName : templates) {
            Template template = engine.getTemplate("generator/web/" + templateName + ".ftl");
            String apiPath = "../erha-admin-web" + File.separator + "src" + File.separator + "api" + File.separator;
            String srcPath = "../erha-admin-web" + File.separator +"src" + File.separator;
            String filePath = getFrontFilePath(templateName, apiPath, srcPath, genMap);

            assert filePath != null;
            File file = new File(filePath);

            // 如果非覆盖生成
            if (!genConfig.getCover() && FileUtil.exist(file)) {
                continue;
            }
            // 生成代码
             genFile(file, template, genMap);
        }
    }

    // 获取模版数据
    public static Map<String, Object> getGenMap(List<CodeColumnConfig> columnInfos, CodeGenConfig genConfig) {
        // 存储模版字段数据
        Map<String, Object> genMap = new HashMap<>(16);
        // 前端views文件路径
        genMap.put("path",genConfig.getPath());
        // 后端RequestMapping接口
        genMap.put("api_path",genConfig.getApiPath());
        // 接口别名
        genMap.put("apiAlias", genConfig.getApiAlias());
        // 简短接口别名
        String[] strings = genConfig.getApiAlias().split("[:：]");
        genMap.put("apiAliasShort",strings[strings.length-1]);
        genMap.put("apiAliasShortF",strings[strings.length-2]);
        strings = genConfig.getApiPath().split("/");
        genMap.put("apiShort",strings[strings.length-2]+"."+strings[strings.length-1]);
        // 接口的两部分
        genMap.put("apiPathF",strings[strings.length-2]);
        genMap.put("apiPathS",strings[strings.length-1]);
        // 包名称
        genMap.put("package", genConfig.getPack());
        // 模块名称
        genMap.put("moduleName", genConfig.getModuleName());
        // 作者
        genMap.put("author", genConfig.getAuthor());
        // 创建日期
        genMap.put("date", new Date().toString());
        // 表名
        genMap.put("tableName", genConfig.getTableName());
        // 大写开头的类名
        String className = StringUtils.toCapitalizeCamelCase(genConfig.getTableName());
        // 小写开头的类名
        String changeClassName = StringUtils.toCamelCase(genConfig.getTableName());
        // 判断是否去除表前缀
        if (StringUtils.isNotEmpty(genConfig.getPrefix())) {
            className = StringUtils.toCapitalizeCamelCase(StrUtil.removePrefix(genConfig.getTableName(), genConfig.getPrefix()));
            changeClassName = StringUtils.toCamelCase(StrUtil.removePrefix(genConfig.getTableName(), genConfig.getPrefix()));
            changeClassName = StringUtils.uncapitalize(changeClassName);
        }
        // 保存类名
        genMap.put("className", className);
        // 保存小写开头的类名
        genMap.put("changeClassName", changeClassName);
        // 存在 Timestamp 字段
        genMap.put("hasTimestamp", false);
        // 查询类中存在 Timestamp 字段
        genMap.put("queryHasTimestamp", false);
        // 存在 BigDecimal 字段
        genMap.put("hasBigDecimal", false);
        // 查询类中存在 BigDecimal 字段
        genMap.put("queryHasBigDecimal", false);
        // 是否需要创建查询
        genMap.put("hasQuery", false);
        // 自增主键
        genMap.put("auto", false);
        // 存在字典
        genMap.put("hasDict", false);
        // 存在日期注解
        genMap.put("hasDateAnnotation", false);
        // 保存字段信息
        List<Map<String, Object>> columns = new ArrayList<>();
        // 保存查询字段的信息
        List<Map<String, Object>> queryColumns = new ArrayList<>();
        // 存储字典信息
        List<String> dicts = new ArrayList<>();
        // 存储 between 信息
        List<Map<String, Object>> betweens = new ArrayList<>();
        // 存储不为空的字段信息
        List<Map<String, Object>> isNotNullColumns = new ArrayList<>();

        // 查询字段的个数
        int countQueryType = 0;
        // 是否含有开关字段
        boolean hasSwitch = false;
        for (CodeColumnConfig column : columnInfos) {
            Map<String, Object> listMap = new HashMap<>(16);
            if (column.getQueryType() != null) countQueryType++;
            if ("开关[仅两个值]".equals(column.getFormType())) hasSwitch = true;
            // 字段描述
            listMap.put("remark", column.getRemark());
            // 字段类型
            listMap.put("columnKey", column.getKeyType());
            // 主键类型
            String colType = ColUtil.cloToJava(column.getColumnType());
            // 小写开头的字段名
            String changeColumnName = StringUtils.toCamelCase(column.getColumnName());
            // 大写开头的字段名
            String capitalColumnName = StringUtils.toCapitalizeCamelCase(column.getColumnName());
            if (PK.equals(column.getKeyType())) {
                // 存储主键类型
                genMap.put("pkColumnType", colType);
                // 存储小写开头的字段名
                genMap.put("pkChangeColName", changeColumnName);
                // 存储大写开头的字段名
                genMap.put("pkCapitalColName", capitalColumnName);
            }
            // 是否存在 Timestamp 类型的字段
            if (TIMESTAMP.equals(colType)) {
                genMap.put("hasTimestamp", true);
            }
            // 是否存在 BigDecimal 类型的字段
            if (BIGDECIMAL.equals(colType)) {
                genMap.put("hasBigDecimal", true);
            }
            // 主键是否自增
            if (EXTRA.equals(column.getExtra())) {
                genMap.put("auto", true);
            }
            // 主键存在字典
            if (StringUtils.isNotBlank(column.getDictName())) {
                genMap.put("hasDict", true);
                if(!dicts.contains(column.getDictName()))
                    dicts.add(column.getDictName());
            }

            // 存储字段类型
            listMap.put("columnType", colType);
            // 存储字原始段名称
            listMap.put("columnName", column.getColumnName());
            // 不为空
            listMap.put("istNotNull", column.getNotNull());
            // 字段列表显示
            listMap.put("columnShow", column.getListShow());
            // 表单显示
            listMap.put("formShow", column.getFormShow());
            // 表单组件类型
            listMap.put("formType", StringUtils.isNotBlank(column.getFormType()) ? column.getFormType() : "未设置");
            // 小写开头的字段名称
            listMap.put("changeColumnName", changeColumnName);
            //大写开头的字段名称
            listMap.put("capitalColumnName", capitalColumnName);
            // 全大写的字段名称
            listMap.put("allCaps", column.getColumnName().toUpperCase());
            // 字典名称
            listMap.put("dictName", column.getDictName());
            // 日期注解
            listMap.put("dateAnnotation", column.getDateAnnotation());
            if (StringUtils.isNotBlank(column.getDateAnnotation())) {
                genMap.put("hasDateAnnotation", true);
            }
            // 添加非空字段信息
            if (column.getNotNull()) {
                isNotNullColumns.add(listMap);
            }
            // 判断是否有查询，如有则把查询的字段set进columnQuery
            if (!StringUtils.isBlank(column.getQueryType())) {
                // 查询类型
                listMap.put("queryType", column.getQueryType());
                // 是否存在查询
                genMap.put("hasQuery", true);
                if (TIMESTAMP.equals(colType)) {
                    // 查询中存储 Timestamp 类型
                    genMap.put("queryHasTimestamp", true);
                }
                if (BIGDECIMAL.equals(colType)) {
                    // 查询中存储 BigDecimal 类型
                    genMap.put("queryHasBigDecimal", true);
                }
                if ("between".equalsIgnoreCase(column.getQueryType())) {
                    betweens.add(listMap);
                } else {
                    // 添加到查询列表中
                    queryColumns.add(listMap);
                }
            }
            // 添加到字段列表中
            columns.add(listMap);
        }
        // 保存查询字段的个数
        genMap.put("countQueryType",countQueryType);
        // 保存是否含有开关字段
        genMap.put("hasSwitch",hasSwitch);
        // 保存字段列表
        genMap.put("columns", columns);
        // 保存查询列表
        genMap.put("queryColumns", queryColumns);
        // 保存字段列表
        genMap.put("dicts", dicts);
        // 保存查询列表
        genMap.put("betweens", betweens);
        // 保存非空字段信息
        genMap.put("isNotNullColumns", isNotNullColumns);
        return genMap;
    }

    /**
     * 定义后端文件路径以及名称
     */
    private static String getAdminFilePath(String templateName, CodeGenConfig genConfig, String className, String rootPath) {
        String projectPath = rootPath + File.separator + genConfig.getModuleName();
        String packagePath = projectPath + File.separator + "src"  + File.separator + "main" + File.separator + "java" + File.separator;
        String resources =  projectPath + File.separator + "src"  + File.separator + "main" + File.separator + "resources" + File.separator;
        if (!ObjectUtils.isEmpty(genConfig.getPack())) {
            packagePath += genConfig.getPack().replace(".", File.separator) + File.separator;
        }

        if ("Entity".equals(templateName)) {
            return packagePath + "domain" + File.separator + className + ".java";
        }

        if ("Controller".equals(templateName)) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if ("Service".equals(templateName)) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if ("ServiceImpl".equals(templateName)) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if ("Mapstruct".equals(templateName)) {
            return packagePath + "service" + File.separator + "mapstruct" + File.separator + className + "Mapstruct.java";
        }

        if ("CreateVo".equals(templateName)) {
            return packagePath + "domain" + File.separator + "vo" + File.separator  + "Create"+className+"Vo.java";
        }

        if ("UpdateVo".equals(templateName)) {
            return packagePath + "domain" + File.separator + "vo" + File.separator + "Update"+className+"Vo.java";
        }

        if ("RetrieveVo".equals(templateName)) {
            return packagePath + "domain" + File.separator + "vo" + File.separator  + "Retrieve"+className+"Vo.java";
        }

        if ("Entity".equals(templateName)) {
            return packagePath + "domain" + File.separator + className + ".java";
        }

        if ("Mapper".equals(templateName)) {
            return packagePath + "mapper" + File.separator + className + "Mapper.java";
        }

        if ("MapperXml".equals(templateName)) {
            return resources + "mapper" + File.separator + className + "Mapper.xml";
        }

        return null;
    }

    /**
     * 定义前端文件路径以及名称
     */
    private static String getFrontFilePath(String templateName, String apiPath, String srcPath, Map<String, Object> genMap) {

        String apiNameS = genMap.get("apiPathS").toString();
        String apiNameF = genMap.get("apiPathF").toString();

        if ("api".equals(templateName)) {
            return apiPath + apiNameF + File.separator + apiNameS + ".ts";
        }

        if ("index".equals(templateName)) {
            return srcPath + "views" + File.separator  + apiNameF + File.separator + apiNameS + File.separator + "index.vue";
        }

        if ("en-US".equals(templateName)) {
            return srcPath + "views" + File.separator  + apiNameF + File.separator + "locale" + File.separator + "en-US.ts";
        }

        if ("zh-CN".equals(templateName)) {
            return srcPath + "views" + File.separator  + apiNameF + File.separator + "locale" + File.separator + "zh-CN.ts";
        }

        if ("router".equals(templateName)) {
            return srcPath + "router" + File.separator
                    + "routes" + File.separator
                    + "modules" + File.separator
                    + apiNameF + ".ts";
        }

        return null;
    }

    private static void genFile(File file, Template template, Map<String, Object> map) throws IOException {
        // 生成目标文件
        Writer writer = null;
        try {
            FileUtil.touch(file);
            writer = new FileWriter(file);
            template.render(map, writer);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            assert writer != null;
            writer.close();
        }
    }
}
