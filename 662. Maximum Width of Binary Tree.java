https://leetcode.com/problems/maximum-width-of-binary-tree/

idea: BFS
    Label each node as its position in a complete binary tree, and get the width in 
    each level via BFS.
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        int maxWidth = 0;
        root.val = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int leftBound = queue.peek().val;
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                maxWidth = Math.max(maxWidth, curr.val - leftBound + 1);
                if (curr.left != null) {
                    curr.left.val = 2*curr.val;
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    curr.right.val = 2*curr.val+1;
                    queue.offer(curr.right);
                }
            }
        }
        
        return maxWidth;
    }
}