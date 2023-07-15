package algorithm.leetcode.my;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author lsh
 * @date 2023/7/2 6:10
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 */
public class ClimbStairs {


  @Test
  public void test() {
    System.out.println(climbStairs(4));
    System.out.println(climbStairs(3));
    System.out.println(climbStairs(5));

  }

  HashMap<Integer, Integer> map = new HashMap<>();

  //试试递归 会超时
  public int climbStairs(int n) {
    if (map.containsKey(n)) {
      return map.get(n);
    }
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 2;
    }
    int x = climbStairs(n - 1);
    int y = climbStairs(n - 2);
    map.put(n - 1, x);
    map.put(n - 2, y);
    return x + y;
  }


  //哇啊哦！一次通过！
  public int climbStairs2(int n) {
    int[] arr = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      if (i == 1) {
        arr[1] = 1;
      }
      if (i == 2) {
        arr[2] = 2;
      } else if (i >= 3) {
        arr[i] = arr[i - 1] + arr[i - 2];

      }
    }
    return arr[n];
  }

}
