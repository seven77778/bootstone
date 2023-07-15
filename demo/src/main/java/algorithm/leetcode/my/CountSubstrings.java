package algorithm.leetcode.my;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/29 10:47
 * <p>
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * <p>
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * <p>
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * <p>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * <p>
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 */
public class CountSubstrings {


  @Test
  public void test() {
    System.out.println(countSubstrings("abc"));//3
    System.out.println(countSubstrings("aaa"));//6
    System.out.println(countSubstrings("aa"));//3
    System.out.println(countSubstrings("aaaa"));//10
    System.out.println(countSubstrings("aaaaa"));//15
    System.out.println(countSubstrings("abba"));//6
    System.out.println(countSubstrings("aba"));//4
    System.out.println(countSubstrings("abcd"));//4

  }


  //中心回溯法
  //如何找到所有中心
  public int countSubstrings(String s) {
    int res = 0;
    for (int i = 0; i < s.length(); i++) {
      for (int j = 0; j <= 1; j++) {
        int left = i;
        int right = i + j;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
          res++;
          left--;
          right++;
        }
      }
    }
    return res;
  }

  //动态规划
  public int countSubstrings2(String s) {
    boolean[][] arr = new boolean[s.length()][s.length()];
    int res = 0;
    for (int j = 0; j < s.length(); j++) {
      for (int i = 0; i <= j; i++) {
        //s[i]==s[j] 时，要看 dp[i+1][j-1] 是不是一个回文串
        //例如 cabac，遍历到c=c，要看a到a之间是不是回文，也就是i+1 到 j-1之间
        //i==j时怎么办，下标岂不是-1？ fixme -- 这是 j-2<2 已经返回true了，不会走到下标为-1
        if (s.charAt(i) == s.charAt(j) && (j - i < 2 || arr[i + 1][j - 1])) {
          arr[i][j] = true;
          res++;
        }
      }
    }
    return res;
  }
}
