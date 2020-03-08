https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/

Idea: Recursion (DFS) + postorder & preorder traversal
Time complexity: O(n)
Space complexity: O(n)

class Solution {
    final int MOD = 1_000_000_007;
    long maxProduct = 1;
    
    private int getSum(TreeNode node) {
        if (node == null) return 0;
        
        // postorder traversal
        node.val += getSum(node.left) + getSum(node.right);
        return node.val;
    }
    
    private void dfs(TreeNode node, int sum) {
        if (node == null) return;
        
        // preorder traversal
        maxProduct = Math.max(maxProduct, (long) node.val * (long) (sum - node.val));
        dfs(node.left, sum);
        dfs(node.right, sum);
    }
    
    public int maxProduct(TreeNode root) {
        getSum(root);
        
        dfs(root, root.val);
        
        return (int) (maxProduct % MOD);
    }
}