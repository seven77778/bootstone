package algorithm.leetcode.my;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/22 17:42
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 */
public class KFindMidNum {

  @Test
  public void test() {
    int[] arr=new int[]{1,2};
    int[] arr2=new int[]{3,2};
    System.out.println(findMedianSortedArrays(new int[]{2}, new int[]{}));
    System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    System.out.println(findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{4, 5, 6}));
    System.out.println(findMedianSortedArrays(new int[]{1, 2, 3, 4}, new int[]{5, 6}));
    System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{4, 5, 6, 7}));

  }


  //开辟新数组
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length == 0 && nums2.length == 0) {
      return 0;
    }
    int size = nums1.length + nums2.length;
    int[] res = new int[size];
    int i = 0;
    int j = 0;
    for (int k = 0; k < size; k++) {
      if (i == nums1.length) {
        res[k] = nums2[j];
        j++;
      } else if (j == nums2.length) {
        res[k] = nums1[i];
        i++;
      } else if (nums1[i] < nums2[j]) {
        res[k] = nums1[i];
        i++;
      } else if (nums1[i] >= nums2[j]) {
        res[k] = nums2[j];
        j++;
      }
    }
    if (res.length == 1) {
      return res[0];
    } else if (res.length % 2 == 0) {
      double d1 = res[res.length / 2];
      double d2 = res[res.length / 2 - 1];
      return (d1 + d2) / 2;
    } else {
      return (double) res[(res.length - 1) / 2];
    }

  }


}
