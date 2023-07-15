package algorithm.leetcode.my;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lsh
 * @date 2023/7/2 22:05
 * <p>
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * <p>
 * <p>
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class MaxProfit {

  @Test
  public void test() {

    System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));
    System.out.println(maxProfit(new int[]{7, 6, 4, 3, 11}));
  }


  //100+70
  public int maxProfit(int[] prices) {
    int sum = 0;
    int right = prices.length - 1;
    int left = right - 1;
    while (left >= 0) {
      if (prices[right] < prices[left]) {
        right = left;
        left--;
        continue;
      }
      int temp = prices[right] - prices[left];
      if (temp > sum) {
        sum = temp;
      }
      left--;
    }

    return sum;
  }


  //超时哈哈好
  public int maxProfitTimeOut(int[] prices) {
    int sum = 0;
    for (int i = 0; i < prices.length; i++) {
      for (int j = prices.length - 1; j >= i; j--) {
        sum = Math.max(sum, prices[j] - prices[i]);
      }
    }
    return sum;
  }
}
