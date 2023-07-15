package algorithm.dynamic;

import org.junit.Test;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author lsh
 * @date 2023/5/31 14:47
 * <p>
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。
 * 求该青蛙跳上一个 10 级的台阶总共有多少种跳法。
 */
public class DynamicOne {
    private LongAdder longAdder = new LongAdder();

    //先试试递归，缺点是计算次数太多，过多的重复计算
    @Test
    public void recursionTest() {
//        System.out.println(circleCount(10));
        System.out.println(circleCount2(39));
//        System.out.println(numWays(10));
    }

    public int circleCount(int n) {

        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return circleCount(n - 1) + circleCount(n - 2);
    }

    Map<Integer, Integer> map = new ConcurrentSkipListMap<>();    //改进
    public int circleCount2(int n) {
        if (n <= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        } else {
            map.put(n, (circleCount2(n - 1) + circleCount2(n - 2))%1000000007 );
            return map.get(n);
        }

    }


    //动态规划
    public int numWays(int n) {
        if (n <= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = (a + b) % 1000000007;
            a = b;
            b = temp;
        }
        return temp;
    }
}
