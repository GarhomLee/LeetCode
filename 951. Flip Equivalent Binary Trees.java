https://leetcode.com/problems/flip-equivalent-binary-trees/

idea: DFS
time complexity: O(n)
space complexity: O(h)

class Solution {
    private boolean dfs(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        
        return node1.val == node2.val && 
            ((dfs(node1.left, node2.left) && dfs(node1.right, node2.right))
             || (dfs(node1.left, node2.right) && dfs(node1.right, node2.left))
             );
    }
    
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return dfs(root1, root2);
    }
}