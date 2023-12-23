package algorithm.leetcode.my;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author lsh
 * @date 2023/8/5 17:43
 */
public class AddBinary {

  @Test
  public void test() {
    merge(new int[]{4,0,0,0,0,0}, 1, new int[]{1,2,3,5,6}, 5);
    merge(new int[]{0}, 0, new int[]{1}, 1);
    merge(new int[]{2,0}, 1, new int[]{1}, 1);
    merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
    merge(new int[]{1, 3, 5, 0, 0, 0}, 3, new int[]{2, 4, 6}, 3);
    merge(new int[]{0}, 1, new int[]{}, 0);


  }

  public void merge2(int[] nums1, int m, int[] nums2, int n) {


  }

  public void merge(int[] nums1, int m, int[] nums2, int n) {
    if (m == 0 && n != 0) {
      for(int x=0;x<n;x++){
        nums1[x] = nums2[x];
      }

      System.out.println(Arrays.toString(nums1));
      return;
    }
    if (n == 0) {
      return;
    }
    int left1 = m - 1;
    int left2 = n - 1;
    int right = m + n - 1;
    while (right >= 0) {
      if (left1 < 0) {
        nums1[right] = nums2[left2];
        left2--;
      } else if (left2 < 0) {
        nums1[right] = nums1[left1];
        left1--;
      } else if (nums1[left1] > nums2[left2]) {
        nums1[right] = nums1[left1];
        left1--;
      } else {
        nums1[right] = nums2[left2];
        left2--;
      }
      right--;
    }
    System.out.println(Arrays.toString(nums1));
  }


  public int search2(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return -1;

  }

  public int search(int[] nums, int target) {
    if ((nums.length == 1 && nums[0] != target) || (nums[0] > target) || target > nums[nums.length - 1]) {
      return -1;
    }
    return ser(nums, 0, nums.length, target);
  }

  public int ser(int[] nums, int left, int right, int target) {
    int mid = (left + right) / 2;
    if (mid > 1 && nums[mid - 1] < target && nums[mid] > target) {
      return -1;
    }
    if (nums[mid] == target) {
      return mid;
    } else if (nums[mid] > target) {
      return ser(nums, left, mid - 1, target);
    } else {
      return ser(nums, mid + 1, right, target);
    }
  }


  public String addStrings(String num1, String num2) {
    int num1Length = num1.length() - 1;
    int num2Length = num2.length() - 1;
    int move = 0;
    StringBuilder sb = new StringBuilder();
    while (num1Length >= 0 || num2Length >= 0 || move > 0) {
      int temp = (num1Length >= 0 ? num1.charAt(num1Length) - '0' : 0)
        + (num2Length >= 0 ? num2.charAt(num2Length) - '0' : 0) + move;
      sb.append(temp % 10);
      move = temp / 10;
      num1Length--;
      num2Length--;
    }
    return sb.reverse().toString();
  }

  public String addStrings2(String num1, String num2) {
    if (num1.startsWith("0") && num2.startsWith("0")) {
      return "0";
    }
    StringBuilder sb = new StringBuilder();
    char[] charsA = num1.toCharArray();
    char[] charsB = num2.toCharArray();
    boolean flag = false;
    int lengthA = num1.length();
    int lengthB = num2.length();

    while (lengthA > 0 || lengthB > 0) {
      int temp = 0;
      if (lengthA > 0) {
        temp += (charsA[lengthA - 1] - '0');
      }
      if (lengthB > 0) {
        temp += (charsB[lengthB - 1] - '0');
      }
      if (flag) {
        temp++;
        flag = false;
      }
      if (temp >= 10) {
        flag = true;
      }
      sb.insert(0, temp % 10);
      lengthA--;
      lengthB--;
    }
    if (flag) {
      sb.insert(0, "1");
    }
    return sb.toString().charAt(0) == '0' ? sb.insert(0, "1").toString() : sb.toString();
  }

  public String addBinary(String a, String b) {
    if (a.startsWith("0") && b.startsWith("0")) {
      return "0";
    }
    StringBuilder sb = new StringBuilder();
    char[] charsA = a.toCharArray();
    char[] charsB = b.toCharArray();
    boolean flag = false;
    int lengthA = a.length();
    int lengthB = b.length();

    while (lengthA > 0 || lengthB > 0) {
      int temp = 0;
      if (lengthA > 0) {
        temp += (charsA[lengthA - 1] - '0');
      }
      if (lengthB > 0) {
        temp += (charsB[lengthB - 1] - '0');
      }
      if (flag) {
        temp++;
        flag = false;
      }
      if (temp >= 2) {
        flag = true;
      }
      sb.insert(0, temp % 2);
      lengthA--;
      lengthB--;
    }
    if (flag) {
      sb.insert(0, "1");
    }
    return sb.toString().charAt(0) == '0' ? sb.insert(0, "1").toString() : sb.toString();
  }


}
