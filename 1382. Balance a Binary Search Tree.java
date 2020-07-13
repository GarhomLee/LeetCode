https://leetcode.com/problems/balance-a-binary-search-tree/

idea: DFS (Inorder traversal)
time complexity: O(n)
space complexity: O(n)

class Solution {
    private void inorder(TreeNode node, List<TreeNode> list) {
        if (node == null) return;
        
        inorder(node.left, list);
        list.add(node);
        inorder(node.right, list);
    }
    
    private TreeNode build(List<TreeNode> list, int low, int high) {
        if (low > high) {
            return null;
        }
        
        int mid = low + (high - low) / 2;
        TreeNode node = list.get(mid);
        node.left = build(list, low, mid - 1);
        node.right = build(list, mid + 1, high);
        return node;
    }
    
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inorder(root, list);
        return build(list, 0, list.size() - 1);
    }
}