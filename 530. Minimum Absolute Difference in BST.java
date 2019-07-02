https://leetcode.com/problems/minimum-absolute-difference-in-bst/

// 解法一：Recursion
//        因为题目给的是BST，所以inorder traversal可以得到sorted array。
//        维护全局变量：min，最小差值（非负数）；pre：inorder traversal中当前节点的前一个节点。
//        套用inorder traversal的recursion模版，对于root则和pre比较更新min。
// 犯错点：1.差值是绝对差值

class Solution {
    int min = Integer.MAX_VALUE;
    TreeNode pre = null;
    
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return min;
    }
    
    private void inorder(TreeNode root) {
        if (root == null) return;
        
        inorder(root.left);
        if (pre != null) {
            //min = Math.min(min, pre.val - root.val);  // {Mistake 1}
            min = Math.min(min, Math.abs(pre.val - root.val));  // {Correction 1: absolute difference}
        }
        pre = root;
        inorder(root.right);
    }
}


// 解法二：Iteration with Stack，模版

class Solution {
    public int getMinimumDifference(TreeNode root) {
        int min = Integer.MAX_VALUE;
        TreeNode curr = root, pre = null;
        Stack<TreeNode> stack = new Stack();
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (pre != null) {
                min = Math.min(min, Math.abs(pre.val - curr.val));
            }
            pre = curr;
            curr = curr.right;
        }
        return min;
    }
}

// 解法三：Morris traversal

class Solution {
    public int getMinimumDifference(TreeNode root) {
        int min = Integer.MAX_VALUE;
        TreeNode curr = root, pre = null;
        while (curr != null) {
            if (curr.left != null) {
                /* find the predecessor of curr in inroder traversal */
                TreeNode temp = curr.left;
                while (temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }
                if (temp.right == null) {  // link not built
                    /* build the link */
                    temp.right = curr;
                    /* search left child */
                    curr = curr.left;  
                } else if (temp.right == curr) {  // link already built, which means left child has been visited
                    /* destroy the link */
                    temp.right = null;
                    /* visit curr */
                    if (pre != null) {
                        min = Math.min(min, Math.abs(pre.val - curr.val));
                    }
                    pre = curr;  // update pre
                    /* search right child */
                    curr = curr.right;
                }
            } else {  // no curr.left
                /* visit curr */
                if (pre != null) {
                    min = Math.min(min, Math.abs(pre.val - curr.val));
                }
                pre = curr;  // update pre
                /* search right child */
                curr = curr.right;
            }
        }
        return min;
    }
}