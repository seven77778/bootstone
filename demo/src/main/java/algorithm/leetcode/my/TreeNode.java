package algorithm.leetcode.my;


import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {

  int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }

    public static TreeNode getTree(Integer[] num){
        Queue<TreeNode> queue = new LinkedList<>();
        if(num.length==0){
            return null;
        }
        TreeNode root = new TreeNode(num[0]);
        queue.offer(root);
        for(int i = 1;i < num.length;i++){
            TreeNode node = queue.peek();
            if (i % 2 != 0){//左孩子
                if (num[i] != null){
                    node.left = new TreeNode(num[i]);
                    queue.offer(node.left);
                }
            }
            else{//右孩子
                if (num[i] != null ){
                    node.right = new TreeNode(num[i]);
                    queue.offer(node.right);
                }
                queue.poll();
            }
        }
        return root;
    }


}
