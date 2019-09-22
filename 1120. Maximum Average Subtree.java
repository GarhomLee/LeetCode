https://leetcode.com/problems/maximum-average-subtree/

// 思路：Recursion，类似postorder traversal
//         维护全局变量average，表示当前搜索的树的平均值中的最大值。
//         递归函数定义：int[] dfs(TreeNode node)，表示得到以node为根的树的所有节点个数和节点值的总和。
//         终止条件：当node == null，返回int[]{0, 0}。
//         递归过程：先调用递归函数，得到左子树和右子树的节点个数和节点值的总和，从而得到当前树的节点个数和
//             节点值的总和，并以此更新average。
//             将当前树的节点个数和节点值的总和以int[]形式返回。
// 时间复杂度：O(n), n=num of nodes
// 空间复杂度：O(h), h=height of tree

class Solution {
    double average = Double.MIN_VALUE;
    
    public double maximumAverageSubtree(TreeNode root) {
        dfs(root);
        return average;
    }
    
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        
        int[] left = dfs(node.left), right = dfs(node.right);
        int sum = node.val + left[0] + right[0];
        int count = 1 + left[1] + right[1];
        average = Math.max(average, (double) sum / count);
        return new int[]{sum, count};
    }
}