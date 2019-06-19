https://leetcode.com/problems/binary-tree-level-order-traversal/

// 解法一：Iteration with Queue
//        tree level order traversal模版。

class Solution {
    List<List<Integer>> res = new ArrayList();
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return res;
        
        Deque<TreeNode> deque = new ArrayDeque();
        deque.offer(root);
        while(!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList();
            while (size-- > 0) {
                TreeNode curr = deque.poll();
                list.add(curr.val);
                if (curr.left != null) deque.offer(curr.left);
                if (curr.right != null) deque.offer(curr.right);
            }
            res.add(list);
        }
        
        return res;
    }
}


// 解法二：Recursion
//        重点在于利用变量level来得到当前处理的层。如果level >= res.size()，那么说明到了新一层，res需要再加入新的List。事实上大于号是不必须的。

class Solution {
    List<List<Integer>> res = new ArrayList();
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        levelorder(root, 0);
        return res;
    }
    
    private void levelorder(TreeNode root, int level) {
        if (root == null) return;
        if (level >= res.size()) res.add(new ArrayList<Integer>());
        res.get(level).add(root.val);
        levelorder(root.left, level + 1);
        levelorder(root.right, level + 1);
    }
}