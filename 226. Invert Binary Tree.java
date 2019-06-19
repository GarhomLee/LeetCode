https://leetcode.com/problems/invert-binary-tree/

// 解法一：Recursion
//        函数的含义是得到翻转后的以root的根的树。因此，root的左子树是翻转后的右子树，右子树是翻转后的左子树。
// 时间复杂度：worst case O(n)
// 空间复杂度：worst case O(n)

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left, right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }
}


// 解法二：Iteration with Queue
//        利用level order traversal模版，先将左右子树翻转，然后再把左右子树放入Queue中。
// 时间复杂度：worst case O(n)
// 空间复杂度：worst case O(n)
// 犯错点：1. root==null作为一般性的corner case，需要讨论

class Solution {
    public TreeNode invertTree(TreeNode root) {
        // {Mistake 1}
        /* corner case */
        if (root == null) return null;  // {Correction 1}
        
        Deque<TreeNode> deque = new ArrayDeque();
        deque.offer(root);
        while (!deque.isEmpty()) {
            TreeNode curr = deque.poll();
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
            if (curr.left != null) {
                deque.offer(curr.left);
            }
            if (curr.right != null) {
                deque.offer(curr.right);
            }
        }
        return root;
    }
}


解法三：Iteration with Postorder Traversal（可行吗？）