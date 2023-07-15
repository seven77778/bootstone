package algorithm.leetcode.my;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lsh
 * @date 2023/7/2 10:05
 * <p>
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * <p>
 * 输入: numRows = 1
 * 输出: [[1]]
 */
public class Generate1 {

  @Test
  public void test() {


    System.out.println(generate3(1).toString());
    System.out.println(generate3(3).toString());
    System.out.println(generate3(4).toString());
    System.out.println(generate3(5).toString());

  }


  //arraylist也是基于数组，所以查询是O1，可以不引入新的数组 -- 效率还可以
  public List<List<Integer>> generate3(int numRows) {
    List<List<Integer>> result = new ArrayList<>(numRows);
    List<Integer> first = new ArrayList();
    first.add(1);
    result.add(first);
    if (numRows == 1) {
      return result;
    }
    for (int i = 2; i <= numRows; i++) {
      List<Integer> next = new ArrayList<>(i);
      for (int j = 1; j <= i; j++) {
        if (j == 1 || j == i) {
          next.add(1);
        } else {
          next.add(result.get(i - 2).get(j - 2) + result.get(i - 2).get(j-1));
        }
      }
      result.add(next);
    }
    return result;
  }

  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> first = new ArrayList();
    first.add(1);
    result.add(first);
    if (numRows == 1) {
      return result;
    }
    int[][] arr = new int[numRows + 1][numRows + 1];
    arr[1][1] = arr[2][1] = arr[2][2] = 1;
    for (int i = 2; i <= numRows; i++) {
      List<Integer> next = new ArrayList<>();
      for (int j = 1; j <= i; j++) {
        if (j == 1 || j == i) {
          arr[i][j] = 1;
        } else {
          arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
        }
        next.add(arr[i][j]);
      }
      result.add(next);
    }
    return result;
  }

  //ok
  public List<List<Integer>> generate2(int numRows) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> first = new ArrayList();
    first.add(1);
    result.add(first);
    if (numRows == 1) {
      return result;
    }

    List<Integer> second = new ArrayList();
    second.add(1);
    second.add(1);
    result.add(second);
    List<Integer> temp = second;
    for (int i = 3; i <= numRows; i++) {
      List<Integer> next = new ArrayList<>();
      next.add(1);
      for (int j = 0; j < temp.size(); j++) {
        if (j + 1 < temp.size()) {
          next.add(temp.get(j) + temp.get(j + 1));
        }
      }
      next.add(1);
      result.add(next);
      temp = next;
    }
    return result;
  }


}
