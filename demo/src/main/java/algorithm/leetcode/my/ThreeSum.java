package algorithm.leetcode.my;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author lsh
 * @date 2023/8/7 21:26
 */
public class ThreeSum {

  @Test
  public void test() {
    System.out.println(Arrays.toString(twoSum(new int[]{3,3},6)));
//    System.out.println((threeSum(new int[]{-1, 0, 1, 0})).toString());
//    System.out.println((threeSum(new int[]{0, 0, 0, 0, 0})).toString());
//    System.out.println((threeSum(new int[]{-1, 0, 1, 2, -1, -4})).toString());
//    System.out.println((threeSum(new int[]{0, 1, 1})).toString());
//    System.out.println((threeSum(new int[]{0, 0, 0})).toString());
  }

  public int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      int x = i + 1;
      while (x < nums.length) {
        if (nums[i] + nums[x] == target) {
          return new int[]{i, x};
        }
        x++;
      }
    }
    return new int[]{};
  }

  public List<List<Integer>> threeSum(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      int left = i;
      int right1 = i + 1;
      int right2 = i + 2;
      while (right1 < nums.length && right2 < nums.length) {
        if (nums[i] + nums[right1] + nums[right2] == 0) {
        
        }
      }
    }
    return null;
  }


}
