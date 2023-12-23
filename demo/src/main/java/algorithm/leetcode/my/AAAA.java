package algorithm.leetcode.my;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/8/13 19:04
 */
public class AAAA {
  @Test
  public void test2() {
    System.out.println(test("apple", "pae"));
    System.out.println(test("a", "abc"));
    System.out.println(test("mitcmab", "mitssdfcmuab"));
    System.out.println(test("horse", "ros"));
  }

  public int test(String str1, String str2) {
    int m = str1.length();
    int n = str2.length();

    // 一个空的，直接返回结果
    if (m == 0 || n == 0) {
      return m + n;
    }
    int[][] dpArr = new int[m + 1][n + 1];
    // 填充初始值
    for (int i = 0; i < m + 1; i++) {
      dpArr[i][0] = i;
    }
    for (int j = 0; j < n + 1; j++) {
      dpArr[0][j] = j;
    }

    for (int i = 1; i < m + 1; i++) {
      for (int j = 1; j < n + 1; j++) {

        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          //下一个字母相同时 ，无需修改
          dpArr[i][j] = dpArr[i - 1][j - 1];
        }else {
          //不相同，加一
          dpArr[i][j] = Math.min(dpArr[i - 1][j - 1], Math.min(dpArr[i - 1][j], dpArr[i][j - 1])) + 1;
        }
      }
    }
    return dpArr[m][n];
  }
}


