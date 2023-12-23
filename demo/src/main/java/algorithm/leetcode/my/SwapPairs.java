package algorithm.leetcode.my;

import algorithm.leetcode.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lsh
 * @date 2023/8/6 14:13
 */
public class SwapPairs {

  @Test
  public void test() {
    getIntersectionNode(ListNode.get(new int[]{4,1,8,4,5}),ListNode.get(new int[]{5,6,1,8,4,5}));
   getIntersectionNode(ListNode.get(new int[]{1,2,3}),ListNode.get(new int[]{4}));

  }

  ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }
    ListNode pA = headA, pB = headB;
    while (pA != pB) {
      pA = pA == null ? headB : pA.next;
      pB = pB == null ? headA : pB.next;
    }
    return pA;
  }


  public ListNode deleteNode(ListNode head, int val) {
    ListNode res=new ListNode();
    ListNode tail = res;
    while (head!=null){
      if(head.val!=val){
        tail.next=head;
        tail = tail.next;
      }else {
        tail.next=null;
      }
      head=head.next;
    }
    return res.next;
  }

  public boolean isPalindrome(ListNode head) {
    if(head==null){
      return false;
    }
    ListNode fast = head;
    ListNode slow = head;
    while (fast.next!=null && fast.next.next!=null){
      fast=fast.next.next;
      slow=slow.next;
    }
    ListNode right=null;
    ListNode left=slow.next;
    while (left!=null){
      ListNode temp = left.next;
      left.next=right;
      right=left;
      left=temp;
    }
    while (right!=null){
      if(right.val!=head.val){
        return false;
      }
      right=right.next;
      head=head.next;
    }
    return true;


  }


  public ListNode reverseList(ListNode head) {

    ListNode right=null;
    ListNode left = head;
    while (left!=null){
      ListNode temp=left.next;
      left.next=right;
      right=left;
      left=temp;
    }
    return right;
  }

  public ListNode sortList(ListNode head) {
    return null;
  }

  public ListNode mergeKLists(ListNode[] lists) {
    List<Integer> list = new ArrayList<>();
    for (ListNode node : lists) {
      while (node != null) {
        list.add(node.val);
        node = node.next;
      }
    }
    ListNode res=new ListNode();
    ListNode tail = res;
    Integer[] integers = list.toArray(new Integer[0]);
    Arrays.sort(integers);
    for (Integer x : integers) {
      tail.next = new ListNode(x);
      tail = tail.next;
    }
    return res.next;
  }


  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode result = new ListNode();
    ListNode tail = result;
    while (head != null && head.next != null) {
      ListNode temp = head.next.next;
      ListNode tempH = head;
      head = head.next;
      head.next = tempH;
      head.next.next = temp;
      tail.next = head;
      tail = tail.next.next;
      head = temp;
    }
    return result.next;

  }


}
