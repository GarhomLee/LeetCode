https://leetcode.com/problems/find-bottom-left-tree-value/

// 解法一：Iteration with Queue
//        套用模版，每一层记录最左边元素（即Queue头部元素）。

class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        if (root != null) queue.offer(root);
        int res = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res = queue.peek().val;
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return res;
    }
}


// 解法二：优化代码，不用管哪一层，只需要每层从右到左依次加入Queue。最后一个TreeNode就是最下面一行的最左边TreeNode。

public int findLeftMostNode(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        root = queue.poll();
        if (root.right != null)
            queue.add(root.right);
        if (root.left != null)
            queue.add(root.left);
    }
    return root.val;
}