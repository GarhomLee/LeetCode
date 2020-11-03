https://leetcode.com/problems/find-nearest-right-node-in-binary-tree/

idea: BFS
time complexity: O(n)
space complexity: O(n)

class Solution {
    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Queue<TreeNode> nextQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode curr = queue.poll();
                if (curr == u) {
                    return queue.isEmpty() ? null : queue.poll();
                }
                
                if (curr.left != null) {
                    nextQueue.offer(curr.left);
                }
                if (curr.right != null) {
                    nextQueue.offer(curr.right);
                }
            }
            
            queue = nextQueue;
        }
        
        return null;
    }
}