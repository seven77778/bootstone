package algorithm.leetcode.other;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author lsh
 * @date 2023/6/12 11:04
 * <p>
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 */
public class NAndK {


    @Test
    public void test() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer( 1 );
        queue.offer( 2 );
        queue.offer( 3 );
        System.out.println( queue.poll() );//1 默认小顶堆
        int nums1[] = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println( findKthLargest2( nums1, 2 ) );//5

        int nums2[] = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println( findKthLargest2( nums2, 4 ) );//4

    }


    //先用下大顶堆
    public int findKthLargest2(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return 0;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>( new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        } );
        for (int i = 0; i < nums.length; i++) {
            queue.offer( nums[i] );
            if (queue.size() >= nums.length - k && !queue.isEmpty() && nums[i] < queue.peek()) {
                queue.poll();
            }

        }
        return queue.isEmpty() ? 0 : queue.poll();
    }


    //大顶堆--性能一般
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (queue.size() >= k) {
                if (!queue.isEmpty() && queue.peek() < nums[i]) {
                    queue.poll();
                    queue.offer( nums[i] );
                }
            } else {
                queue.offer( nums[i] );
            }
        }
        return queue.isEmpty() ? 0 : queue.poll();
    }

}
