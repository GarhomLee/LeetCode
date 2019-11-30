https://leetcode.com/problems/longest-univalue-path/

思路：DFS

时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    int max = 0;
    
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int res = 0;
        int left = dfs(node.left), right = dfs(node.right);
        
        if (node.left != null && node.val == node.left.val) {
            left += 1;
        } else {
            left = 0;
        }
        
        if (node.right != null && node.val == node.right.val) {
            right += 1;
        } else {
            right = 0;
        }
        
        max = Math.max(left + right, max);
        return Math.max(left, right);
    }
    
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return max;
    }
}