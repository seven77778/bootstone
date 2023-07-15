package algorithm.leetcode.some;

import algorithm.leetcode.ListNode;
import org.junit.Test;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lsh
 * @date 2023/6/20 10:09
 */
public class BackNumAgain {

  @Test
  public void test() {
    System.out.println(isPalindrome(get(new int[]{1, 2, 3, 4})));
    System.out.println(isPalindrome(get(new int[]{1, 2, 2, 1})));
    System.out.println(isPalindrome(get(new int[]{1, 0, 1})));
    System.out.println(isPalindrome(get(new int[]{1, 0, 1, 3, 4})));
  }

  public ListNode get(int[] arr) {
    ListNode head = new ListNode();
    ListNode tail = head;
    for (int i : arr) {
      tail.next = new ListNode(i);
      tail = tail.next;
    }
    return head.next;
  }


  //快慢指针
  public boolean isPalindrome2(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    ListNode temp ;
    while (fast.next!=null && fast.next.next!=null){
      fast=fast.next.next;
      slow=slow.next;
    }


    return true;
  }
    public boolean isPalindrome(ListNode head) {
    List<ListNode> list = new ArrayList<>();
    while (head != null) {
      list.add(head);
      head = head.next;
    }
    int size = list.size()-1;
    int start = 0;
    while (size >= start) {
      if (list.get(size).val != list.get(start).val) {
        return false;
      }
      size--;
      start++;
    }
    return true;
  }

}
