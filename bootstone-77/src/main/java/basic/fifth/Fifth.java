package basic.fifth;

/**
 * Created by lsh on 2019-07-26.
 *
 * 三目表达式 又名 三元表达式
 *
 */
public class Fifth {

    public static void main(String[] args) {

        int a = 5;
        int b = 6;
        //固定格式 (第一目：一个判断真假） + ？ + （第二目：一个值） + ： + （第三目：另外一个值）
        //规则：如果第一目的表达式为真，则最终结果是？后面的值，否则，取冒号后面的值
        int c = b > a ? 7 : 8; //b大于a为真，所以c = 7，7是问号后面的值
        System.out.println(c);

        //最终的结果取决于 第二目和第三目的值，问号和冒号后面一定是同一类值，比如都是int，或者都是boolean
        boolean d = b > a ? true : false;



        /**
         * 下面用三目来替换昨天的 判断缴税 功能
         */
        int xiaoLiu = 5000;
        int xiaoLi = 3000;

        /**
         * ****************************************************
         */
        //小刘要不要缴税
        boolean xiaoLiuPayTax = xiaoLiu > 3500 ? true : false;
        //小李要不要缴税
        boolean xiaoLiPayTax = xiaoLi > 3500 ? true : false;
        /**
         * ****************************************************
         * 就两行代码，替换昨天的好几行代码，简单、优雅
         */
        System.out.println("小刘要不要缴税 ： " + xiaoLiuPayTax);
        System.out.println("小李要不要缴税 ： " + xiaoLiPayTax);

        /**
         *  但是，不是所有的 if...else 都可以被 三目表达式 替换掉，please look
         *  其实 if else 可以有多个条件，在需要特别细分的 功能上，比如 通过年龄区分 青年和老年等等
         *  固定格式是
         *
         *  if(){
         *  } else if(){
         *  } else if(){
         *  }
         *  ...
         *  else {
         *  }
         *
         *
         */

        int age = 18;

        if( age <= 3){ //小于三岁的都算婴儿
            System.out.println("婴儿");
        }else if( 3 < age && age <= 18){
            System.out.println("青少年");
        }else if( 18 < age && age <= 30){
            System.out.println("青年");
        }else if( 30 < age && age <= 50){
            System.out.println("中年");
        }else if( 50 < age ){// 大于50的，都老了呀
            System.out.println("老年");
        }else {
            System.out.println("它不是人！");
        }

    }
}
