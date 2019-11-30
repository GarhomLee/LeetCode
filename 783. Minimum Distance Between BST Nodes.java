https://leetcode.com/problems/minimum-distance-between-bst-nodes/

// 解法一：DFS (inorder traversal)
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    int min = Integer.MAX_VALUE;
    TreeNode pre = null;
    
    public int minDiffInBST(TreeNode root) {
        dfs(root);
        return min;
    }
    
    private void dfs(TreeNode node) {
        if (node == null) return;
        
        dfs(node.left);
        
        if (pre != null) {
            min = Math.min(min, Math.abs(pre.val - node.val));
        }
        pre = node;
        
        dfs(node.right);
    }
}


// 解法二：Morris traversal
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int minDiffInBST(TreeNode root) {
        TreeNode pre = null;
        int min = Integer.MAX_VALUE;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode temp = curr.left;
                while (temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }
                
                if (temp.right == null) {
                    temp.right = curr;
                    curr = curr.left;
                } else if (temp.right == curr) {
                    temp.right = null;
                    if (pre != null) {
                        min = Math.min(min, curr.val - pre.val);
                    }
                    pre = curr;
                    curr = curr.right;
                }
            } else {
                if (pre != null) {
                    min = Math.min(min, curr.val - pre.val);
                }
                pre = curr;
                curr = curr.right;
            }
        }
        
        return min;
    }
}