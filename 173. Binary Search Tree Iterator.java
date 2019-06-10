https://leetcode.com/problems/binary-search-tree-iterator/

// 思路：由于题目要求average O(1) time and uses O(h) memory，意味着不能简单的把tree用inorder traversal转换成一维数组，
//      这样的空间复杂度为O(n)，不符合题意。
//      应该利用Stack和iteration，这样有n次调用next()，而push()和pop()各自也调用了n次，所以均摊时间复杂度为O(1)。
//      同时，Stack的size不会超过tree最大高度，所以空间复杂度O(h)。
// 时间复杂度：next(): average O(1); hasNext(): O(1)
// 空间复杂度：O(h), h=height of tree

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BSTIterator {
    Stack<TreeNode> stack;
    TreeNode curr;
    
    public BSTIterator(TreeNode root) {
        stack = new Stack();
        curr = root;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        curr = node.right;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
        return node.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */