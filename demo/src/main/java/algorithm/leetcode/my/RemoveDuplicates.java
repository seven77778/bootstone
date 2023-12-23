package algorithm.leetcode.my;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lsh
 * @date 2023/7/30 21:46
 */
public class RemoveDuplicates {


  @Test
  public void test() {
//    System.out.println(removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
//    System.out.println(removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 4}));
//    System.out.println(removeDuplicates(new int[]{1, 1, 2, 3}));
//    System.out.println(removeDuplicates(new int[]{1, 1, 2}));
//
//    System.out.println(removeDuplicates(new int[]{1, 1, 2, 3, 3}));

    System.out.println(removeElement(new int[]{2,2,2}, 2));
    System.out.println(removeElement(new int[]{2,2,3}, 2));
    System.out.println(removeElement(new int[]{1}, 1));
    System.out.println(removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    System.out.println(removeElement(new int[]{3, 2, 2, 3}, 3));


  }


  public int removeElement(int[] nums, int val) {
    int left=0;
    for (int cur = 0; cur < nums.length; cur++) {
      if(nums[cur]!=val){
        nums[left]=nums[cur];
        left++;
      }
    }
    return left;

  }


  public int removeDuplicates(int[] nums) {
    int last = 0;
    int next = 1;
    for (int cur = 1; cur < nums.length; cur++) {
      if (nums[last] == nums[cur]) {
      } else {
        nums[next] = nums[cur];
        last++;
        next++;
      }
    }
    return next;
  }
}
