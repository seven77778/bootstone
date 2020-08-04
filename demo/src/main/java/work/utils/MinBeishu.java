package work.utils;

/**
 * Created by lsh on 2019-12-11.
 */
public class MinBeishu {

    public static void main(String[] args) {
//        System.out.println(leastMajorityMultiple(1,2,3,4,5));
//        System.out.println(leastMajorityMultiple(1,3,5,9,17));
        get(1, 3, 5, 9, 17);
    }


    public static void get(int a, int b, int c, int d, int e) {
        int count = 0;
        int result;
        for (result = 1; result < Integer.MAX_VALUE; result++) {
            if (result % a == 0) {
                count++;
            }
            if (result % b == 0) {
                count++;
            }
            if (result % c == 0) {
                count++;
            }
            if (result % d == 0) {
                count++;
            }
            if (result % e == 0) {
                count++;
            }
            if (count >= 3) {
                break;
            } else {
                count = 0;//count值小于3，count值置为0
            }
        }
        System.out.println(result);
    }

    public static int leastMajorityMultiple(int a, int b, int c, int d, int e) {
        int i = 1;
        int count = 0;//能整除的次数
        while (i > 0) {
            System.out.println(i);
            if (i % a == 0) {
                count++;
            }
            if (i % b == 0) {
                count++;
            }
            if (i % c == 0) {
                count++;
            }
            if (i % d == 0) {
                count++;
            }
            if (i % e == 0) {
                count++;
            }
            if (count >= 3) {
                break;
            }
            count = 0;//若count值小于3，恢复到0
            i++;//不满足条件，i++
        }
        return i;
    }


}
