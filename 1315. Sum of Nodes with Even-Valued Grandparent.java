https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/

思路：Recursion (postorder traversal?)
        递归函数定义：int dfs(TreeNode curr, TreeNode parent, TreeNode grandparent)表示求得
            以curr为根，且已知其parent和grandparent的树的符合题意的值总和。
        终止条件：curr == null，返回0
        递归过程：先递归求解curr.left和curr.right的结果，加和，然后根据当前的grandparent决定需不需
            要加上当前值curr.val。
时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    private int dfs(TreeNode curr, TreeNode parent, TreeNode grandparent) {
        if (curr == null) {
            return 0;
        }
        
        int value = grandparent != null && grandparent.val % 2 == 0 ? curr.val : 0;
        return value + dfs(curr.left, curr, parent) + dfs(curr.right, curr, parent);
    }
    
    public int sumEvenGrandparent(TreeNode root) {
        return dfs(root, null, null);
    }
}