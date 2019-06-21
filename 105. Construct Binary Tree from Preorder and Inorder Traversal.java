https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

// 思路：Recursion。题目条件规定了所有值都不相等。
//      根据性质可知，在inorder数组，根节点必然在中间位置，左右子树的所有节点分列跟节点两侧；在preorder数组，根节点的下一位
//      为左子树的根（if any），根节点+左子树大小后为右子树的根（if any）。
//      因此，只需要在preorder数组中确定根节点的值，在inorder数组中找到对应的index，即可确定左右子树的递归处理方向。然后，再
//      通过给定的inorder数组中当前树的处理范围[inStart:inEnd]，就可以确定左右子树的大小，然后在preorder数组找到左右子树的
//      根节点值，即可递归构建整个树。

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = build(preorder, 0, inorder, 0, inorder.length - 1);
        return root;
    }
    
    private TreeNode build(int[] preorder, int preRootIndex, int[] inorder, int inStart, int inEnd) {
        /* corner cases */
        if (inStart > inEnd) return null;
        if (inStart == inEnd) return new TreeNode(inorder[inStart]);
        
        /* get all info for building a tree */
        int rootVal = preorder[preRootIndex];  // get the root value by its index in preorder array
        int inRootIndex = find(inorder, rootVal);  // get the root index in inorder array
        int leftPreRootIndex = preRootIndex + 1;  // get the root of left child tree in inorder array
        int rightPreRootIndex = preRootIndex + 1 + inRootIndex - inStart;  // get the root of right child tree in inorder array
        /* build the tree by recursion */
        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, leftPreRootIndex, inorder, inStart, inRootIndex - 1);
        root.right = build(preorder, rightPreRootIndex, inorder, inRootIndex + 1, inEnd);
        return root;
    }
    /** find the index of the target in inorder array */
    private int find(int[] inorder, int target) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == target) return i;
        }
        return -1;
    }
}