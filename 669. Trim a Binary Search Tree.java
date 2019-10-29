https://leetcode.com/problems/trim-a-binary-search-tree/

// 思路：Recursion (DFS)
//         递归函数定义：TreeNode dfs(TreeNode node, int lower, int upper)，表示求得位于[lower:upper]
//             范围内的子树，返回其根节点。
//         终止条件：node==null，返回null
//         递归过程：对于node，可能有3种情况：
//             1）如果node.val < lower，那么左子树一定不会有节点在[lower:upper]范围中，因此返回对右子树
//                 node.right进行dfs的结果。
//             2）如果node.val > upper，那么右子树一定不会有节点在[lower:upper]范围中，因此返回对左子树
//                 node.left进行dfs的结果。
//             3）如果node.val在[lower:upper]范围中，那么对左右子树分别进行dfs得到结果，并替换原来的node.left
//                 和node.right，然后返回node。
// 时间复杂度：O(n), n=num of nodes
// 空间复杂度：O(h), h=height of tree

class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        return dfs(root, L, R);
    }
    
    private TreeNode dfs(TreeNode node, int lower, int upper) {
        if (node == null) {
            return null;
        }
        
        if (node.val < lower) {
            return dfs(node.right, lower, upper);
        }
        if (node.val > upper) {
            return dfs(node.left, lower, upper);
        }
        
        node.left = dfs(node.left, lower, upper);
        node.right = dfs(node.right, lower, upper);
        
        return node;
    }
}