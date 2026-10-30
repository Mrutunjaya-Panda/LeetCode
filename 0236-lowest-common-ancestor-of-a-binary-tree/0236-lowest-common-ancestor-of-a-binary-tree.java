/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    //recursively using dfs we can solve this question.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;

        if(root == p || root == q) return root;

        //else go left and try to find p and q.
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        if(left == null && right == null){
            //we couldn't find p or q in any of the sides.
            return null;
        }
        if(left == null){
            //we were able to find in right.
            return right;
        }
        if(right == null){
            //we were able to find in left.
            return left;
        }

        //if left != null && right != null, both are in opposite side.
        return root;
    }
}

// Time complexity: O(N)
// Space complexity: O(H) --> Recu
// rsive Stack Space ( Ignorable )