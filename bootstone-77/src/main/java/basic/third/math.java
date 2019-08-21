package basic.third;

import org.springframework.core.annotation.SynthesizedAnnotation;

public class math {
    public static void main(String[] args) {
        int a = 10 / 2;
        int b = 3;
        double c = Math.pow(a, 2) * 3.14 * b;
        System.out.println("体积为" + c);
        double d = 750 * c;
        System.out.println("一共可以储存" + d + "千克粮食");


        int xiaowang = 25;
        int xiaozhang = 32;
        boolean result = ((xiaowang > 23) && (xiaowang < 30));
        boolean result2 = ((xiaozhang > 23) && (xiaozhang < 30));
        System.out.println("小王是否满足:" + result);
        System.out.println("小张是否满足:" + result2);

        boolean e = (23 < xiaowang) && (xiaowang < 30) ? true : false;
        boolean f = (23 < xiaozhang) && (xiaozhang < 30) ? true : false;
        System.out.println("小王是否满足：" + e);
        System.out.println("小张是否满足：" + f);

        int g = 425;
        int h = 424;
        if (h >= g) {
            System.out.println("该学生通过考试");
        }
        if (h < g){
            System.out.println("该学生没有通过考试");
        }






    }


}
