package algorithm.leetcode.my;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/28 7:51
 */
public class LengthOfLastWord {


  @Test
  public void test() {
    String s="aaa aaa";
    System.out.println(s.length());
    System.out.println(s.toCharArray().length);
    System.out.println(lengthOfLastWord("aaa bbb agdsfg"));
    System.out.println(lengthOfLastWord("aaa  bbb  agdsfg"));
    System.out.println(lengthOfLastWord("   fly me   to   the moon  "));
    System.out.println(lengthOfLastWord("   fly me   to   the moon "));
  }


  public int lengthOfLastWord(String s) {
    int sum = 0;
    for (int i = s.length() - 1; i >= 0; i--) {
      if (s.charAt(i) != ' ') {
        sum++;
      } else if (sum != 0) {
        return sum;
      }
    }
    return sum;
  }

  //能过，意义不大
  public int lengthOfLastWord2(String s) {

    String[] srr = s.split(" ");
    return srr[srr.length - 1].length();

  }
}
