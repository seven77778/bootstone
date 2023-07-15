package algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author lsh
 * @date 2023/6/4 18:05
 * NO921.
 * <p>
 * 给你一个整数数组 nums，请你将该数组升序排列。
 * 示例 1：
 * <p>
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * 示例 2：
 * <p>
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 */
public class NineHundredAndTwelve {

    @Test
    public void test() {
        int nums[]=new int[]{5,4,2,1,3,6,9};
        int[] arr = sortArray(nums);
        System.out.println(Arrays.toString(arr));
    }


    //hhh超时
    public int[] sortArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int temp = 0;
                if (nums[i] > nums[j]) {
                    temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        return nums;

    }

}
