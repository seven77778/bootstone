package algorithm.leetcode.my;

/**
 * 莱温斯坦距离（动态规划）.
 */
public class Distinct {
  private static final char[] A = "m".toCharArray();
  public static final int A_LENGTH = A.length;
  private static final char[] B = "mitssdfcmuab".toCharArray();
  public static final int B_LENGTH = B.length;
  private static final Integer[][] MIN = new Integer[A_LENGTH + 1][B_LENGTH + 1];

  public static int distinct() {
    if (A_LENGTH == 0 || B_LENGTH == 0) {
      return A_LENGTH + B_LENGTH;
    }
// 初始化第一个节点
    MIN[0][0] = 0;
    for (int a = 0; a < A_LENGTH; a++) {
      for (int b = 0; b < B_LENGTH; b++) {
        Integer min = MIN[a][b];
        if (min == null) {
          continue;
        }
        if (A[a] == B[b]) {
          updateDist(a + 1, b + 1, min);
          continue;
        }
        updateDist(a + 1, b, min + 1);
        updateDist(a, b + 1, min + 1);
        updateDist(a + 1, b + 1, min + 1);
      }
    }
    output();
    return min(MIN[A_LENGTH - 1][B_LENGTH], MIN[A_LENGTH][B_LENGTH - 1], MIN[A_LENGTH][B_LENGTH]);
  }

  private static int min(Integer dist1, Integer dist2, Integer dist3) {
    dist1 = defaultIfNull(dist1);
    dist2 = defaultIfNull(dist2);
    dist3 = defaultIfNull(dist3);
    return Math.min(Math.min(dist1, dist2), dist3);
  }

  private static Integer defaultIfNull(Integer dist) {
    return dist == null ? Integer.MAX_VALUE : dist;
  }

  private static void output() {
    for (int i = 0; i <= A_LENGTH; i++) {
      for (int j = 0; j <= B_LENGTH; j++) {
        System.out.print(MIN[i][j] + " ");
      }
      System.out.println();
    }
  }

  private static void updateDist(int i, int j, int c) {
    if (MIN[i][j] == null) {
      MIN[i][j] = c;
    } else if (c < MIN[i][j]) {
      MIN[i][j] = c;
    }
  }

  public static void main(String[] args) {
    System.out.println(distinct());
    System.out.println(MIN);
  }
}
