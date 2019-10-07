https://leetcode.com/problems/inorder-successor-in-bst/

// 思路：Iteration with Stack (Inorder Traversal)，套用模版
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            
            curr = stack.pop();
            if (curr.val > p.val) {
                return curr;
            }
            curr = curr.right;
        }
        
        return null;
    }
}