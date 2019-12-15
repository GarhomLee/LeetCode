https://leetcode.com/problems/average-of-levels-in-binary-tree/

// 思路：BFS with Queue
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        // edge case
        if (root == null) {
            return res;
        }
        
        queue.offer(root); // initialize queue
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0; // sum of current level
            int count = size; // num of nodes at current level
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                sum += curr.val; // update sum
                if (curr.left != null) {
                    queue.offer(curr.left); // update queue
                }
                if (curr.right != null) {
                    queue.offer(curr.right); // update queue
                }
            }
            res.add(sum / count); // update res
        }
        
        return res;
    }
}