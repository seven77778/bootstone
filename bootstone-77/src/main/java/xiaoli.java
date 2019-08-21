public class xiaoli {

    public static void main(String[] args) {
        /**
         * 用true和false分别判断身高1米和1.5米的儿童乘坐火车时是否应该购票（超过1.2米的孩童乘坐火车时需购票）
         */
        //小李身高1米，小明身高1.5米
        double xiaoli = 1;
        double xiaoliu = 1.5;
        // 调用buyTicket方法，传入小李和小刘身高
        System.out.println("小李要不要买票 ： " + qqqq(xiaoli));
        System.out.println("小刘要不要买票 ： " + qqqq(xiaoliu));
    }

    /**
     * 思路 ： 该方法接收参数为 身高，double类型，返回值是boolean，true代码身高大于1.2，即需要买票
     *
     * @return 布尔值
     */
    public static boolean qqqq(double x) {
        if (x > 1.2) {
            return true;
        } else {
            return false;
        }


    }
}