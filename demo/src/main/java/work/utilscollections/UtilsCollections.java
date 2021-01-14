package work.utilscollections;

import com.google.common.base.Joiner;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by lsh on 2020-05-13.
 *
 * 开源工具合集
 *
 */
public class UtilsCollections {


    /**
     * 1.commons-lang3
     *       如果字符串都是空格的话
     *         StringUtils.isBlank(" ")       = true;
     *         StringUtils.isEmpty(" ")       = false；
     *
     * 2.字符串固定长度
     * 这个通常用于字符串需要固定长度的场景，比如需要固定长度字符串作为流水号，若流水号长度不足，，左边补 0 。
     *
     * 这里当然可以使用 String#format 方法，不过比较麻烦，这里可以这样使用：
     *
     * // 字符串固定长度 8位，若不足，忘左补 0
     * StringUtils.leftPad("test", 8, "0");
     * 另外还有一个 StringUtils#rightPad,这个方法与上面方法正好相反。
     *
     * 3.字符串关键字替换
     * StringUtils 提供一些列的方法，可以替换某些关键字：
     *
     * // 默认替换所有关键字
     * StringUtils.replace("aba", "a", "z")   = "zbz";
     * // 替换关键字，仅替换一次
     * StringUtils.replaceOnce("aba", "a", "z")   = "zba";
     * // 使用正则表达式替换
     * StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "")   = "ABC123"；
     *
     *
     * 4.字符串拼接
     *
     *
     * 5.字符串拆分
     * StringUtils.split("a..b.c", '.')   = ["a", "b", "c"]
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("a..b.c", ".")= ["a","", "b", "c"]
     *
     *
     */


    //多次替换得到目标值
    @Test
    public void testStringGet(){
        String str = "### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '121212' for key 'xxx'\n" +
                "; SQL []; Duplicate entry '1212121' for key 'xxx'; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry 'xxx' for key 'xxx'";

        String str1 = StringUtils.substringBefore(str,"for");
        System.out.println(StringUtils.substringAfter(str1,"entry"));
    }

    public static void main(String[] args) {

        //补全字符串
        System.out.println(StringUtils.leftPad("test", 8, "0"));
        System.out.println(StringUtils.rightPad("test", 8, "0"));

        // 逗号分隔符，跳过 null
        List<String> list = Lists.newArrayList();
        list.add("123");
        list.add("abc");
        list.add("777");

        //拼接list字符串
        Joiner joiner=Joiner.on("-").skipNulls();
        String resultList = joiner.join(list);
        System.out.println(resultList);
    }



    @Test
    public void testDate(){
        // 按照 yyyy-MM-dd HH:mm:ss 转化时间
        LocalDateTime dateTime = LocalDateTime.parse("2020-05-13 15:55:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println(dateTime);

        // 将 LocalDateTime 格式化字符串
        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(dateTime);

        System.out.println(format);
    }

    @Test
    public void testList(){
        List<String> list = Lists.newArrayList();
        list.add("123");
        list.add("123");
        list.add("1234");
        // commons-collections4 版本较高
        CollectionUtils.isEmpty(list);

        //list转set,去重
        HashSet<String> set = new HashSet<>(list);
        System.out.println(set);
    }


    /**
     * <dependency>
     *     <groupId>commons-io</groupId>
     *     <artifactId>commons-io</artifactId>
     *     <version>2.6</version>
     * </dependency>
     */
    @Test
    public void testFile()throws Exception{

        //复制文件
        File fileA = new File("D:\\test\\test.txt");
        File fileB = new File("D:\\test1\\test.txt");
        FileUtils.copyFile(fileA,fileB);


        // 按照指定文件后缀如java,txt等去查找指定文件夹的文件
        File directory = new File("D:\\test");
        Collection<File> res1 = FileUtils.listFiles(directory, new String[]{"txt"}, false);

        // 读取指定文件所有行 不需要使用 while 循环读取流了
        List<String> lines = FileUtils.readLines(fileA,"utf-8");
        System.out.println(lines);

        // 可以一行行写入文本
        List<String> linesWriter = new ArrayList<>();
        FileUtils.writeLines(new File(""),linesWriter);

    }

    /**
     *
     * @param list
     */
    public static void reverseList(List list){
        Collections.reverse(list);
    }
}
