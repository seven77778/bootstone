package basic.expand1;

/**
 * Created by lsh on 2019-07-25.
 */
public class PayTax {

    public static void main(String[] args) {

        //小李月入6000，小刘月入4999，看看要不要缴税
        double xiaoli = 6000;
        double xiaoliu = 4999;
        // 调用buyTicket方法，传入小李和小刘的收入
        System.out.println("小李要不要缴税 ： " + payTax(xiaoli));
        System.out.println("小刘要不要缴税 ： " + payTax(xiaoliu));
    }

    /**
     * 思路 ： 该方法接收参数为 收入，double类型，返回值是boolean，
     * true 代表 收入大于 5000 ，即需要纳税
     * @return 布尔值
     */
    public static boolean payTax(double x){
        // 判断收入是否大于5000
        if(x > 5000){
            return true;
        }else {
            return false;
        }
    }
}
