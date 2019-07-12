https://leetcode.com/problems/subtree-of-another-tree/

// 思路：Recursion
//      用了两层递归。
//      第一层递归函数定义：isSubtree(TreeNode s, TreeNode t)，判断t为根的树是否为s为根的树的子树
//      终止条件：1）如果s和t都为null，返回true
//               2）如果不符合1），但s或t有一个为null，那么另一个必为non-null，返回false
//      递归过程：要么s和t调用递归函数isEqual()发现它们是相同的树，要么t树是s.left的子树，要么t树是s.right的子树
//      第二层递归函数定义：isEqual(TreeNode s, TreeNode t)
//      终止条件：1）如果s和t都为null，返回true
//               2）如果不符合1），但s或t有一个为null，那么另一个必为non-null，返回false
//      递归过程：只有s.val == t.val，且s.left和t.left相等，且s.right和t.right，这三个条件同时满足，才能返回true。
// 犯错点：1.思路错误：用isSubtree(s.left, t.left) && isSubtree(s.right, t.right)代替
//          isEqual(s.left, t.left) && isEqual(s.right, t.right)，不符合判断子树是否相同的要求。

class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        //return (s.val == t.val && isSubtree(s.left, t.left) && isSubtree(s.right, t.right)) || isSubtree(s.left, t) || isSubtree(s.right, t);  // {Mistake 1}
        return isEqual(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);  // {Correction 1}
    }
    
    private boolean isEqual(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return s.val == t.val && isEqual(s.left, t.left) && isEqual(s.right, t.right);
    }
}