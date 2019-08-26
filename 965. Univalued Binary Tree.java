https://leetcode.com/problems/univalued-binary-tree/

// 思路：Tree Traversal模版，Recurion，Iteration with Stack，Morris Travseral。
//     这里用Morris Inorder Travseral。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public boolean isUnivalTree(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode pre = curr.left;
                while (pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }
                
                if (pre.right == curr) {
                    pre.right = null;
                    if (curr.val != root.val) {
                        return false;
                    }
                    curr = curr.right;
                } else if (pre.right == null) {
                    pre.right = curr;
                    curr = curr.left;
                }
                
            } else {
                if (curr.val != root.val) {
                    return false;
                }
                curr = curr.right;
            }
        }
        
        return true;
    }
}