package algorithm.leetcode.my;

import algorithm.leetcode.ListNode;
import org.junit.Test;

import java.sql.SQLOutput;

/**
 * @author lsh
 * @date 2023/6/22 9:50
 */
public class MergeTwoLists {

  @Test
  public void test() {
//    System.out.println(mergeTwoLists2(getNode(new int[]{1, 2, 3}), getNode(new int[]{4, 5, 6})));
//    System.out.println(mergeTwoLists(getNode(new int[]{4, 4, 5}), getNode(new int[]{1, 2, 9})));
//    System.out.println(mergeTwoLists(getNode(new int[]{}), getNode(new int[]{1, 1, 9})));


    System.out.println(mergeTwoLists2(list1,list2));

  }
  ListNode list1 = getNode(new int[]{1, 2, 3});
  ListNode list2 = getNode(new int[]{4, 5, 6});

  //官方推荐递归？？
  public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    } else if (l1.val < l2.val) {
      l1.next = mergeTwoLists2(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLists2(l1, l2.next);
      return l2;
    }


  }


  public ListNode getNode(int[] arr) {
    if (arr.length == 0) {
      return null;
    }
    ListNode head = new ListNode(arr[0]);
    ListNode tail = head;
    for (int i : arr) {
      tail.next = new ListNode(i);
      tail = tail.next;

    }
    return head.next;


  }


  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    }
    if (list2 == null) {
      return list1;
    }
    ListNode head = new ListNode(0);
    ListNode tail = head;
    while (list1 != null && list2 != null) {
      if (list1.val >= list2.val) {
        tail.next = list2;
        list2 = list2.next;

      } else {
        tail.next = list1;
        list1 = list1.next;
      }
      tail = tail.next;
    }

    tail.next = list1 != null ? list1 : list2;
    return head.next;
  }
}
