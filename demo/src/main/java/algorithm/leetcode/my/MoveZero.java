package algorithm.leetcode.my;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/21 14:30
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * <p>
 * 输入: nums = [0]
 * 输出: [0]
 */
public class MoveZero {


  @Test
  public void test() {
    int[] nums = new int[]{0, 1, 0, 2, 3, 4};
    int[] nums2 = new int[]{0};
    int[] nums3 = new int[]{0, 0, 0, 1, 2, 3};
    moveZeroes2(nums);
    moveZeroes2(nums2);
    moveZeroes2(nums3);
    System.out.println(123);
  }

  //只记录不是0的数字
  public void moveZeroes2(int[] nums) {

    int size = 0;
    for (int i : nums) {
      if (i != 0) {

        nums[size] = i;
        size++;
      }
    }
    if (size > 0) {
      for (int j = nums.length - 1; j >= size; j--) {
        nums[j] = 0;
      }
    }


  }


  //双5%，太弱了
  public void moveZeroes(int[] nums) {
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] == 0) {
          if (nums[j] != 0) {
            sum++;
            nums[i] = nums[j];
            nums[j] = 0;
          }
        }
      }
    }

  }
}
