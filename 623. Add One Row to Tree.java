https://leetcode.com/problems/add-one-row-to-tree/

idea: DFS (postorder traversal)
time complexity: O(n)
space complexity: O(h)

class Solution {
    public TreeNode helper(TreeNode node, int v, int d) {
        if (node == null) {
            return null;
        }
        if (d > 2) {
            node.left = helper(node.left, v, d - 1);
            node.right = helper(node.right, v, d - 1);
            return node;
        } 
        
        TreeNode newLeft = new TreeNode(v), newRight = new TreeNode(v);
        newLeft.left = node.left;
        node.left = newLeft;
        newRight.right = node.right;
        node.right = newRight;
        
        return node;
    }
    
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        
        return helper(root, v, d);
    }
}