package algorithm.leetcode.my;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lsh
 * @date 2023/8/18 6:38
 */
public class Permute {

  List<List<Integer>> res = new ArrayList<>();
  LinkedList<Integer> path = new LinkedList<>();
  boolean[] vis;

  @Test
  public void test() {
    dfs(new int[]{1, 2, 2}, 0);
    System.out.println(res.toString());
  }

  @Test
  public void testSub() {
    subset(new int[]{1, 2, 3});

    System.out.println(res.toString());
  }

  public List<List<Integer>> subset(int[] nums) {
    vis = new boolean[nums.length];
    dfsAll(nums);
    return res;
  }

  public void dfsAll(int[] nums) {
    if (path.size() == nums.length) {
      res.add(new ArrayList<Integer>(path));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (vis[i]) {
        continue;
      }
      path.add(nums[i]);
      vis[i] = true;
      dfsAll(nums);
      vis[i] = false;
      path.removeLast();
    }

  }

  public void dfs(int[] nums, int start) {
    if (!res.contains(path)) {
      res.add(new ArrayList<Integer>(path));
    }
    if (start >= nums.length) {
      return;
    }
    for (int i = start; i < nums.length; i++) {
      path.add(nums[i]);
      dfs(nums, i + 1);
      path.removeLast();
    }
  }


}
