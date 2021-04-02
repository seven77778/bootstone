package work.utils;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class StringFormatUtil {

    /**
     * 给定一个字符串，如果不足4位，补全四位
     */
    @Test
    public void test(){
        String s1 = "12345";
        String s2="";
        List<String> list = Lists.newArrayList();
        list.add(s1);
        list.add(s2);
        List<String> rr = list.stream().filter(x -> StringUtils.equals("12345", x)).collect(Collectors.toList());
        System.out.println(rr);


//
//        DecimalFormat decimalFormat = new DecimalFormat("0000");
//        System.out.println(decimalFormat.format(s1));
//        System.out.println(decimalFormat.format(s2));

        System.out.println(StringUtils.isNoneBlank(s1,s2));



    }

}
