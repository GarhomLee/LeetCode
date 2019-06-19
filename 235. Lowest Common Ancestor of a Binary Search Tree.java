https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

// 写法一：Recursion
    //    题目给定BST，且p和q是不同的数，且必定存在于BST，所以只需要判断p，q和root的关系。
    //    1）p和q都比root小，那么LCA一定在左子树
    //    2）p和q都比root大，那么LCA一定在右子树
    //    3）其他情况（p和q分别在root两边，或q，p其中之一为root），那么LCA只能为root
// 时间复杂度：worst case O(n)
// 空间复杂度：worst case O(n)

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lca(root, p, q);  // use helper mehod for a shorter name
    }
    
    private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        /* corner case: the root is the LCA */
        if (p.val == root.val || q.val == root.val) return root;
        
        if (p.val < root.val && q.val < root.val) {  // both p and q are less than root, LCA must lie in the left child
            return lca(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {  // both p and q are greater than root, LCA must lie in the right child
            return lca(root.right, p, q);
        } 
        
        return root;  // p and q are separated by root, thus the root is LCA
    }
}


// 写法二：Iteration
//        Recursion的改写。
// 时间复杂度：worst case O(n)
// 空间复杂度：O(1)

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val == root.val || q.val == root.val) return root;
        
        while ((p.val < root.val && q.val < root.val) || (p.val > root.val && q.val > root.val)) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            }
        }
        
        return root;
    }
}