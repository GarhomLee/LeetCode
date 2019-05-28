https://leetcode.com/problems/kth-smallest-element-in-a-bst/

// 总体思路：由于是BST，进行inorder traversal后就会变成sorted array，找第k小就是找从左到右第k个元素。
//         用BST的inorder traversal的iteration模版，用Stack储存当前扫描到的元素，然后统计pop出来的元素个数作为sorted array从左往右的元素个数。
// 时间复杂度：O(h+k),h=the height of the tree
// 空间复杂度：O(h+k),h=the height of the tree

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            k--;
            if (k == 0) return curr.val;
            curr = curr.right;
        }
        return -1;
    }
}