package algorithm.leetcode.my;

import algorithm.leetcode.ListNode;
import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/27 15:52
 */
public class RemoveElements {

  @Test
  public void test() {
    ListNode node = removeElements(getNode(new int[]{1, 2, 2,3,4,2}), 2);
    ListNode node2 = removeElements(getNode(new int[]{1, 2, 6, 3, 4, 5, 6}), 6);
    ListNode node3 = removeElements(getNode(new int[]{3, 3, 3}), 3);
    System.out.println(node);
  }

  //
  public ListNode removeElements(ListNode head, int val) {
    if (head!=null && head.val == val) {
      if (head.next != null) {
        head = head.next;
        return removeElements(head,val);
      } else {
        return null;
      }
    }
    if (head == null) {
      return head;
    }

    ListNode temp = new ListNode();
    temp.val = head.val;
    temp.next = removeElements(head.next, val);
    return temp;


  }

  //递归 todo
  public ListNode removeElements3(ListNode head, int val) {
    if (head == null || head.next == null) {
      return head;
    }
    head.next = removeElements(head.next, val);
    if (head.val == val) {
      return head.next;
    }
    return head;


  }


  public ListNode getNode(int[] arr) {
    ListNode head = new ListNode();
    ListNode tail = head;
    for (int i : arr) {
      tail.next = new ListNode(i);
      tail = tail.next;
    }
    return head.next;
  }

  //双44%
  public ListNode removeElements2(ListNode head, int val) {
    ListNode res = new ListNode();
    ListNode tail = res;
    while (head != null) {
      if (head.val != val) {
        tail.next = new ListNode(head.val);
        tail = tail.next;
      }
      head = head.next;
    }
    return res.next;
  }
}
