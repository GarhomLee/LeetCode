https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/

idea: DFS (postorder traversal) + Info Cache
time complexity: O(n)
space complexity: O(n)

class Solution {
    int maxLen = 0;
    
    /* return lens arr such that lens = {decr len, incr len} starting from this node as the parent */
    public int[] helper(TreeNode node) {
        if (node == null) {
            return new int[2];
        }
        
        int[] currLens = {1, 1};    // currLens = {decr len, incr len}
        int[] leftLens = helper(node.left), rightLens = helper(node.right);
        if (node.left != null) {
            if (node.val == node.left.val + 1) {
                // decrease from node to node.left
                currLens[0] = Math.max(currLens[0], 1 + leftLens[0]);
            } else if (node.val == node.left.val - 1) {
                // increase from node to node.left
                currLens[1] = Math.max(currLens[1], 1 + leftLens[1]);
            }
        }
        if (node.right != null) {
            if (node.val == node.right.val + 1) {
                // decrease from node to node.right
                currLens[0] = Math.max(currLens[0], 1 + rightLens[0]);
            } else if (node.val == node.right.val - 1) {
                // increase from node to node.right
                currLens[1] = Math.max(currLens[1], 1 + rightLens[1]);
            }
        }
        
        maxLen = Math.max(maxLen, currLens[0] + currLens[1] - 1);
        return currLens;
    }
    
    public int longestConsecutive(TreeNode root) {
        helper(root);
        return maxLen;
    }
}