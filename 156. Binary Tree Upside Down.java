https://leetcode.com/problems/binary-tree-upside-down/

// 思路：DFS (Recursion)，关键在于理解题意。
//         根据给的例子，作为leaf node或者为null的当前节点的右子节点root.right翻转后会作为当前节点的左子节点
//         root.left的左子节点，当前节点root会作为root.left的右子节点，而翻转前的root.left在翻转后则会变成
//         root.right和root的父节点。递归完成这个过程即可。
//         注意：翻转后，root的左右子节点要设为null，避免指针的错误索引。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        return dfs(root);
    }
    
    private TreeNode dfs(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        
        TreeNode res = dfs(root.left);
        root.left.left = root.right;  // reassign the left child of root.left
        root.left.right = root;  // reassign the right child of root.left
        root.left = null;  // set root.left to null to avoid memory leak
        root.right = null;  // set root.right to null to avoid memory leak
        
        return res;
    }
}