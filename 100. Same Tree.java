https://leetcode.com/problems/same-tree/

// 解法一：Recursion

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}


// 解法二：Iteration with Stacks
//        利用preorder traversal模版和两个Stack，先看val是否相等，再看tree的结构是否相同。

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        
        Stack<TreeNode> stack1 = new Stack(), stack2 = new Stack();
        TreeNode curr1 = p, curr2 = q;
        while ((curr1 != null && curr2 != null) || (!stack1.isEmpty() && !stack2.isEmpty())) {
            while (curr1 != null && curr2 != null) {
                if (curr1.val != curr2.val) return false;  // check value similarity

                stack1.push(curr1);
                curr1 = curr1.left;

                stack2.push(curr2);
                curr2 = curr2.left;
            }
            if ((curr1 == null && curr2 != null) || (curr1 != null && curr2 == null)) return false;  // check structural similarity
            curr1 = stack1.pop();
            curr1 = curr1.right;

            curr2 = stack2.pop();
            curr2 = curr2.right;
        }
        
        return true;
    }
}