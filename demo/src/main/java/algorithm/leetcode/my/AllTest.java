package algorithm.leetcode.my;

import algorithm.leetcode.ListNode;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author lsh
 * @date 2023/7/5 14:48
 */
public class AllTest {

  @Test
  public void test2(){
    ListNode node = reverseList(getNode(new int[]{1,2,3}));
    System.out.println();
  }

  public ListNode reverseList(ListNode head) {
    //左指针
    ListNode left =head;
    //右指针
    ListNode right = null;
    while (left!=null){
      //暂存下一个
      ListNode temp=left.next;
      //左指针指向右边
      left.next=right;
      //局部交换
      right=left;
      //拿到暂存值，继续往前
      left=temp;
    }
    return right;
  }


  public ListNode getNode(int[] arr){
    ListNode head = new ListNode();
    ListNode tail = head;
    for(int i:arr){
      tail.next = new ListNode(i);
      tail=tail.next;
    }
    return head.next;
  }




  @Test
  public void test() {
    System.out.println(isSubsequence("abc", "ahbgdc"));

    System.out.println(isSubsequence("abc", "aebtce"));
    System.out.println(isSubsequence("aaa", "bbaa"));
    System.out.println(isSubsequence("aa", ""));
    System.out.println(isSubsequence("acb", "aeeeebc"));
    System.out.println(isSubsequence("acb", "aeeeebc"));

    System.out.println(isSubsequence("axc", "ahbgdcxxcc"));
  }

  /**
   * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
   * 输入：s = "abc", t = "ahbgdc"
   * 输出：true
   * <p>
   * 输入：s = "axc", t = "ahbgdc"
   * 输出：false
   */
  int sum = 0;

  public boolean isSubsequence(String s, String t) {
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


  HashMap<Integer, Integer> map = new HashMap<>();

  public int fib(int n) {
    if (n == 0 || n == 1) {
      return n;
    }
    if (map.containsKey(n)) {
      return map.get(n);
    }
    int temp1 = fib(n - 1);
    int temp2 = fib(n - 2);
    map.put(n - 1, temp1);
    map.put(n - 2, temp2);
    return temp1 + temp2;
  }
}
