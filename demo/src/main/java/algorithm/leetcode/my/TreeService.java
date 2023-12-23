package algorithm.leetcode.my;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/8/30 21:53
 */
public class TreeService {


  @Test
  public void test() {
//    System.out.println(countNodes(TreeNode.getTree(new Integer[]{})));
    System.out.println(countNodes(TreeNode.getTree(new Integer[]{1, 2, 3, 4, 5, 6})));

    System.out.println(countNodes(TreeNode.getTree(new Integer[]{1})));
  }

  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = 0;
    int right = 0;
    TreeNode leftNode = root.left;
    TreeNode rightNode = root.right;
    while (leftNode != null) {
      left++;
      leftNode = leftNode.left;
    }

    while (rightNode != null) {
      right++;
      rightNode = rightNode.right;
    }
    if (right == left) {
      return (2 << left) - 1;
    }
    int leftNum = countNodes(root.left);
    int rightNum = countNodes(root.right);

    return leftNum + rightNum + 1;
  }


  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = maxDepth(root.left) + 1;
    int right = maxDepth(root.right) + 1;
    return Math.max(left, right);
  }

  public int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    if (root.right == null && root.left != null) {
      return left + 1;
    }
    if (root.right != null && root.left == null) {
      return right + 1;
    }
    return Math.min(right, left) + 1;
  }


}
