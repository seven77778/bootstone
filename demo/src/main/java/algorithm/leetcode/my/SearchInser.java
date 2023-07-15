package algorithm.leetcode.my;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/22 20:53
 *
 * 35.
 */
public class SearchInser {

  @Test
  public void test() {

    System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 0));
    System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 7));
    System.out.println(searchInsert(new int[]{1, 3}, 3));

    System.out.println(searchInsert(new int[]{1, 3}, 3));
    System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 0));
    System.out.println(searchInsert(new int[]{1, 2, 4, 6, 7}, 3));
    System.out.println(searchInsert(new int[]{1, 3, 5}, 2));
    System.out.println(searchInsert(new int[]{1}, 1));
    System.out.println(searchInsert(new int[]{3, 6, 7, 8, 10}, 5));
    System.out.println(searchInsert(new int[]{1, 3, 4, 5, 6}, 3));
    System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2));
    System.out.println(searchInsert(new int[]{1, 2, 3, 4, 5}, 4));


  }


  public int searchInsert(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      int mid = (left + right) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return nums[left] > target ? left : nums[left] == target ? left : left + 1;
  }

  public int searchInsert3(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return left;

  }

  //递归，嘿嘿
  public int searchInsert2(int[] nums, int target) {
    if (nums[0] > target) {
      return 0;
    }
    if (nums[nums.length - 1] < target) {
      return nums.length;
    }
    return search(0, nums.length - 1, target, nums);
  }



  public int search(int start, int end, int target, int[] nums) {
    int temp = (start + end) / 2;
    if (nums[temp] < target && nums[temp + 1] > target) {
      return temp + 1;
    }
    if (nums[temp] == target) {
      return temp;
    } else if (nums[temp] < target) {
      return search(temp + 1, end, target, nums);
    } else {
      return search(0, temp, target, nums);
    }
  }
}
