package algorithm.leetcode.my;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author lsh
 * @date 2023/6/26 22:18
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * <p>
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 */
public class LongestCommonPrefix {

  @Test
  public void test() {
    System.out.println(longestCommonPrefix2(new String[]{"flower", "flow", "flight"}));
    System.out.println(longestCommonPrefix2(new String[]{"1", "2", "3"}));
    System.out.println(longestCommonPrefix2(new String[]{"dog","racecar","car"}));
  }

  public String longestCommonPrefix(String[] strs) {

    StringBuilder sb = new StringBuilder();
    for (String s : strs) {

    }


    return "";
  }


  //先排序，在比较首尾
  public String longestCommonPrefix2(String[] strs) {

    StringBuilder sb = new StringBuilder();
    Arrays.sort(strs);
    int min = Math.min(strs[0].length(), strs[strs.length - 1].length());
    int temp = 0;
    for (int i = 0; i < min; i++) {
      if (temp == i && strs[0].charAt(i) == strs[strs.length - 1].charAt(i)) {
        sb.append(strs[0].charAt(i));
        temp++;
      } else {
        return sb.toString();
      }
    }
    return sb.toString();
  }
}
