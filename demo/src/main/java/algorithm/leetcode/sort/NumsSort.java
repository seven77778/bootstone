package algorithm.leetcode.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lsh
 * @date 2023/6/12 12:00
 * <p>
 * 912.
 * 给你一个整数数组 nums，请你将该数组升序排列。
 * <p>
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * <p>
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 */
public class NumsSort {


    @Test
    public void test() {
        int[] nums = new int[]{5, 2, 3, 1};
        System.out.println(Arrays.toString(sortArray5(nums)));
        int[] nums2 = new int[]{5, 1, 1, 2, 0, 0};
        System.out.println(Arrays.toString(sortArray5(nums2)));

        int[] nums3 = new int[]{-1, 2, -8, -10};
        System.out.println(Arrays.toString(sortArray5(nums3)));

    }


    //快速排序
    public int[] sortArray5(int[] nums) {
        quickSort(nums, 0, nums.length-1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        int pIndex = partition(nums, left, right);
        quickSort(nums, left, pIndex - 1);
        quickSort(nums, pIndex + 1, right);
    }

    private static final Random RANDOM = new Random();

    private int partition(int[] nums, int left, int right) {
        int randomIndex = RANDOM.nextInt(right - left + 1) + left;
        swap(nums, left, randomIndex);

        // 基准值
        int pivot = nums[left];
        int lt = left;
        // 循环不变量：
        // all in [left + 1, lt] < pivot
        // all in [lt + 1, i) >= pivot
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                lt++;
                swap(nums, i, lt);
            }
        }
        swap(nums, left, lt);
        return lt;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }



    //插入排序 -- 超时
    // 循环不变量：将 nums[i] 插入到区间 [0, i) 使之成为有序数组
    //关键是0-i之间先排序
    public int[] sortArray4(int[] nums) {
        for (int i = 1; i < nums.length ; i++) {
            int temp = nums[i];
            int j = i;
            while (j > 0 && j < nums.length && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temp;
        }
        return nums;
    }

    //选择排序 -- 超时
    //思路：第一次循环，在0-1中排好，第二次在1-2中排好
    public int[] sortArray3(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                int temp;
                temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
            }
        }
        return nums;
    }

    //写一个快速排序，会超时，这个是冒泡排序吧
    public int[] sortArray2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int temp = 0;
                if (nums[j] < nums[i]) {
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }


    //能通过，但是没意思啊
    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

}
