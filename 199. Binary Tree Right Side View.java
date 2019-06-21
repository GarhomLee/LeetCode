https://leetcode.com/problems/binary-tree-right-side-view/

// 解法一：Recursion + DFS
//        先走右子树，如果level >= res.size()，说明有新的结果，加入res。
// 时间复杂度：worst O(n)
// 空间复杂度：worst O(n)

class Solution {
    List<Integer> res = new ArrayList();
    
    public List<Integer> rightSideView(TreeNode root) {
        view(root, 0);
        return res;
    }
    
    private void view(TreeNode root, int level) {
        if (root == null) return;
        if (level >= res.size()) res.add(root.val);
        view(root.right, level + 1);
        view(root.left, level + 1);
    }
}


// 解法二：Iteration with Queue
//        用level order traversal模版，要维护一个level变量表示当前遍历的层。

class Solution {
    List<Integer> res = new ArrayList();
    
    public List<Integer> rightSideView(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque();
        if (root != null) deque.offer(root);
        int level = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size-- > 0) {
                TreeNode curr = deque.poll();
                if (level >= res.size()) res.add(curr.val);
                if (curr.right != null) deque.offer(curr.right);
                if (curr.left != null) deque.offer(curr.left);
            }
            level++;
        }
        
        return res;
    }
}