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


解法二：Morris inorder travesal
时间复杂度：O(n)
空间复杂度：O(1)

class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode curr = root, pre = null;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode temp = curr.left;
                while (temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }
                
                if (temp.right == null) {
                    temp.right = curr;
                    curr = curr.left;
                } else {
                    temp.right = null;
                    if (pre != null) {
                        return curr;
                    }
                    if (curr.val == p.val) {
                        pre = curr;
                    }
                    curr = curr.right;
                }
            } else {
                if (pre != null) {
                    return curr;
                }
                if (curr.val == p.val) {
                    pre = curr;
                }
                curr = curr.right;
            }
        }
        
        return null;
    }
}