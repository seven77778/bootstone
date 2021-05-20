package work.utils;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 数学工具类
 */
public class MathUtil {



    //计算小数，获得百分比
    @Test
    public void getRate(){
        Integer a = 6;
        Integer b= 10;
        BigDecimal res = new BigDecimal(a).divide(new BigDecimal(b), 2, RoundingMode.HALF_UP);
        System.out.println(res);//0.60
        DecimalFormat df = new DecimalFormat("0%");
        System.out.println(df.format(res));//60%

    }

}
