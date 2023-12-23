package algorithm.leetcode;

/**
 * @author lsh
 * @date 2023/6/20 10:11
 */
public class ListNode {

  public static ListNode get(int[] arr){
    ListNode head = new ListNode();
    ListNode tail = head;
    for(int i:arr){
      tail.next=new ListNode(i);
      tail=tail.next;
    }
    return head.next;
  }

  public int val;
  public ListNode next;

  public ListNode() {
  }

  public ListNode(int val) {
    this.val = val;
  }

  public ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}
