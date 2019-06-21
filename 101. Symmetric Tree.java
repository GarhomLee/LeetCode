https://leetcode.com/problems/symmetric-tree/

// 解法一：Recursion
//        可知symmetric tree的性质是除root外，在root左右两边的子节点相同，且左子树的左子树和右子树的右子树互为镜像，
//        左子树的右子树和右子树的左子树也互为镜像。
//        或者这么看：如果两个树互为镜像，那么root节点相同，左子树和对方右子树互为镜像。

class Solution {
    public boolean isSymmetric(TreeNode root) {
        /* 写法一 */
        if (root == null) return true;
        return isMirror(root.left, root.right);
        
        /* 写法二 */
        /*return isMirror(root, root);*/
    }
    
    private boolean isMirror(TreeNode root1, TreeNode root2) {
        return (root1 == null && root2 == null) || 
        (root1 != null && root2 != null && root1.val == root2.val && isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left));
    }
}


解法二：Iteration