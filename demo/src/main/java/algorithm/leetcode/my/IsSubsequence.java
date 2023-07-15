package algorithm.leetcode.my;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @author lsh
 * @date 2023/7/5 16:26
 * <p>
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * <p>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * <p>
 * <p>
 * indexof，从某个下标开始找
 */
public class IsSubsequence {

  @Test
  public void test() {
    System.out.println(isSubsequence("b", "abc"));
    System.out.println(isSubsequence("", ""));
    System.out.println(isSubsequence("", "ahbgdc"));
    System.out.println(isSubsequence("abc", ""));
    System.out.println(isSubsequence("abc", "aebtce"));
    System.out.println(isSubsequence("aaa", "bbaa"));
    System.out.println(isSubsequence("aa", ""));
    System.out.println(isSubsequence("acb", "aeeeebc"));
    System.out.println(isSubsequence("acb", "aeeeebc"));
    System.out.println(isSubsequence("axc", "ahbgdcxxcc"));
  }


  //正则表达式
  public boolean isSubsequence(String s, String t) {
    if(s.length()==0 && t.length()!=0){
      return true;
    }
    StringBuilder sb = new StringBuilder();
    for(char c:s.toCharArray()){
      sb.append(".*").append(c).append(".*");
    }
    String pattern = sb.toString();
    return t.matches(pattern);
  }

    //DP
  public boolean isSubsequence66(String s, String t) {
    int n = s.length(), m = t.length();

    int[][] f = new int[m + 1][26];
    for (int i = 0; i < 26; i++) {
      f[m][i] = m;
    }

    for (int i = m - 1; i >= 0; i--) {
      for (int j = 0; j < 26; j++) {
        if (t.charAt(i) == j + 'a')
          f[i][j] = i;
        else
          f[i][j] = f[i + 1][j];
      }
    }
    int add = 0;
    for (int i = 0; i < n; i++) {
      if (f[add][s.charAt(i) - 'a'] == m) {
        return false;
      }
      add = f[add][s.charAt(i) - 'a'] + 1;
    }
    return true;
  }


  //双指针
  public boolean isSubsequence6(String s, String t) {
    int left = 0;
    int leftT = 0;
    while (left < s.length() && leftT < t.length()) {
      if (s.charAt(left) == t.charAt(leftT)) {
        if (left++ == s.length()) {
          return true;
        }
      }
      leftT++;
    }
    return left == s.length();

  }

  /**
   * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
   * 输入：s = "abc", t = "ahbgdc"
   * 输出：true
   * <p>
   * 输入：s = "axc", t = "ahbgdc"
   * 输出：false
   */


  public boolean isSubsequence2(String s, String t) {
    int sum = 0;
    int temp = 0;
    int left = 0;
    for (int i = 0; i < s.length(); i++, left = temp) {
      while (left <= t.length() - 1) {
        if (t.charAt(left) == s.charAt(i)) {
          temp = left + 1;
          if (sum++ == s.length()) {
            return true;
          }
          break;
        }
        left++;
      }
    }
    return sum == s.length();
  }
}
