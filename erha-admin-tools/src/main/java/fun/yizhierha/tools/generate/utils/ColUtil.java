package fun.yizhierha.tools.generate.utils;


import cn.hutool.setting.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColUtil {
    private static final Logger log = LoggerFactory.getLogger(ColUtil.class);
    private static final Setting settings = new Setting("generate.setting");

    /**
     * 转换mysql数据类型为java数据类型
     *
     * @param type 数据库字段类型
     * @return String
     */
    public static String cloToJava(String type) {
       return settings.getStr(type,"unknown");
    }



}
