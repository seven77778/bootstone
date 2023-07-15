package algorithm.leetcode;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/11 21:06
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * <p>
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 */
public class Thirty {

    @Test
    public void test() {

        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen3(7, nums)); //2
        int[] nums2 = new int[]{1, 4, 4};
        System.out.println(minSubArrayLen3(4, nums2));//1
        int[] nums3 = new int[]{1, 2, 3, 4, 5};
        System.out.println(minSubArrayLen3(11, nums3));//3
        int[] nums4 = new int[]{2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen3(7, nums4));//2
        int[] nums5 = new int[]{5};
        System.out.println(minSubArrayLen3(7, nums5));//0
    }


    //滑动窗口
    public int minSubArrayLen3(int target, int[] nums) {
        int min = 0;
        int left = 0;
        int tempNum = 0;
        for (int i = 0; i < nums.length; i++) {
            tempNum += nums[i];
            if (tempNum >= target) {
                while (tempNum - nums[left] >= target) {
                    tempNum -= nums[left];
                    left++;
                }
                min = min == 0 ? i - left + 1 : Math.min(min, i - left + 1);
            }
        }
        return min;
    }

    //滑动窗口 todo --写的太啰嗦，可以优化
    public int minSubArrayLen2(int target, int[] nums) {
        if (nums.length == 0) {
            return nums.length;
        }
        if (nums.length == 1) {
            return nums[0] >= target ? 1 : 0;
        }
        int min = 0;
        int left = 0;
        int tempNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return 1;
            } else {
                tempNum += nums[i];
                if (tempNum >= target) {
                    while (tempNum - nums[left] >= target) {
                        tempNum -= nums[left];
                        left++;
                    }
                    if (min == 0) {
                        min = i - left + 1;
                    } else {
                        min = Math.min(min, i - left + 1);
                    }
                }
            }
        }
        return min;
    }

    //老规矩，笨方法先来 -- todo 直接超时
    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int min = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            if (temp >= target) {
                return 1;
            }
            for (int j = i + 1; j < nums.length; j++) {
                temp += nums[j];
                if (temp >= target) {
                    if (min == 0) {
                        min = j - i + 1;
                    } else if (j - i + 1 > min) {
                        break;
                    } else {
                        min = Math.min(min, j - i + 1);
                    }
                    break;
                }
            }
        }
        return min;
    }


}
