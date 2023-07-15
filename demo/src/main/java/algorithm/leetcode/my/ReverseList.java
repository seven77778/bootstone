package algorithm.leetcode.my;

import algorithm.leetcode.ListNode;
import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/27 9:53
 * <p>
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * <p>
 * 输入：head = []
 * 输出：[]
 */
public class ReverseList {

  @Test
  public void ttt() {
    System.out.println(add(5));
  }

  public int add(int num) {
    if (num == 1) {
      return 1;
    }
    return add(num-1)+num;

  }

  @Test
  public void test() {
    ListNode res = reverseList(getNode(new int[]{1, 2, 3, 4, 5}));
    System.out.println("--" + i);
    reverseList(getNode(new int[]{2, 1}));
    reverseList(getNode(new int[]{}));

  }

  //递归
  int i = 0;

  public ListNode reverseList(ListNode head) {
    i++;
    if (head == null || head.next == null) {
      return head;
    } else {
      ListNode p = reverseList(head.next);
      head.next.next = head;
      head.next = null;
      return p;
    }
  }


  //改变指针
  public ListNode reverseList2(ListNode head) {
    ListNode tail = null;
    while (head != null) {
      ListNode temp = new ListNode(head.val);
      temp.next = tail;
      head = head.next;
      tail = temp;
    }
    return tail;
  }


  public ListNode getNode(int arr[]) {
    ListNode head = new ListNode();
    ListNode tail = head;
    for (int i : arr) {
      tail.next = new ListNode(i);
      tail = tail.next;
    }
    return head.next;
  }

}
