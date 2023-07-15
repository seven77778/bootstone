package algorithm.leetcode.nandk;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author lsh
 * @date 2023/6/16 8:27
 * 剑指 Offer II 076. 数组中的第 k 大的数字
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * todo 手写堆排序
 */
public class FindMinKNum {
    @Test
    public void test() {
        int nums[] = new int[]{3, 2, 1, 5, 6, 4};
        int nums2[] = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println( findKthLargest4( nums, 2 ) );//5
        System.out.println( findKthLargest4( nums2, 4 ) );//4
    }

    /***************************手写堆排序****************************************/





    /***************************简洁优雅****************************************/
    //others todo
    public int findKthLargest1(int[] nums, int k) {
        int half = nums.length / 2;
        PriorityQueue<Integer> queue;
        NeedReplace needReplace;
        if (k <= half) {
            //默认小顶堆  一直保证堆里k个元素
            queue = new PriorityQueue<>( k );
            needReplace = (num, top) -> num > top;
        } else {
            k = nums.length + 1 - k;
            //大顶堆
            queue = new PriorityQueue<>( k, (o1, o2) -> o2 - o1 );
            needReplace = (num, top) -> num < top;
        }
        for (int num : nums) {
            if (queue.size() < k) {
                queue.offer( num );
            } else {
                if (needReplace.needReplace( num, queue.peek() )) {
                    queue.poll();
                    queue.offer( num );
                }
            }
        }

        return queue.isEmpty() ? 0 : queue.peek();
    }

    interface NeedReplace {
        boolean needReplace(int num, int top);
    }

    /*******************************************************************/


    //大顶堆+小顶堆结合优化 --自己写的，性能还是一般
    public int findKthLargest4(int[] nums, int k) {
        PriorityQueue<Integer> queue;
        if (k < nums.length / 2) {
            queue = new PriorityQueue<>();
            for (int i = 0; i < nums.length; i++) {
                if (!queue.isEmpty() && queue.peek() < nums[i] && queue.size() >= k) {
                    queue.poll();
                    queue.offer( nums[i] );
                } else if (queue.size() < k) {
                    queue.offer( nums[i] );
                }
            }
            return queue.isEmpty() ? 0 : queue.poll();
        } else {
            queue = new PriorityQueue<>( (o1, o2) -> o2 - o1 );
            for (int i = 0; i < nums.length; i++) {
                if (!queue.isEmpty() && queue.size() >= nums.length - k + 1 && queue.peek() > nums[i]) {
                    queue.poll();
                    queue.offer( nums[i] );
                } else if (queue.size() < nums.length - k + 1) {
                    queue.offer( nums[i] );
                }
            }
            return queue.isEmpty() ? 0 : queue.poll();
        }


    }

    //怎么用大顶堆呢 -- 用大顶堆就是，比如找第k大，换算成第 (n-k+1) 小
    public int findKthLargest3(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>( (o1, o2) -> o2 - o1 );
        for (int i = 0; i < nums.length; i++) {
            if (!queue.isEmpty() && queue.size() >= nums.length - k + 1 && queue.peek() > nums[i]) {
                queue.poll();
                queue.offer( nums[i] );
            } else if (queue.size() < nums.length - k + 1) {
                queue.offer( nums[i] );
            }
        }
        return queue.isEmpty() ? 0 : queue.poll();
    }


    //看着简单，性能不如 findKthLargest
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            queue.offer( nums[i] );
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.isEmpty() ? 0 : queue.poll();
    }


    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (!queue.isEmpty() && queue.peek() < nums[i] && queue.size() >= k) {
                queue.poll();
                queue.offer( nums[i] );
            } else if (queue.size() < k) {
                queue.offer( nums[i] );
            }
        }
        return queue.isEmpty() ? 0 : queue.poll();
    }
}
