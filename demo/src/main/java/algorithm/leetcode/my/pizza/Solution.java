package algorithm.leetcode.my.pizza;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author lsh
 * @date 2023/8/19 15:27
 */
public class Solution {

  @Test
  public void test(){
    System.out.println(maxSizeSlices(new int[]{9, 8, 1, 7, 7, 9, 5, 10, 7, 9, 3, 8, 3, 4, 8}));
  }


  public int maxSizeSlices(int[] slices) {
    int[] v1 = new int[slices.length - 1];
    int[] v2 = new int[slices.length - 1];
    System.arraycopy(slices, 1, v1, 0, slices.length - 1);
    System.arraycopy(slices, 0, v2, 0, slices.length - 1);
    int ans1 = calculate(v1);
    int ans2 = calculate(v2);
    return Math.max(ans1, ans2);
  }

  public int calculate(int[] slices) {
    int N = slices.length, n = (N + 1) / 3;
    int[][] dp = new int[N][n + 1];
    for (int i = 0; i < N; i++) {
      Arrays.fill(dp[i], Integer.MIN_VALUE);
    }
    dp[0][0] = 0;
    dp[0][1] = slices[0];
    dp[1][0] = 0;
    dp[1][1] = Math.max(slices[0], slices[1]);
    for (int i = 2; i < N; i++) {
      dp[i][0] = 0;
      for (int j = 1; j <= n; j++) {
        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 2][j - 1] + slices[i]);
      }
    }
    return dp[N - 1][n];
  }
}

