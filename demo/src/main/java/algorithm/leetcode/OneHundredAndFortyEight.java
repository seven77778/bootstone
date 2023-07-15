package algorithm.leetcode;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/5 8:57
 * <p>
 * NO148.
 * <p>
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * <p>
 * 输入：head = []
 * 输出：[]
 * <p>
 */

public class OneHundredAndFortyEight {

    @Test
    public void test() {
        ListNode listNode = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        ListNode ll = sortList(listNode);
        System.out.println(ll);

    }


    public ListNode sortList(ListNode head) {

        while (head.next != null) {
            if (head.val > head.next.val) {
                ListNode node = new ListNode();
                node = head;
                if (head.next.next != null) {
                    head = head.next.next;

                } else {
                    break;
                }
            } else {
                head = head.next;
            }
        }
        return head;
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
