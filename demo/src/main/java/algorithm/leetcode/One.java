package algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author lsh
 * @date 2023/6/3 8:04
 * <p>
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 */
public class One {

    @Test
    public void test() {
        int[] num1 = new int[]{2, 7, 11, 15};
        int[] num2 = new int[]{3, 2, 4};
        int[] num3 = new int[]{3, 2, 3};
        int[] num4 = new int[]{3, 2, 4};
        System.out.println(tableSizeFor(6));
        System.out.println(Arrays.toString(twoSum2(num1, 9)));
        System.out.println(Arrays.toString(twoSum2(num2, 6)));
        System.out.println(Arrays.toString(twoSum2(num3, 6)));
        System.out.println(Arrays.toString(twoSum2(num4, 6)));

    }

    //两层for
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    //hash -- ok
    //hash碰撞？
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length>>>2);
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null) {
                return new int[]{i, map.get(target-nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }


     final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
    }
}
