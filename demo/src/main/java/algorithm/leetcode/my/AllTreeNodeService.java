package algorithm.leetcode.my;

import algorithm.leetcode.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lsh
 * @date 2023/8/20 9:37
 */
public class AllTreeNodeService {

  @Test
  public void test() {
    System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7).toString());
    System.out.println(combinationSum(new int[]{2, 3, 5}, 8).toString());


  }

  LinkedList<Integer> path = new LinkedList<>();
  List<List<Integer>> res = new ArrayList<>();
  int sum = 0;
  boolean[] visited;


  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    path.clear();
    res.clear();
    sum = 0;
    combinationSumDfs(candidates, target, 0, 0);
    return res;
  }


  public void combinationSumDfs(int[] nums, int target, int start, int sum) {
    if (sum == target) {
      res.add(new ArrayList<>(path));
      sum = 0;
      return;
    } else if (sum > target) {
      sum = 0;
      return;
    }
    for (int i = start; i < nums.length; i++) {
      if (sum > target) {
        return;
      }
      path.add(nums[i]);
      sum += nums[i];
      combinationSumDfs(nums, target, i, sum);
      sum -= path.getLast();
      path.removeLast();
    }
  }


  public List<List<Integer>> subsetsWithDup(int[] nums) {
    path.clear();
    res.clear();
    Arrays.sort(nums);
    visited = new boolean[nums.length];
    dfs(nums, 0, visited);
    return res;
  }

  public void dfs(int[] nums, int start, boolean[] visited) {
    res.add(new ArrayList<>(path));
    for (int i = start; i < nums.length; i++) {
      if (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) {
        continue;
      }
      path.add(nums[i]);
      visited[i] = true;
      dfs(nums, i + 1, visited);
      visited[i] = false;
      path.removeLast();
    }
  }


  //输入：head = [1,2,3,4,5], n = 2
  //输出：[1,2,3,5]
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode slow = new ListNode(0, head);
    ListNode fast = new ListNode(0, head);
    //快指针先走
    while (n > 0) {
      n--;
      fast = fast.next;

    }
    if (fast == null) {
      return head.next;
    }

    while (fast != null) {
      slow = slow.next;
      fast = fast.next;
    }
    slow.next = slow.next.next;
    return head;

  }


  public boolean checkTree(TreeNode root) {
    return ((root.left.val + root.right.val) == root.val);
  }
}
