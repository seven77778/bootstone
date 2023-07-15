package algorithm.leetcode.my;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lsh
 * @date 2023/7/2 11:21
 */
public class Generate2 {

  @Test
  public void test() {
    System.out.println(getRow66(3).toString());
    System.out.println(getRow66(4).toString());
    System.out.println(getRow66(1).toString());
  }


  //从后往前加,实在是666啊
  public List<Integer> getRow66(int rowIndex) {
    List<Integer> res = new ArrayList<>(rowIndex);
    for (int i = 0; i <= rowIndex; ++i) {
      res.add(1);
      for (int j = i - 1; j >= 1; --j) {
        res.set(j, res.get(j) + res.get(j - 1));
      }
    }
    return res;
  }


  //fixme 有点东西的
  public List<Integer> getRow(int rowIndex) {
    List<Integer> ans = new ArrayList<>(rowIndex);
    for (int i = 0; i <= rowIndex; ++i) {
      ans.add(1);
      for (int j = i - 1; j > 0; --j) {
        ans.set(j, ans.get(j) + ans.get(j - 1));
      }
    }
    return ans;
  }

  //规定时间复杂度On

  public List<Integer> getRow6(int rowIndex) {
    List<Integer> first = new ArrayList();
    first.add(1);
    if (rowIndex == 0) {
      return first;
    }


    first.clear();

    first.add(1);

    first.add(rowIndex - 1);
    if (rowIndex % 2 == 0) {
      first.add(2 * rowIndex - 2);
      first.add(2 * rowIndex - 2);
    } else {
      first.add(2 * rowIndex - 4);
    }

    return first;


  }

  public List<Integer> getRow2(int rowIndex) {
    List<Integer> first = new ArrayList();
    first.add(1);
    if (rowIndex == 0) {
      return first;
    }
    rowIndex += 1;
    int[][] arr = new int[rowIndex + 1][rowIndex + 1];
    arr[1][1] = arr[2][1] = arr[2][2] = 1;
    List<Integer> res = null;
    for (int i = 2; i <= rowIndex; i++) {
      res = new ArrayList<>();
      for (int j = 1; j <= i; j++) {
        if (j == 1 || j == i) {
          arr[i][j] = 1;
        } else {
          arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
        }
        res.add(arr[i][j]);
      }
    }
    return res;
  }

}
