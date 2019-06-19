https://leetcode.com/problems/binary-tree-paths/

// 思路：DFS+Backtracking
//      goal:到达leaf node，左右节点均为null
//      choices:左节点和右节点
//      constraints:子节点不能为null
// 注意：在每个choice结束后，StringBuilder都要完全重置为初始状态，所以可以用一个temp来保存初始状态。

class Solution {
    List<String> res = new ArrayList();
    
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return res;
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return res;
    }
    
    private void dfs(TreeNode root, StringBuilder sb) {
        sb.append(root.val);
        
        /* goal */
        if (root.left == null && root.right == null) {
            res.add(sb.toString());
            return;
        }
        
        StringBuilder temp = new StringBuilder(sb);
        /* choices */
        if (root.left != null) {  // constraint
            sb.append("->");
            dfs(root.left, sb);
            sb = temp;  // reset to initial state
        }
        if (root.right != null) {  // constraint
            sb.append("->");
            dfs(root.right, sb);
            sb = temp;  // reset to initial state
        }      
        
    }
}