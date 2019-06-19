https://leetcode.com/problems/maximum-depth-of-binary-tree/

// 写法一：Recursion
// 时间复杂度：worst case O(n)
// 空间复杂度：worst case O(n)

class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}


写法二：Iteration using Queue