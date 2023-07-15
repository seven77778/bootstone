package algorithm.dynamic;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author lsh
 * @date 2023/5/31 15:34
 * 有一只免子要下台阶，每步只能下2阶台阶或3阶台阶，现在有一段楼梯，台阶数未知，
 * 请写一段代码能算出来他最少需要几步能下完所有的楼梯。（如果无论下多少次都无法下来请返回-1）
 *
 *
 * 原题：
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 * 链接：https://leetcode.cn/problems/coin-change
 *
 * 示例 1：
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 */
public class RabbitDownstairs {

    @Test
    public void test(){
        int[] coins = new int[]{2,3};
        System.out.println(coinChange(coins,898965458));
    }

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
