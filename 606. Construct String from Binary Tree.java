https://leetcode.com/problems/construct-string-from-binary-tree/

// 思路：Recursion (DFS)
//     递归函数定义：String dfs(TreeNode node)，返回以node为根的树转换成字符串后的结果
//     终止条件：node == null，返回空字符串""
//     递归过程：先将当前node的值加入StringBuilder sb，然后对左右子树递归调用dfs()。对于左右子树是否为空，
//         可能有4种情况，但可以总结为3种：
//         （1）右子树不为空，那么右子树一定会有"()"。同时，不管左子树是否为空，都不能删除左子树的"()"。
//         （2）右子树为空，但左子树不为空，那么只需要加入左子树的结果。
//         （3）右子树为空且左子树为空，左右子树的结果都可以忽略。
// 时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    private String dfs(TreeNode node) {
        if (node == null) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        String left = dfs(node.left);
        String right = dfs(node.right);
        // sb.append("(").append(dfs(node.left)).append(")");
        if (node.right != null) {
            sb.append("(").append(left).append(")");
            sb.append("(").append(right).append(")");
        } else if (node.left != null) {
            sb.append("(").append(left).append(")");
        }
        
        return sb.toString();
    }
    
    public String tree2str(TreeNode t) {
        return dfs(t);
    }
}