package algorithm.leetcode.my;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/27 9:44
 * <p>
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 */
public class LongestPalindrome {

  @Test
  public void test() {
    System.out.println(longestPalindrome("abbcddeewww"));
    System.out.println(longestPalindrome("abc"));
    System.out.println(longestPalindrome("abbc"));
    System.out.println(longestPalindrome("aaaa"));

    System.out.println(longestPalindrome("abba"));
  }


  //中心回溯法
  public String longestPalindrome(String s) {
    int lastLeft = 0;
    int lastRight = 0;
    for (int i = 0; i < s.length(); i++) {
      for (int j = 0; j <= 1; j++) {
        int left = i;
        int right = i + j;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
          if (right - left > (lastRight - lastLeft)) {
            lastLeft = left;
            lastRight = right;
          }
          left--;
          right++;
        }
      }
    }
    return s.substring(lastLeft, lastRight + 1);

  }


  //动态规划~ 效率一般
  public String longestPalindrome2(String s) {
    String res = "";
    boolean[][] arr = new boolean[s.length()][s.length()];
    for (int j = 0; j < s.length(); j++) {
      for (int i = 0; i <= j; i++)
        if (s.charAt(i) == s.charAt(j) && (j - i < 2 || arr[i + 1][j - 1])) {
          arr[i][j] = true;
          if (res.equals("")) {
            res = s.substring(i, j + 1);
          } else if (j - i > res.length() - 1) {
            res = s.substring(i, j + 1);
          }
        }
    }
    return res;
  }
}
