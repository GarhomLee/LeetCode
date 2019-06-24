https://leetcode.com/problems/binary-tree-maximum-path-sum/

// 思路：DFS，利用helper method求以当前root为根的树的某一条path的最大值，这个值至少为root.val。只取一条path是因为有左右子树，可以走两边，只取其中一边。
//      递归求取以左子树根root.left和右子树根root.right开始的path最大值，分别记为left和right。要注意的是，如果left和right都不能出现小于0，否则
//      不管是root.val + left还是root.val + right都会比root.val本身小，这样没有意义，所以left和right至少为0.
// 注意：helper method只求了一条path的最大值，题目要求的是从root开始的两条path的总和最大值，所以将这个求取过程放在helper method中间，利用中间结果
//      left和right得到root.val + left + right来更新maxSum，但本身这个过程对helper method的返回值没有影响。

class Solution {
    int maxSum = Integer.MIN_VALUE;  // max sum might be less than 0, so it should not be initialized as 0
        
    public int maxPathSum(TreeNode root) {
        sum(root);
        return maxSum;
    }
    
    /* find the path with max sum from current root to either left or right child tree */
    private int sum(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(sum(root.left), 0);
        int right = Math.max(sum(root.right), 0);
        
        maxSum = Math.max(maxSum, root.val + left + right);  // this line is for getting the final result of this question, not for this particular function

        return root.val + Math.max(left, right);
    }
}