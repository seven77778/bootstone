package algorithm.leetcode.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author lsh
 * @date 2023/6/13 12:53
 * 40.
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，
 * 则最小的4个数字是1、2、3、4。
 * <p>
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 */
public class MinFourNum {

    @Test
    public void test() {
        Integer a =100;
        Integer b =100;
        Integer c =1000;
        Integer d =1000;
        System.out.println(a==b);
        System.out.println(c==d);
        System.out.println(c.equals( d ));

        float f1 = 6.65f;
        System.out.println(f1);
        float f3= 0.25f;
        System.out.println(f3);
        float f2 = 1.3f;
        System.out.println(f1 + f2);
        int[] arr = new int[]{3, 2, 1, 6};
        System.out.println( Arrays.toString( getLeastNumbers( arr, 2 ) ) );

        int[] arr2 = new int[]{12, 34, 5, 5, 6, 7, 4};
        System.out.println( Arrays.toString( getLeastNumbers( arr2, 3 ) ) );

        int[] arr3 = new int[]{12, 34, 5, 5, 6, 7, 4};
        System.out.println( Arrays.toString( getLeastNumbers( arr3, 0 ) ) );
        System.out.println( Arrays.toString( getLeastNumbers( arr3, 1 ) ) );

    }

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) {
            return new int[]{};
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>( new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        } );
        for (int i = 0; i < arr.length; i++) {
            queue.offer( arr[i] );
            if (queue.size() > k && queue.peek() >= arr[i]) {
                queue.poll();
            }
        }
        int[] brr = new int[k];
        int i = 0;
        while (!queue.isEmpty()) {
            brr[i] = queue.poll();
            i++;

        }
        return brr;
    }
}
