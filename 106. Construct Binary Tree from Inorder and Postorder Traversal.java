https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

// 思路：Recursion，题目条件规定了所有值都不相等。
//      根据性质可知，在inorder数组，根节点必然在中间位置，左右子树的所有节点分列跟节点两侧；在postorder数组，根节点在最右，根节点
//      的前一位为右子树的根（if any），根节点位置-右子树大小后为左子树的根（if any）。
//      因此，只需要在postorder数组中确定根节点的值，在inorder数组中找到对应的index，即可确定左右子树的递归处理方向。然后，再
//      通过给定的inorder数组中当前树的处理范围[inStart:inEnd]，就可以确定左右子树的大小，然后在postorder数组找到左右子树的
//      根节点值，即可递归构建整个树。
// 犯错点：1.利用右子树长度求左子树根的index时，因为进行减操作，所以一定要带上括号

class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, 0, inorder.length - 1, postorder, postorder.length - 1);
    }
    
    private TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postRootIndex) {
        if (inStart > inEnd) return null;
        if (inStart == inEnd) return new TreeNode(inorder[inStart]);
        
        /* get all info for building a tree */
        int rootVal = postorder[postRootIndex];
        int inRootIndex = find(inorder, rootVal);
        //int leftPostRootIndex = postRootIndex - 1 - inEnd - inRootIndex;  // {Mistake 1: substraction must have parenthesis}
        int leftPostRootIndex = postRootIndex - 1 - (inEnd - inRootIndex);  // {Correction 1}
        int rightPostRootIndex = postRootIndex - 1;
        /* tree construction */
        TreeNode root = new TreeNode(rootVal);
        root.left = build(inorder, inStart, inRootIndex - 1, postorder, leftPostRootIndex);
        root.right = build(inorder, inRootIndex + 1, inEnd, postorder, rightPostRootIndex);
        return root;
    }
    
    private int find(int[] inorder, int target) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == target) return i;
        }
        return -1;
    }
}