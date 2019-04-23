https://leetcode.com/problems/binary-tree-inorder-traversal/

// 经典的树的遍历题。

// 解法一：recursion

class Solution {
    List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return list;
    }
    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        list.add(node.val);
        inorder(node.right);
    }
}

// 解法二：iteration using Stack

class Solution {
    List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }
}