https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/

idea: DFS (postorder traversal)
time complexity: O(n)
space complexity: O(n)

class Solution {
    int count = 0;  // increase when each time a query node is found
    
    public TreeNode helper(TreeNode curr, TreeNode p, TreeNode q) {
        if (curr == null) return null;
        
        TreeNode left = helper(curr.left, p, q);
        TreeNode right = helper(curr.right, p, q);
        
        if (curr != p && curr != q) {
             if (left == null) {
                return right;
            } else if (right == null) {
                return left;
            }
            
            return curr;
        }
        
        count++;
        return curr;
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = helper(root, p, q);
        return count == 2 ? res : null; // LCA is truly found only when 2 query nodes are found
    }
}