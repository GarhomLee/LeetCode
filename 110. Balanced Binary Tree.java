https://leetcode.com/problems/balanced-binary-tree/

// 思路：明显的Recursion。Iteration写起来比较麻烦。
//      关键是了解height-balanced binary tree的性质：左右子树都为balanced binary tree，且左右子树的最大高度相差不超过1。

class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(height(root.left) - height(root.right)) <= 1;
    }
    /** get the depth (maximum height) of a tree */
    private int height(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }
}