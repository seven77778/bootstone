package dll;


import com.sun.jna.Native;
import org.junit.Test;

/**
 * Created by lsh on 2019/4/8.
 *
 * java调用 C++ dll
 *
 * 1.新建接口 与 dll 方法一致
 * 2.接口要 extends StdCallLibrary
 * 3.dll 路径 -- 直接扔jdk-bin目录
 * 4.32位和64位 检查project和modules的配置
 *
 */
public class JavaToCPlusPlus {

    /**
     * 1.通过接口调用
     * @param args
     */
    public static void main(String[] args) {
        //设置为true，控制台可以看到更多的调试信息，可以省略
        System.setProperty("jna.debug_load", "true");
        Native.setProtected(true);
        CPlusPlusService cPlusPlusService = Native.loadLibrary("ProjectForJava.dll",CPlusPlusService.class);
        System.out.println(cPlusPlusService.add(1,6));
        System.out.println(cPlusPlusService.getstr());
    }





    /**
     * 2.直接映射
     */
    public static  native String getstr(String content);

    @Test
    public void test(){
        Native.register("ProjectForJava.dll");
        System.out.println(getstr("1123"));
    }

}
