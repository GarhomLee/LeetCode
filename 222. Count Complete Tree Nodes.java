https://leetcode.com/problems/count-complete-tree-nodes/

// 解法一：Recursion

class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}

// 解法二：Iteration，进行preorder traversal的同时统计node数量

class Solution {
    public int countNodes(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        int count = 0;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
                count++;
            }
            root = stack.pop();
            root = root.right;
        }
        return count;
    }
}