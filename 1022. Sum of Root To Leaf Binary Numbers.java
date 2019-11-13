https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/

// 思路：Recursion (DFS)
// 时间复杂度：O(n), n=num of nodes
// 空间复杂度：O(n*s), n=num of nodes, s=s.length
// 犯错点：1.终止情况错误：如果把sum更新放在null node，由于leaf node的两个子节点都是null，会导致sum被更新两次。
//             因此，应该将sum更新放在leaf node，而不是null node。

class Solution {
    int sum = 0;
    
    public int sumRootToLeaf(TreeNode root) {
        dfs(root, "");
        return sum;
    }
    
    private void dfs(TreeNode node, String s) {
        if (node == null) {
            //sum += Integer.parseInt(s, 2);  // {Mistake 1}
            return;
        }
        
        s += (node.val + "");
        if (node.left == null && node.right == null) {
            sum += Integer.parseInt(s, 2);
            return;
        }  // {Correction 1}
        
        dfs(node.left, s);
        dfs(node.right, s);
    }
}