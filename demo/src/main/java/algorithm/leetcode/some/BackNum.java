package algorithm.leetcode.some;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/19 18:39
 * 234.
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 输入：head = [1,2,2,1]
 * 输出：true
 * <p>
 * 输入：head = [1,2]
 * 输出：false
 */
public class BackNum {


  @Test
  public void test() {
    int[] arr1 = new int[]{1, 2, 2, 1};
    int[] arr2 = new int[]{1, 2, 3, 4};
    System.out.println(get(arr2));

  }


  public ListNode get(int[] arr) {
    ListNode head = new ListNode(arr[0]);
    ListNode tail = head;
    for (int i : arr) {
      tail.next = new ListNode(i);
      tail = tail.next;
    }
    return head;
  }


  @Test
  public void test4() {
    ListNode node1 = new ListNode(1);
    ListNode node2 = node1;
    node2.val = 3;
    ListNode node3 = node2;
    node3.val = 5;
    System.out.println(node1.val + ":" + node2.val + ":" + node3.val);
    int i = 10;
    int j = i;
    j = 20;
    System.out.println(i + "--" + j);

  }

  @Test
  public void test2() {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    System.out.println(node1);
  }


  public boolean isPalindrome(ListNode head) {


    return true;
  }


  private class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }


}
