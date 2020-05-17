https://leetcode.com/problems/count-good-nodes-in-binary-tree/

idea: DFS (postorder traversal)
time complexity: O(n)
space complexity: O(n)

class Solution {
    private int dfs(TreeNode node, int preMax) {
        if (node == null) {
            return 0;
        }
        
        int currMax = Math.max(node.val, preMax);
        int left = dfs(node.left, currMax);
        int right = dfs(node.right, currMax);
        
        return left + right + (currMax <= node.val ? 1 : 0);
    }
    
    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }
}