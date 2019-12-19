https://leetcode.com/problems/validate-binary-search-tree/

// 解法一：Recursion + 遍历寻找root的inorder traversal的前一个和后一个节点值。
// 犯错点：1.前一个节点值的默认值和后一个节点值的默认值不能设为Integer的最大最小值，因为如果node.val恰好为Integer的最大最小值
//         的时候就会出错。同时，也不能设为Integer的最大最小值+1或-1，因为对于int来说就是数据溢出。
//         因此，单独设变量preVal和nextVal分别为Long的最小值和最大值。

class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;
        //TreeNode pre = new TreeNode(Integer.MIN_VALUE), next = new TreeNode(Integer.MAX_VALUE);  // {Mistake 1: this will cause a problem when the node.val is exactly MIN_VALUE or MAX_VALUE}
        //long preVal = Integer.MIN_VALUE - 1, nextVal = Integer.MAX_VALUE + 1;  // {Mistake 1: this will cause overflow}
        TreeNode pre = null, next = null;  // {Correction 1}
        long preVal = Long.MIN_VALUE, nextVal = Long.MAX_VALUE;  // {Correction 1}
        
        if (root.left != null) {
            pre = root.left;
            while (pre.right != null) pre = pre.right;
            preVal = pre.val;
        }
        if (root.right != null) {
            next = root.right;
            while (next.left != null) next = next.left;
            nextVal = next.val;
        }
        return isValidBST(root.left) && isValidBST(root.right) && preVal < root.val && root.val < nextVal;
    }
}

// 解法二：Recursion，比较root和lowerLimit以及upperLimit。
//        根据BST性质，对于树的每一个node，都符合lowerLimit < node.val < upperLimit，且左子树符合lowerLimit < node.left.val < node.val，
//        右子树符合node.val < node.right.val < upperLimit

class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }
    private boolean isValid(TreeNode root, Integer lowerLimit, Integer upperLimit) {
        if (root == null) return true;
        if (lowerLimit != null && root.val <= lowerLimit) return false;
        if (upperLimit != null && root.val >= upperLimit) return false;
        
        return isValid(root.left, lowerLimit, root.val) && isValid(root.right, root.val, upperLimit);
    }
}

// 另一种DFS：递归函数得到左右边界，用来更新全局变量isBST

class Solution {
    boolean isBST = true;
    
    private long[] dfs(TreeNode node) {
        if (node == null) {
            return new long[]{Long.MAX_VALUE, Long.MIN_VALUE};
        }
        
        long[] left = dfs(node.left), right = dfs(node.right);
        if (node.val <= left[1] || node.val >= right[0]) {
            isBST = false; 
        }
        return new long[]{Math.min(left[0], node.val), Math.max(right[1], node.val)};
    }
    
    public boolean isValidBST(TreeNode root) {
        dfs(root);
        
        return isBST;
    }
}


// 解法三：Iteration with Stack，利用inorder traversal的性质
//        维护一个TreeNode pre，记录inorder traversal当前节点的前一个节点。
//        根据BST的性质，在inorder traversal时能转化成sorted array。所以，如果出现pre.val >= curr.val，
//        说明不符合BST性质，返回false。
// 犯错点：1.BST的性质规定了不能有重复的值，所以如果pre.val和curr.val相等，也应该返回false

class Solution {
    public boolean isValidBST(TreeNode root) {
        //if (root == null) return true;  // unnecessary corner case
        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root, pre = null;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            //if (pre != null && pre.val > curr.val) return false;  // {Mistake 1: there should be no duplicates}
            if (pre != null && pre.val >= curr.val) return false;  // {Correction 1}
            pre = curr;
            curr = curr.right;
        }
        
        return true;
    }
}


// 解法四：Morris traversal，相当于解法三的优化

class Solution {
    public boolean isValidBST(TreeNode root) {
        TreeNode pre = null, curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode temp = curr.left;
                while (temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }
                
                if (temp.right != curr) {
                    temp.right = curr;
                    curr = curr.left;
                } else {
                    temp.right = null;
                    if (pre != null && pre.val >= curr.val) {
                        return false;
                    }

                    pre = curr;
                    curr = curr.right;
                }
            } else {
                if (pre != null && pre.val >= curr.val) {
                    return false;
                }
                
                pre = curr;
                curr = curr.right;
            }
        }
        
        return true;
    }
}