https://leetcode.com/problems/insert-into-a-binary-search-tree/

// 解法一：Recursion，模版
// 时间复杂度：O(log n)
// 空间复杂度：O(log n)

class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        return insert(root, val);  // just to change a shorter name
    }
    
    private TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        
        return root;
    } 
}


// 解法二：Iteration with binary search in BST
//         维护pre节点，作为当curr==null找到插入位置时的parent node。
// 时间复杂度：O(log n)
// 空间复杂度：O(1)

class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        
        TreeNode curr = root, pre = null;
        while (curr != null) {
            pre = curr;
            if (val > curr.val) {
                curr = curr.right;
                if (curr == null) {
                    pre.right = new TreeNode(val);
                }
            } else {
                curr = curr.left;
                if (curr == null) {
                    pre.left = new TreeNode(val);
                }
            }
        }
        
        return root;
    } 
}