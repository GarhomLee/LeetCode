https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/

// 思路：Recursion
//      用了两个helper method。
//      第一个递归函数定义：lca(TreeNode root)，返回root树的最底部leaf node的最小公共祖先节点（lca）
//      终止条件：root == null，返回null
//      递归过程：首先要确定最底部leaf node的深度，根据左右子树的高度有三种情况：
//             1）左子树更深，那么最底部leaf node只能在左子树，对应的lca也只能在左子树，因此递归调用lca()
//             2）右子树更深，那么最底部leaf node只能在右子树，对应的lca也只能在右子树，因此递归调用lca()
//             3）左右子树一样深，说明最底部leaf node既在左子树也在右子树，根据lca的定义，就是当前根节点root，
//                因此返回root。
//      第二个递归函数定义：height(TreeNode root)，返回root树的最大高度
//      终止条件：root == null，返回0
//      递归过程：递归求解左右子树最大高度+1

class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return lca(root);
    }
    
    private TreeNode lca(TreeNode root) {
        if (root == null) return null;
        int leftHeight = height(root.left), rightHeight = height(root.right);
        if (leftHeight > rightHeight) return lca(root.left);
        if (leftHeight < rightHeight) return lca(root.right);
        return root;
    }
    
    private int height(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }
}