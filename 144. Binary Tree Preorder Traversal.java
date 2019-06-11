https://leetcode.com/problems/binary-tree-preorder-traversal/

解法一：recursion

class Solution {
    List<Integer> res = new ArrayList();
    
    public List<Integer> preorderTraversal(TreeNode root) {
        preorder(root);
        return res;
    }
    
    private void preorder(TreeNode node) {
        if (node == null) return;
        res.add(node.val);
        preorder(node.left);
        preorder(node.right);
    }
}


// 解法二：iteration with Stack
// 犯错点：1.Stack存的是TreeNode，不是Integer

class Solution {
    List<Integer> res = new ArrayList();
    
    public List<Integer> preorderTraversal(TreeNode root) {
        //Stack<Integer> stack = new Stack();  // {Mistake 1}
        Stack<TreeNode> stack = new Stack();  // {Correction 1}
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                res.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            curr = curr.right;
        }
        
        return res;
    }
}