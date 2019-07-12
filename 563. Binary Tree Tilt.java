https://leetcode.com/problems/binary-tree-tilt/

// 思路：Recursion(DFS) + 更新全局变量。递归函数结构和508. Most Frequent Subtree Sum，543. Diameter of Binary Tree相似。
//      递归函数定义：sum(TreeNode root)，返回以root为根节点的树的所有节点之和。
//      终止条件：root == null
//      递归过程：递归调用求出以root.left和root.right为跟节点的左右子树所有节点之和，然后和当前节点root.val相加。
//      在这个过程中，利用中间变量，更新全局变量tilt，表示经过以当前节点root为根节点的树的左子树所有节点的和与右子树所有节点的和的
//      绝对差值，即|sum(root.left) - sum(root.right)|。题目要求对于所有的node都求这个差值，然后加和。
//      最后得到的diameter即为问题所求。

class Solution {
    int tilt = 0;
    
    public int findTilt(TreeNode root) {
        sum(root);
        return tilt;
    }
    
    private int sum(TreeNode root) {
        if (root == null) return 0;
        int left = sum(root.left);
        int right = sum(root.right);
        tilt += Math.abs(left - right);
        return root.val + left + right;
    }
}