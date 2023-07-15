package algorithm.leetcode.my;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/27 18:15
 *
 * 列表 arr 由在范围 [1, n] 中的所有整数组成，并按严格递增排序。请你对 arr 应用下述算法：
 *
 * 从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
 * 重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
 * 不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
 * 给你整数 n ，返回 arr 最后剩下的数字。
 *
 *
 * 输入：n = 9
 * 输出：6
 * 解释：
 * arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
 * arr = [2, 4, 6, 8]
 * arr = [2, 6]
 * arr = [6]
 *
 *输入：n = 1
 * 输出：1
 */
public class LastRemaining {

  @Test
  public void test(){
    System.out.println(lastRemaining(10));
  }

  public int lastRemaining(int n) {
    int[] arr = new int[n];
    for(int i=0;i<n;i++){
      arr[i]=i;
    }

    while (arr.length>1){

    }


    return 1;
  }
}
