https://leetcode.com/problems/find-leaves-of-binary-tree/

// 思路：DFS
//         巧妙转化为将所有节点根据height分组，那么就转化为dfs求height的问题。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    private int dfs(TreeNode node, List<List<Integer>> res) {
        if (node == null) {
            return -1;
        }
        
        int left = dfs(node.left, res);
        int right = dfs(node.right, res);
        int height = 1 + Math.max(left, right);
        if (res.size() == height) {
            res.add(new ArrayList<>());
        }
        res.get(height).add(node.val);
        return height;
    }
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
}