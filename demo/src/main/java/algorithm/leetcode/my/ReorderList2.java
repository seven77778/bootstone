package algorithm.leetcode.my;

import algorithm.leetcode.ListNode;
import org.junit.Test;

/**
 * @author lsh
 * @date 2023/7/20 21:05
 */
public class ReorderList2 {


  @Test
  public void test() {
    reorderList(get(new int[]{1, 2, 3, 4}));
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


  public void reorderList(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    ListNode left = slow.next;
    slow.next = null;
    ListNode right = null;
    while (left != null) {
      ListNode temp = left.next;
      left.next = right;
      right = left;
      left = temp;
    }
    ListNode halfAfter = right;
    //合并
    merge(head,halfAfter);
  }

  public void merge(ListNode l1,ListNode l2){
      ListNode head = new ListNode();
      ListNode tail = head;
      boolean flag = true;
      while (l1!=null && l2!=null){
        if(flag){
          tail.next=l1;
          flag=false;
          l1=l1.next;
        }else {
          tail.next=l2;
          flag=true;
          l2=l2.next;
        }
        tail=tail.next;
      }
      tail.next = l1==null?l2:l1;
  }


}
