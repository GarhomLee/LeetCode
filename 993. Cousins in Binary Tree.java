https://leetcode.com/problems/cousins-in-binary-tree/

// 思路：HashMap
//         维护两个HashMap：
//         1）depth存放和节点相关的level信息，key为节点的值，value为该节点的level
//         2）parent存放和节点的parent信息，key为节点的值，value为该节点parent node。
//         递归搜索x和y相关的level和parent，最后检查x和y是否level相同且parent node不相同。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    Map<Integer, Integer> depth = new HashMap<>();
    Map<Integer, TreeNode> parent = new HashMap<>();
    
    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, null, 0, x, y);
        return depth.get(x) == depth.get(y) && parent.get(x) != parent.get(y);
    }
    
    private void dfs(TreeNode node, TreeNode prnt, int level, int x, int y) {
        if (node == null) {
            return;
        }
        
        depth.put(node.val, level);
        if (prnt != null) {
            parent.put(node.val, prnt);
        }
        
        if (node.val == x || node.val == y) {
            return;
        }
        
        dfs(node.left, node, level + 1, x, y);
        dfs(node.right, node, level + 1, x, y);
    }
}