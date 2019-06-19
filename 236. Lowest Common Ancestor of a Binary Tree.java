https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

// 解法一：Recursion
//       递归函数的意义是找到以root为根的包含p和q的树的LCA。如果树同时不包含p和q，那么会返回null。
//       先判断当前root（root!=null）是不是和p或q相等，如果是，那么直接返回root。
//       递归查找左右子树的LCA。可知，如果p和q同时在左子树（或右子树），那么递归得到的另一边右子树（或左子树）的LCA就为null，
//       结果返回非null一边的LCA。如果两边都非null，那么说明p和q分别出现在左右子树，所以LCA为root，返回root。
// 时间复杂度：worst case O(n)
// 空间复杂度：worst case O(n)
// 犯错点：1. root==null作为一般性的termination condition，需要讨论

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lca(root, p, q);  // use helper mehod for a shorter name
    }
    
    private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        // {Mistake 1}
        /* corner case */
        if (root == null) return null;   //{Correction 1}
        /* corner case: the root is the LCA */
        if (p.val == root.val || q.val == root.val) return root;
                
        TreeNode leftLCA = lca(root.left, p, q);
        TreeNode rightLCA = lca(root.right, p, q);
        if (leftLCA == null) {
            return rightLCA;
        } else if (rightLCA == null) {
            return leftLCA;
        }
        return root;
    }
}


解法二：Iteration