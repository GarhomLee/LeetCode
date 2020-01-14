https://leetcode.com/problems/print-binary-tree/solution/

思路：DFS
        先用第一个递归函数求出整个树的深度height，来确定结果res的大小，rowLen为height，colLen为2^height - 1。
        再用第二个递归函数填充res。
        递归函数定义：void dfs(TreeNode node, int level, int low, int high, List<List<String>> res)，
            表示在res中填充在整个树中深度为level，以node为根，范围为[low:high]的子树的值。
        终止条件：node == null，直接返回跳出。
        递归过程：利用范围边界low和high得到node所在的index=mid=(low+high)/2，填入res.get(level)对应位置。
            然后，对左右子节点调用dfs()，范围相应改为[low:mid-1]和[mid+1:high]。
时间复杂度：O(height * (2^height - 1))
空间复杂度：O(height * (2^height - 1))

class Solution {
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        return 1 + Math.max(height(node.left), height(node.right));
    }
    
    private void dfs(TreeNode node, int level, int low, int high, List<List<String>> res) {
        if (node == null) return;
        
        int mid = low + (high - low) / 2;
        res.get(level).set(mid, String.valueOf(node.val));
        dfs(node.left, level + 1, low, mid - 1, res);
        dfs(node.right, level + 1, mid + 1, high, res);
    }
    
    public List<List<String>> printTree(TreeNode root) {
        int h = height(root);
        int len = (1 << h) - 1;
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                list.add("");
            }
            res.add(list);
        }
        
        dfs(root, 0, 0, len, res);
        
        return res;
    }
}