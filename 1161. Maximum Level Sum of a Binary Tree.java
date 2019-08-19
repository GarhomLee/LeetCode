https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/

// 思路：BFS
//         套用树遍历的BFS模版，记录当前所在的层号，在每一层求所有节点的和，当出现更大的和时更新minLevel记录的层号。
//         最后返回minLevel。
// 时间复杂度：O(n)
// 空间复杂度：O(2^h), h=height of tree

class Solution {
    public int maxLevelSum(TreeNode root) {
        int level = 1, minLevel = 1, max = Integer.MIN_VALUE;
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            int val = 0;
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                val += curr.val;
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            if (val > max) {
                max = val;
                minLevel = level;
            }
            level++;
        }
        
        return minLevel;
    }
}