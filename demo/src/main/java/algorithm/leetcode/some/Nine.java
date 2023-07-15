package algorithm.leetcode.some;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lsh
 * @date 2023/6/17 11:42
 */
public class Nine {


    @Test
    public void test() {
        System.out.println( isPalindrome( 12344 ) );
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        int length = (x + "").length();
        Stack<Integer> stack = new Stack<>();
        Integer integer = new Integer( x );
        return false;

    }


    /*********************************************************************/

    @Test
    public void test2() {
        System.out.println( isPalindrome3( build( new int[]{1, 2, 3,4,5,6} ) ) );
        System.out.println( isPalindrome3( build( new int[]{1, 2, 3, 3, 2, 1} ) ) );
        System.out.println( isPalindrome2( build( new int[]{1, 0, 1} ) ) );
        System.out.println( isPalindrome2( build( new int[]{1, 2, 3, 4} ) ) );
        System.out.println( isPalindrome2( build( new int[]{1, 2, 3, 4,5} ) ) );


    }

    // todo !!!
    public ListNode build(int[] arr) {
        ListNode head = new ListNode( arr[0] );
        ListNode next = head;
        for (int num : arr) {
            next.next = new ListNode( num );
            next = next.next;
        }
        return head.next;
    }


    //不要prepre
    public static boolean isPalindrome66(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        ListNode pre = null;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode temp = slow.next;
            slow.next = pre;
            pre = slow;
            slow = temp;
        }
        if(fast != null) {
            slow = slow.next;
        }
        while(pre != null && slow != null) {
            if(pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }
        return true;
    }

    //todo 快慢指针
    public boolean isPalindrome3(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        ListNode pre = head, prepre = null;
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            pre.next = prepre;
            prepre = pre;
        }
        System.out.println(pre);
        if(fast != null) {
            slow = slow.next;
        }
        while(pre != null && slow != null) {
            if(pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }
        return true;

    }


    //使用数组--还不错，双指针
    public boolean isPalindrome2(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add( head );
            head = head.next;
        }
        int right = 0;
        int left = list.size() - 1;
        for (ListNode node : list) {
            if (left >= right && list.get( right ).val != list.get( left ).val) {
                return false;
            }
            right++;
            left--;
        }
        return true;
    }

    //双5%…………
    public boolean isPalindrome(ListNode head) {
        if (head.next == null) {
            return true;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode next = head;
        ListNode add = head;
        stack.push( head );
        int length = 1;
        while ((add = add.next) != null) {
            length++;
        }
        int temp = 0;
        while ((next = next.next) != null) {
            temp++;
            if (length % 2 != 0 && temp == length / 2) {
                continue;
            }
            if (!stack.isEmpty() && temp >= length / 2 && stack.peek().val == next.val) {
                stack.pop();
            } else {
                stack.push( next );
            }
        }
        return stack.isEmpty();
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
