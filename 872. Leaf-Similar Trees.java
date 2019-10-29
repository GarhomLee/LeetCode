https://leetcode.com/problems/leaf-similar-trees/

// 思路：Recursion (DFS)，类似preorder traversal
// 时间复杂度：O(n1 + n2)
// 空间复杂度：O(n1 + n2)

class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        return list1.equals(list2);
    }

    private void dfs(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            res.add(node.val);
            return;
        }
        
        dfs(node.left, res);
        dfs(node.right, res);
    }
}