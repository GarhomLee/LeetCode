https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/

// 思路：DFS，类似preorder traversal
//         在递归的过程中，不断更新全局变量maxLen，表示当前找到的longest consecutive sequence长度。
//         递归函数定义：void dfs(TreeNode curr, TreeNode pre, int len)，表示遍历从curr节点开始
//                 的树（带上父节点得到的信息）。
//         终止条件：curr==null，直接return。
//         递归过程：先对curr节点进行操作。根据curr.val和pre.val的关系，决定len是否需要重置为0，然后
//                 len++表示加上当前节点curr后的consecutive sequence长度，并更新maxLen。
//                 然后递归遍历curr.left和curr.right。因此是preorder traversal
// 时间复杂度：O(n), n=num of tree nodes
// 空间复杂度：O(h), h=height of tree

class Solution {
    int maxLen = 0;
    
    public int longestConsecutive(TreeNode root) {
        dfs(root, null, 0);
        return maxLen;
    }
    
    private void dfs(TreeNode curr, TreeNode pre, int len) {
        if (curr == null) {
            return;
        }
        
        if (pre != null && pre.val != curr.val - 1) {  // reset the path len when it is not consecutive
            len = 0;
        }
        len++;
        maxLen = Math.max(maxLen, len);
        
        dfs(curr.left, curr, len);
        dfs(curr.right, curr, len);
    }
}