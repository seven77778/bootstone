package algorithm.dynamic;

import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lsh
 * @date 2023/6/2 11:05
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * .影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12
 *
 * 链接：https://leetcode.cn/problems/house-robber
 */
public class ThiefMax1 {

    @Test
    public void test(){
        int[] nums = new int[]{1,2,3,1};
        System.out.println(rob1(nums));

        int[] nums2 = new int[]{2,7,9,3,1};
        System.out.println(rob1(nums2));

        int[] nums3 = new int[]{2,1,1,2};
        System.out.println(rob1(nums3));
    }

    //最笨的，暴力破解
    public int rob1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            int sum=0;
            for (int j=i;j<nums.length;j=j+2){
                sum=sum+nums[j];
            }
            set.add(sum);
        }

        return Collections.max(set);

    }

}
