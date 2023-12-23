package algorithm.leetcode.my;

/**
 * @author lsh
 * @date 2023/8/13 18:50
 */

public class Distinct2 {
  private static final char[] A = "horse".toCharArray();
  private static final char[] B = "ros".toCharArray();
  private static final Integer[][] MIN = new Integer[A.length + 1][B.length + 1];
  private static int count = 0;

  public static int distinct(int i, int j, int distinct) {
    if (MIN[i][j] != null) {
      return MIN[i][j];
    }
    count++;
    if (i == A.length) {
      return distinct + B.length - j;
    } else if (j == B.length) {
      return distinct + A.length - i;
    } else if (A[i] == B[j]) {
      int dist = distinct(i + 1, j + 1, distinct);
      if (MIN[i][j] == null) {
        MIN[i][j] = dist;
      } else if (dist < MIN[i][j]) {
        MIN[i][j] = dist;
      }
      return dist;
    }
    int dist1 = distinct(i + 1, j, distinct + 1);
    int dist2 = distinct(i, j + 1, distinct + 1);
    int dist3 = distinct(i + 1, j + 1, distinct + 1);
    return Math.min(Math.min(dist1, dist2), dist3);
  }

  public static void main(String[] args) {
    System.out.println(distinct(0, 0, 0));
    System.out.println("递归总次数：" + count);
  }
}
