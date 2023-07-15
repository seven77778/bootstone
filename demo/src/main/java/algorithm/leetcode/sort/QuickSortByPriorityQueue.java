package algorithm.leetcode.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author lsh
 * @date 2023/6/12 22:18
 */
public class QuickSortByPriorityQueue {

    @Test
    public void test() {
        int[] nums = new int[]{5, 2, 1, 4, 8};
        System.out.println( Arrays.toString( sortArray5( nums ) ) );
        int[] nums2 = new int[]{5, 1, 1, 2, 0, 0};
        System.out.println( Arrays.toString( sortArray5( nums2 ) ) );

        int[] nums3 = new int[]{-1, 2, -8, -10};
        System.out.println( Arrays.toString( sortArray5( nums3 ) ) );

    }

    //用大顶堆做排序 -- 通过……
    public int[] sortArray5(int[] nums) {
        if (nums.length == 0) {
            return nums;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            queue.offer( nums[i] );
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = queue.isEmpty() ? 0 : queue.poll();
        }
        return nums;
    }
}
