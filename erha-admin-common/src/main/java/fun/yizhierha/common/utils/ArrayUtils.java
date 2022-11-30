package fun.yizhierha.common.utils;

import cn.hutool.core.util.ArrayUtil;

import java.lang.reflect.Array;

public class ArrayUtils extends ArrayUtil {

    /**
     *例如： arrStr = "[sda, qaq, apple]"
     *      clazz = String.class
     *      return  {"sda", "qaq", "apple"}
     * @param clazz 要转换的数组类型
     * @param arrStr string类型的字符串
     * @return  数组
     * @param <T>   要转换的类型
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] parseToArray(Class<T> clazz,String arrStr){
        String replace = arrStr.replace("[", "")
                .replace("]","");
        String[] split = replace.split(",");
        T[] res = (T[]) Array.newInstance(clazz, split.length);

        for (int i = 0; i < split.length; i++) {
            res[i] = (T) split[i].trim();
        }
        return res;
    }
}
