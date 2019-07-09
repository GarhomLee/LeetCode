https://leetcode.com/problems/sum-of-left-leaves/

// 解法一：Recursion
//       递归函数：sum(TreeNode node)，表示以node为根的数的所有left leaf nodes的和
//       终止条件：node == null，返回0
//       递归：对于node.right，一定要调用sum()；对于node.left，如果是left leaf，直接返回node.left.val和
//            sum(node.right)的和；否则，返回sum(node.left)和sum(node.right)的和。

class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return sum(root);
    }
    
    private int sum(TreeNode node) {
        if (node == null) return 0;
        return sum(node.right) + (isLeaf(node.left) ? node.left.val : sum(node.left));
    }
    
    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}


// 解法二：BFS Iteration with Queue
//        套用模版，只需要增加是否找到left leaf的判断，并更新res。

class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        int res = 0;
        Queue<TreeNode> queue = new LinkedList();
        if (root != null) queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                if (curr.left != null) {
                    queue.offer(curr.left);
                    /* update res if left leaf is found */
                    if (curr.left.left == null && curr.left.right == null) {
                        res += curr.left.val;
                    }
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
        
        return res;
    }
}