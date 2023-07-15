package algorithm.leetcode;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/3 9:51
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * <p>
 * [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]
 * [5,6,4]
 */
public class Two {

    @Test
    public void test() {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode addd = addTwoNumbers3(l1, l2);
        System.out.println(addd);
    }


    /**
     * 官方答案
     * 两个问题，链表的遍历 + 结果链表的保存，也就是指针的指向问题
     */

    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int addCount = 0;
        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += addCount;
            addCount = sum / 10;
            if (head == null) {
                head = tail= new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;//? 关键
            }
            if (addCount > 0) {
                tail.next = new ListNode(addCount);
            }

        }
        return head;
    }
//
//    public ListNode addTwoNumbers4(ListNode l1, ListNode l2) {
//        ListNode tail = null;
//        int addCount = 0;
//        while (l1 != null || l2 != null) {
//            int sum = 0;
//            if (l1 != null) {
//                sum += l1.val;
//                l1 = l1.next;
//            }
//            if (l2 != null) {
//                sum += l2.val;
//                l2 = l2.next;
//            }
//            sum += addCount;
//            addCount = sum / 10;
//            if (tail == null) {
//               tail= new ListNode(sum % 10);
//            } else {
//                tail.next = new ListNode(sum % 10);
//                tail = tail.next;//? 关键
//            }
//            if (addCount > 0) {
//                tail.next = new ListNode(addCount);
//            }
//
//        }
//        return tail;
//    }

    //递归  返回两个链表相加的头部
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        return add(l1, l2, 0);
    }

    public ListNode add(ListNode l1, ListNode l2, int bit) {
        if (l1 == null && l2 == null && bit == 0) {
            return null;
        }
        int val = bit;
        if (l1 != null) {
            val += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            val += l2.val;
            l2 = l2.next;
        }
        ListNode node = new ListNode(val % 10);
        node.next = add(l1, l2, val / 10);
        return node;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        StringBuilder sb1 = new StringBuilder();
        sb1.append(l1.val);
        ListNode node = new ListNode();
        node = l1.next;
        while (true) {
            if (node != null) {
                sb1.append(node.val);
                node = node.next;
            } else {
                break;
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(l2.val);
        node = new ListNode();
        node = l2.next;
        while (true) {
            if (node != null) {
                sb2.append(node.val);
                node = node.next;
            } else {
                break;
            }
        }
        String s1 = sb1.reverse().toString();
        String s2 = sb2.reverse().toString();
        Long ii = Long.valueOf(s1) + Long.valueOf(s2);
        String str = String.valueOf(ii);
        ListNode res1 = new ListNode();
        res1.val = Integer.valueOf(str.substring(0, 1));
        for (int i = 1; i < str.length(); i++) {
            ListNode inner = new ListNode();
            inner.val = Integer.valueOf(str.substring(i, i + 1));
            inner.next = res1;
            res1 = inner;

        }

        return res1;
    }


    class ListNode {
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
