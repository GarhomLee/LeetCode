https://leetcode.com/problems/search-in-a-binary-search-tree/

// 思路：Recursion (DFS)，类似preorder traversal
// 时间复杂度：O(n)
// 空间复杂度：O(depth) or O(n)

class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        return dfs(root, val);
    }
    
    private TreeNode dfs(TreeNode node, int target) {
        if (node == null) {
            return null;
        }
        
        if (node.val != target) {
            return node.val > target ? dfs(node.left, target) : dfs(node.right, target);
        }
        
        return node;
    }
}


解法二：Iteration with Stack


解法三：Morris Traversal