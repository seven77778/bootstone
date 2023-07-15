package algorithm.leetcode.my;

import algorithm.leetcode.ListNode;
import org.junit.Test;

/**
 * @author lsh
 * @date 2023/7/13 21:07
 * L0 → L1 → … → Ln - 1 → Ln
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 */
public class ReorderList {

  private ListNode get(int[] arr){
    ListNode head = new ListNode();
    ListNode tail = head;
    for(int i:arr){
      tail.next=new ListNode(i);
      tail=tail.next;
    }
    return head.next;
  }


  @Test
  public void test(){
    reorderList(get(new int[]{1,2,3,4,5}));
//    middleNode(get(new int[]{1,2,3,4,5,6}));
  }

  //找到链表的中点
  public ListNode middleNode(ListNode head) {
    //快慢指针找到中间，一分为二
    ListNode fast =head;
    ListNode slow=head;
    while (fast!=null && fast.next!=null && slow.next!=null){
      fast=fast.next.next;
      slow=slow.next;
    }
    return slow;
  }


  public void reorderList(ListNode head) {

    //快慢指针找到中间，一分为二
    ListNode fast =head;
    ListNode slow=head;
    while (fast!=null && fast.next!=null && slow.next!=null){
      fast=fast.next.next;
      slow=slow.next;
    }
    System.out.println(slow);
    //head 还是head，遍历一半即可

    slow=slow.next;
    //slow 需要反转
    ListNode left =slow;
    ListNode right = null;
    while (left!=null){
      ListNode temp = left.next;
      left.next=right;
      right = left;
      left=temp;
    }

    System.out.println(slow);
    ListNode t1 ;
    ListNode t2;
    while (right!=null && head!=null){
      t1 = head.next;
      t2 = right.next;

      head.next = right;
      head = t1;

      right.next = head;
      right = t2;


    }

    System.out.println(head);
  }


}
