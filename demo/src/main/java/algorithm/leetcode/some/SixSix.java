package algorithm.leetcode.some;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author lsh
 * @date 2023/6/16 15:45
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头
 * <p>
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * <p>
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 */
public class SixSix {

    @Test
    public void test() {
        int nums[] = new int[]{1, 2, 3};
        int nums2[] = new int[]{9, 9, 9};
        int nums3[] = new int[]{9};
        int nums4[] = new int[]{1};
        int nums5[] = new int[]{8, 9, 9, 9};
        int nums6[] = new int[]{9, 8, 9};

        System.out.println( Arrays.toString( plusOne2( nums5 ) ) );//9000
        System.out.println( Arrays.toString( plusOne2( nums ) ) );//124
        System.out.println( Arrays.toString( plusOne2( nums2 ) ) );
        System.out.println( Arrays.toString( plusOne2( nums3 ) ) );
        System.out.println( Arrays.toString( plusOne2( nums4 ) ) );

        System.out.println( Arrays.toString( plusOne2( nums6 ) ) );

    }

    public int[] plusOne2(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        if (digits[0] == 0) {
            digits = new int[digits.length + 1];
            digits[0]=1;
        }
        return digits;
    }

    //调试几次，也算写出来了--感觉太啰嗦了
    public int[] plusOne(int[] digits) {
        int temp = digits[digits.length - 1] == 9 ? 1 : 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] += temp;
            if (digits[i] == 10) {
                digits[i] = 0;
                temp = 1;
            } else {
                if (i == digits.length - 1) {
                    digits[i] += 1;
                }
                temp = 0;
            }
        }
        if (digits[0] == 0) {
            int num[] = new int[digits.length + 1];
            System.arraycopy( digits, 0, num, 1, digits.length - 1 );
            num[0] = 1;
            return num;
        }
        return digits;
    }
}
