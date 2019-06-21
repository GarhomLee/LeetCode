https://leetcode.com/problems/binary-tree-level-order-traversal-ii/

// 102.Binary Tree Level Order Traversal的follow-up，将每层的结果从后往前加入最终结果列表。

// 解法一：Recursion
//        类似102.Binary Tree Level Order Traversal的Recursion写法，只需更改加入res的位置，从level变为res.size() - 1 - level。
// 犯错点：1.向res加入元素时，加入的位置是res.size() - 1 - level，因为level是0-based。代入最大level == res.size() - 1验算即可。

class Solution {
    List<List<Integer>> res = new ArrayList();
    
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        levelorder(root, 0);
        return res;
    }
    
    private void levelorder(TreeNode root, int level) {
        if (root == null) return;
        if (level >= res.size()) {
            res.add(0, new ArrayList<Integer>());
        }
        //res.get(res.size() - level).add(root.val);   // {Mistake 1: level is 0-based}
        res.get(res.size() - 1 - level).add(root.val);   // {Correction 1}
        levelorder(root.left, level + 1);
        levelorder(root.right, level + 1);
    }
}


// 解法二：Iteration
//        类似102.Binary Tree Level Order Traversal的Iteration写法，只需更改加入res的位置，从末尾加入变成头部0的位置加入。

class Solution {
    List<List<Integer>> res = new ArrayList();
    
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

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
            res.add(0, list);
        }
        
        return res;
    }
}