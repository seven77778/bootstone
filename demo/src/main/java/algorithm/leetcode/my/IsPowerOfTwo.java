package algorithm.leetcode.my;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/27 17:42
 * <p>
 * 输入：n = 1
 * 输出：true
 * 解释：20 = 1
 * <p>
 * 输入：n = 16
 * 输出：true
 * 解释：24 = 16
 * <p>
 * 输入：n = 3
 * 输出：false
 */
public class IsPowerOfTwo {

  @Test
  public void test() {
    System.out.println(4&3);
    System.out.println(5&4);

    System.out.println(isPowerOfTwo(10));
    System.out.println(isPowerOfTwo(6));
    System.out.println(isPowerOfTwo(32));
    System.out.println(isPowerOfTwo(45));

    System.out.println(isPowerOfTwo(1));
    System.out.println(isPowerOfTwo(0));
  }



  //位运算
  public boolean isPowerOfTwo(int n) {
    return n>0 && (n&(n-1)) ==0;
  }

  //不用循环或者递归，试试二分法
  public boolean isPowerOfTwo3(int n) {
    if (n == 1 || n==2) {
      return true;
    }
    if (n % 2 != 0) {
      return false;
    }
    int left = 0;
    int right = n;
    while (left < right) {
      int temp = left + right;
      if (temp % 2 != 0) {
        return false;
      }
      int mid = temp / 2;
      if (mid > 2) {
        right = mid;
      }
      if (mid == 2) {
        return true;
      }
      if (mid < 2) {
        return false;
      }
    }
    return false;
  }

  public boolean isPowerOfTwo2(int n) {
    if (n <= 0) {
      return false;
    }
    if (n == 1) {
      return true;
    }
    if (n % 2 != 0) {
      return false;
    }
    if (n == 2) {
      return true;
    }
    return isPowerOfTwo(n / 2);
  }
}
