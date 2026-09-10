/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int cnt =0;
    public Pair<Integer,Integer> postOrder(TreeNode root){
        if(root == null) return new Pair<>(0,0);
        Pair<Integer,Integer> left  = postOrder(root.left);
        Pair<Integer,Integer> right  = postOrder(root.right);

        int nodeSum = left.getKey()+right.getKey()+root.val;
        int nodeCnt = left.getValue()+right.getValue()+1;

        if(root.val == (nodeSum)/nodeCnt){
            cnt++;
        }

        return new Pair<>(nodeSum,nodeCnt);
    }
    public int averageOfSubtree(TreeNode root) {
        postOrder(root);
        return cnt;
    }
}

//T.c:- O(N)
//S.c:- O(N)