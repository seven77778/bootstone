package algorithm.leetcode.my;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/24 22:32
 * 69.
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * <p>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * <p>
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5
 */
public class MySqrt {

  @Test
  public void test() {
    System.out.println(mySqrt(2));
    System.out.println(mySqrt(4));
    System.out.println(mySqrt(5));
    System.out.println(mySqrt(9));
  }

  public int mySqrt(int x) {
    if (x == 0) {
      return 0;
    }
    if (x == 1) {
      return 1;
    }
    int left = 0;
    int right = x;
    int res = 0;
    while (left < right) {
      int mid = (left + right) / 2;
      if (mid * mid <= x) {
        res = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return res;
  }


}
