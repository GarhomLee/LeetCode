https://leetcode.com/problems/deepest-leaves-sum/

// 思路：BFS
//         常规level order traversal，只需要保留最后一层的sum。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int deepestLeavesSum(TreeNode root) {
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            sum = 0;
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                sum += curr.val;
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
        
        return sum;
    }
}