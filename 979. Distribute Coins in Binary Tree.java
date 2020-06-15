https://leetcode.com/problems/distribute-coins-in-binary-tree/

idea: DFS (postorder traversal). Referring to: https://www.youtube.com/watch?v=zQqku1AXVF8
time complexity: O(n)
space complexity: O(n)

class Solution {
    int res = 0;
    
    /** Return the excess coins to offer this node after balancing the whole tree that is rooted at this node.
     *  This can be positive (move out) or negative (move in).
     */
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftExcess = dfs(node.left);    // coins to offer node.left
        int rightExcess = dfs(node.right);  // coins to offer node.right
        
        res += Math.abs(leftExcess) + Math.abs(rightExcess);    // flow at this step to balance node.left and node.right
        
        int currExcess = node.val - 1 + leftExcess + rightExcess;   // excess after balancing node.left and node.right
        return currExcess;
    }
    
    public int distributeCoins(TreeNode root) {
        dfs(root);  // there should be no excess coins
        return res;
    }
}