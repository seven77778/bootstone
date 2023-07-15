package algorithm.leetcode.my;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/7/11 21:03
 */
public class ReverseString {


  @Test
  public void test() {
    char[] s = new char[]{'h','e','l','l','o'};
    reverseString(s);
    System.out.println(s);
  }


  public void reverseString(char[] s) {
    int left=0;
    int right = s.length-1;
    while (left<=right){
      char temp = s[right];
      s[right]=s[left];
      s[left] = temp;
      left++;
      right--;
    }
  }

}
