https://leetcode.com/problems/find-all-the-lonely-nodes/

idea: DFS
time complexity: O(n)
space complexity: O(h)

class Solution {
    private void dfs(TreeNode node, List<Integer> ret) {
        if (node == null) return;
        
        if (node.left != null && node.right != null) {
            dfs(node.left, ret);
            dfs(node.right, ret);
        } else if (node.left != null) {
            ret.add(node.left.val);
            dfs(node.left, ret);
        } else if (node.right != null) {
            ret.add(node.right.val);
            dfs(node.right, ret);
        } 
    }
    
    public List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        dfs(root, ret);
        return ret;
    }
}