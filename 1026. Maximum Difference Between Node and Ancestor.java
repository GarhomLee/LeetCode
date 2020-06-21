https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/

idea: DFS
time complexity: O(n)
space complexity: O(h)

class Solution {
    int res = 0;
    
    private void dfs(TreeNode node, int min, int max) {
        if (node == null) {
            return;
        }
        
        int diff = Math.max(Math.abs(max - node.val), Math.abs(min - node.val));
        res = Math.max(res, diff);
        min = Math.min(min, node.val);
        max = Math.max(max, node.val);
        
        dfs(node.left, min, max);
        dfs(node.right, min, max);
    }
    
    public int maxAncestorDiff(TreeNode root) {
        dfs(root, root.val, root.val);
        return res;
    }
}