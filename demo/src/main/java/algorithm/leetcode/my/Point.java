package algorithm.leetcode.my;

/**
 * @author lsh
 * @date 2023/6/30 10:29
 */
public class Point {
  private int x;
  private int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  private static void modifyPoint(Point p1, Point p2) {
//    Point tmpPoint = p1;
    p1 = p2;
//    p2 = tmpPoint;
    p1.setLocation(5, 5);
//    p2 = new Point(5, 5);
  }

  public static void main(String[] args) {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    modifyPoint(p1, p2);
    System.out.println("[" + p1.x + "," + p1.y + "],[" + p2.x + "," + p2.y + "]");
  }
}
