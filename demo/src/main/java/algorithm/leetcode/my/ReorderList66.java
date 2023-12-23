package algorithm.leetcode.my;

import algorithm.leetcode.ListNode;
import org.junit.Test;

/**
 * @author lsh
 * @date 2023/7/20 22:37
 */
public class ReorderList66 {
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
    if (head == null) {
      return;
    }
    ListNode mid = middleNode(head);
    ListNode l1 = head;
    ListNode l2 = mid.next;
    mid.next = null;
    l2 = reverseList(l2);
    mergeList(l1, l2);
  }

  public ListNode middleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode nextTemp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = nextTemp;
    }
    return prev;
  }

  public void mergeList(ListNode l1, ListNode l2) {
    ListNode l1_tmp;
    ListNode l2_tmp;
    while (l1 != null && l2 != null) {
      l1_tmp = l1.next;
      l2_tmp = l2.next;

      l1.next = l2;
      l1 = l1_tmp;

      l2.next = l1;
      l2 = l2_tmp;
    }
  }


}
