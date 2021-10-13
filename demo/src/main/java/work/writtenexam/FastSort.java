package work.writtenexam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class FastSort {
    /**
     * 快速排序
     */

    public static void main(String[] args) {
        int[] a = new int[]{5,4,3,2,1};
        sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        applicationContext.getBean(FastSort.class);
    }

    public static void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int base = arr[start];
        int before = start;
        int after = end;

        while (before < after) {
            //找到比base小的数字
            while (before < after && arr[after] > base) {
                after--;
            }

            //当然了并不是所有高位数字都比低位大，如果比低位要小，则这个数字要覆盖低位的数字
            arr[before] = arr[after];

            while (before < after && arr[before] < base) {
                before++;
            }
            arr[after] = arr[before];
        }
        arr[before] = base;
        sort(arr, start, before);

        sort(arr, before + 1, end);

    }
}
