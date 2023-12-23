package algorithm.leetcode.my.pizza;

/**
 * @author lsh
 * @date 2023/8/19 14:57
 */


public class Solution2 {
  int max = 0;
  int[] slices;
  int length;
  int pis;

  public int maxSizeSlices(int[] slices) {
    this.slices = slices;
    this.length = slices.length;
    this.pis = this.length / 3;
    maxSizeSlices(0, 0, 0, new boolean[length]);
    return max;
  }

  public void maxSizeSlices(int count, int start, int selected, boolean[] visitor) {
    if (count == pis) {
      if (selected > max) {
        max = selected;
      }
      return;
    }
    int pointer = findNext(start, visitor, 1);
    if (pointer == -1) {
      return;
    }
    for (int i = count; i < length; i++) {
      int left = findNext(pointer - 1, visitor, -1);
      int right = findNext(pointer + 1, visitor, 1);
      boolean[] v1 = copy(visitor);
      v1[pointer] = true;
      v1[left] = true;
      v1[right] = true;
      maxSizeSlices(count + 1, right + 1, selected + slices[pointer], v1);
    }
  }

  public boolean[] copy(boolean[] visitor) {
    boolean[] n = new boolean[visitor.length];
    for (int i = 0; i < visitor.length; i++) {
      n[i] = visitor[i];
    }
    return n;
  }


  public int findNext(int index, boolean[] visitor, int f) {
    for (int i = 0; i < length; i++) {
      if (index >= length) {
        index = index - length;
      } else if (index < 0) {
        index = length + index;
      }
      if (visitor[index] == false) {
        return index;
      }
      index += f;
    }
    return -1;
  }


  public static void main(String[] args) {
    int[] slices = new int[]{9, 8, 1, 7, 7, 9, 5, 10, 7, 9, 3, 8, 3, 4, 8};
    Solution2 so = new Solution2();
    int max = so.maxSizeSlices(slices);
    System.out.println(max);
  }



}
