package algorithm.leetcode.nandk;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author lsh
 * @date 2023/6/16 21:10
 */
public class TryNK {

    @Test
    public void test() {
        int nums[] = new int[]{3, 2, 1, 5, 6, 4};
        int nums2[] = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println( findKthLargest( nums, 2 ) );//5
        System.out.println( findKthLargest( nums2, 4 ) );//4
        System.out.println( findKthLargest( new int[]{}, 0 ) );//0
    }


    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>( (o1, o2) -> o2 - o1 );


        for (int num : nums) {
            if (!queue.isEmpty() && queue.size() == nums.length + 1 - k && queue.peek() > num) {
                queue.poll();
                queue.offer( num );
            } else if (queue.size() < nums.length + 1 - k) {
                queue.offer( num );
            }
        }
        return queue.isEmpty() ? 0 : queue.poll();
    }
}
