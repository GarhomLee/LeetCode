https://leetcode.com/problems/two-sum-bsts/

// 思路：DFS, preorder traversal
//         将tree1利用preorder traversal放入HashSet中，然后对tree2也进行preorder traversal，利用HashSet找到
//         和当前元素的加和为target的HashSet元素（来自tree1）。
// 时间复杂度：O(n1 + n2), n1=node num of tree1, n2=node num of tree2
// 空间复杂度：O(n1 + n2), n1=node num of tree1, n2=node num of tree2

class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        Set<Integer> set = new HashSet<>();
        dfs(root1, set);
        
        return preorder(root2, target, set);
    }
    
    private boolean preorder(TreeNode node, int target, Set<Integer> set) {
        if (node == null) {
            return false;
        }
        
        if (set.contains(target - node.val)){
            return true;
        }
        
        return preorder(node.left, target, set) || preorder(node.right, target, set);
    }
    
    private void dfs(TreeNode node, Set<Integer> set) {
        if (node == null) {
            return;
        }
        
        set.add(node.val);
        dfs(node.left, set);
        dfs(node.right, set);
    }
}