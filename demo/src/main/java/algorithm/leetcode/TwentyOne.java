package algorithm.leetcode;

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lsh
 * @date 2023/6/5 14:51
 * <p>
 * NO21.
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * <p>
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * <p>
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 */
public class TwentyOne {

  //new 一个链表
//    @Test
//    public void testt() {
//        int[] arr = new int[]{1, 2, 3, 4, 5};
//        ListNode root = new ListNode(arr[0]);
//        ListNode other = root;
//
//        for (int i = 1; i < arr.length; i++) {
//            ListNode temp = new ListNode(arr[i]);
//            other.next=temp;
//            other=temp;
//
//        }
//        System.out.println(root);
//    }


  @Test
  public void test() {
    ListNode list = mergeTwoLists(get(new int[]{1,2,3}), get(new int[]{1,2,4,5}));
    System.out.println(list);

  }


  //递归
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    }
    if (list2 == null) {
      return list1;
    }
    if (list1.val < list2.val) {
      list1.next = mergeTwoLists(list1.next, list2);
      return list1;
    } else {
      list2.next = mergeTwoLists(list1, list2.next);
      return list2;
    }
  }
  public ListNode get(int[] arr){
    ListNode head = new ListNode();
    ListNode tail = head;
    for(int a:arr){
      tail.next=new ListNode(a);
      tail=tail.next;
    }
    return head.next;
  }



  public ListNode mergeTwoLists3(ListNode list1, ListNode list2) {
    if (list1 == null && list2 == null) {
      return null;
    }
    if (list1 == null) {
      return list2;
    }
    if (list2 == null) {
      return list1;
    }
    ListNode root = new ListNode(-1);
    ListNode other = root;
    while (list1 != null && list2 != null) {
      if (list1.val < list2.val) {
        other.next = list1;
        list1 = list1.next;
      } else {
        other.next = list2;
        list2 = list2.next;
      }
      other = other.next;
    }
    other.next = list1 != null ? list1 : list2;// ???
    return root.next;

  }


  public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    }
    if (list2 == null) {
      return list1;
    }

    if (list1.val < list2.val) {
      list1.next = mergeTwoLists2(list1.next, list2);
      return list1;
    } else {
      list2.next = mergeTwoLists2(list1, list2.next);
      return list2;
    }


  }

  //3 ms
  //, 在所有 Java 提交中击败了
  //0.79%
  //的用户 --hhh
  public ListNode mergeTwoLists99(ListNode list1, ListNode list2) {
    List<Integer> arrList = new ArrayList<>();
    if (list1 == null && list2 == null) {
      return null;
    }
    if (list1 != null) {
      arrList.add(list1.val);
      while (list1.next != null) {
        arrList.add(list1.next.val);
        list1 = list1.next;
      }
    }
    if (list2 != null) {
      arrList.add(list2.val);
      while (list2.next != null) {
        arrList.add(list2.next.val);
        list2 = list2.next;
      }
    }
    Integer[] arr = arrList.toArray(new Integer[arrList.size()]);
    Arrays.sort(arr);
    ListNode root = new ListNode(arr[0]);
    ListNode other = root;
    for (int i = 1; i < arr.length; i++) {
      ListNode temp = new ListNode(arr[i]);
      other.next = temp;
      other = temp;
    }
    return root;
  }

  public class ListNode {
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
