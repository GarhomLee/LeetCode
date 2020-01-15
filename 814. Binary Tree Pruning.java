https://leetcode.com/problems/binary-tree-pruning/

思路：DFS
时间复杂度：O(n)
空间复杂度：O(h), h=height of tree

class Solution {
    /* return true if this whole tree rooted at TreeNode node can be pruned */
    private boolean dfs(TreeNode node) {
        if (node == null) {
            return true;
        }
        
        boolean isLeftPruned = dfs(node.left), isRightPruned = dfs(node.right);
        if (isLeftPruned) {
            node.left = null;
        }
        if (isRightPruned) {
            node.right = null;
        }
        
        return node.val == 0 && isLeftPruned && isRightPruned;
    }
    
    public TreeNode pruneTree(TreeNode root) {
        return dfs(root) ? null : root;
    }
}