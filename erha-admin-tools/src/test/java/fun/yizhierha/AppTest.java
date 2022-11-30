package fun.yizhierha;

import cn.hutool.core.util.ArrayUtil;
import fun.yizhierha.common.utils.ArrayUtils;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public static void main(String[] args) {
        String str = "[sda, qaq, apple]";
        String[] strings = parseToArray(String.class,str);
        for (String string : strings) {
            System.out.println("    1"+string);
        }
        System.out.println(ArrayUtils.toString(strings));
    }

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
