https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/

idea: DFS (preorder traversal)
time complexity: O(n)
space complexity: O(h)

class Solution {
    public TreeNode helper(TreeNode node, Set<TreeNode> set) {
        if (node == null) {
            return null;
        }
        if (set.contains(node)) {
            return node;
        }
        
        TreeNode left = helper(node.left, set), right = helper(node.right, set);
        if (left != null && right != null) {
            return node;
        }
        
        return left == null ? right : left;
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<TreeNode> set = new HashSet<>();
        for (TreeNode node : nodes) {
            set.add(node);
        }
        
        return helper(root, set);
    }
}