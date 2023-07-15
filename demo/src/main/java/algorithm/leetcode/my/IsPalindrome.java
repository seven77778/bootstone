package algorithm.leetcode.my;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/28 8:21
 * <p>
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 例如，121 是回文，而 123 不是。
 * <p>
 * 输入：x = 121
 * 输出：true
 * <p>
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * <p>
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 */
public class IsPalindrome {

  @Test
  public void test() {
    System.out.println(isPalindrome(10));
    System.out.println(isPalindrome(1221));
    System.out.println(isPalindrome(121));
    System.out.println(isPalindrome(0));
    System.out.println(isPalindrome(1));

    System.out.println(isPalindrome(-121));
    System.out.println(isPalindrome(12451));
  }


  //翻转数字的一半，fixme
  public boolean isPalindrome(int x) {

    return true;
  }


    //创建数组来判断，内存占用高
  public boolean isPalindrome3(int x) {
    int finalNum = x;
    if (x < 0) {
      return false;
    }
    if (x < 10) {
      return true;
    }
    int sum = 0;
    while (x > 0) {
      sum++;
      x = x / 10;
    }

    int[] arr = new int[sum];
    for (int i = 0; i < sum; i++) {
      arr[i] = finalNum % 10;
      finalNum = finalNum / 10;
    }
    int left = 0;
    int right = sum-1;
    while (left < right) {
      if (arr[left] != arr[right]) {
        return false;
      }
      left++;
      right--;
    }

    return true;
  }


  //todo 原生求整数位数
  //不转字符串,求整数的位数
  public int getByte(int x) {
    if (x < 0) {
      x = -x;
    }
    if (x == 0) {
      return 1;
    }
    int sum = 0;
    while (x > 0) {
      sum++;
      x = x / 10;
    }
    return sum;
  }

  //转字符串确实好做
  public boolean isPalindrome2(int x) {
    if (x < 0) {
      return false;
    }
    if (x == 0) {
      return true;
    }
    String str = x + "";
    int left = 0;
    int right = str.length() - 1;
    while (left < right) {
      if (str.charAt(left) != str.charAt(right)) {
        return false;
      } else {
        left++;
        right--;
      }

    }
    return true;
  }

}
