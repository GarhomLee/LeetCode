https://leetcode.com/problems/diameter-of-binary-tree/

// 思路：Recursion(DFS) + 更新全局变量。递归函数结构和508. Most Frequent Subtree Sum相似。
//      递归函数定义：path(TreeNode root)，得到从当前树的根节点root到当前树的最远叶节点的路径长度
//      终止条件：root == null
//      递归过程：分别递归求出从root.left到最远叶节点和从root.right到最远叶节点的路径长度，注意判断子节点为null
//              的情况，然后取较大值作为返回值。
//              在这个过程中，利用中间变量，更新全局变量diameter，表示经过当前根节点root的两个node的最长路径长度。
//              最后得到的diameter即为问题所求。

class Solution {
    int diameter = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        path(root);
        return diameter;
    }
    
    private int path(TreeNode root) {
        if (root == null) return 0;

        int left = root.left == null ? 0 : 1 + path(root.left);
        int right = root.right == null ? 0 : 1 + path(root.right);

        diameter = Math.max(diameter, left + right);

        return Math.max(left, right);
    }
}