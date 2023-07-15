package algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author lsh
 * @date 2023/6/4 14:00
 * <p>
 * NO215.
 * <p>
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 * <p>
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class TwoHundredAndFifteen {

    @Test
    public void test() {
        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(findKthLargest2(nums, 4));

        int[] nums2 = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargest2(nums2, 2));
    }


    public int findKthLargest3(int[] nums, int k) {

        int r = nums[0];
        return 0;
    }

    //这个竟然可以通过，时间复杂度n*logn
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue priorityQueue = new PriorityQueue();
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    //试试大顶堆，找k个数中最大的 ,有k-1的比他大，保证大顶堆元素是k个，最小的那个就是目标值
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue(k);
        for (int i = 0; i < nums.length; i++) {
            priorityQueue.offer(nums[i]);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();//弹出总是最小的
            }
        }
        return priorityQueue.poll();
    }
}
