https://leetcode.com/problems/minimum-depth-of-binary-tree/

// 思路：Recursion，类似104. Maximum Depth of Binary Tree
//      如果左右子树都非null，那么取较小的min dephth再+1。
//      如果左子树或者右子树为null，那么取非null子树的min depth + 1.
//      如果左右子树都null，那么min depth为0。
// 时间复杂度：worst case O(n)
// 空间复杂度：worst case O(n)
// 犯错点：1.这题和104. Maximum Depth of Binary Tree不同，在于即使有一个子树为null，它的min depth还需要考虑另一个非null的子树。

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        // {Mistake 1: if the current node is not a left node, and either left or right child is null, its min depth should be 1 + depth of non-null child}
        if (root.left == null) return 1 + minDepth(root.right);  // {Correction 1}
        else if (root.right == null) return 1 + minDepth(root.left);

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}