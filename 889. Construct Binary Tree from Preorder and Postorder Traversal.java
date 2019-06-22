https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/

// 另外两种构建树的方式：105. Construct Binary Tree from Preorder and Inorder Traversal，106. Construct Binary Tree from Inorder and Postorder Traversal

// 思路：类似105题和106题。由于只给了preorder数组和postorder数组，所以结果可能不唯一。
//      关键点在于先找到左子树的长度。
//      因为由性质可知，在preorder数组中左子树root一定在当前root的下一位，而在postorder数组中右子树root一定在当前root的前一位。
//      所以，先在postorder数组中找到左子树root的位置，那么左子树的size就可以确定，postorder数组中左右子树的范围也随之确定。
//      接下来再根据左子树size确定preorder数组中右子树root的位置，就可以递归构建整个树。

class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return build(pre, 0, post, 0, post.length - 1);
    }
    
    private TreeNode build(int[] pre, int preRootIndex, int[] post, int postStart, int postEnd) {
        if (postStart > postEnd) return null;
        if (postStart == postEnd) return new TreeNode(post[postStart]);
        /* get all info for building a tree */
        int rootVal = pre[preRootIndex];
        int leftPostRootIndex = find(post, pre[preRootIndex + 1]);  // left tree size is then determined
        int leftPreRootIndex = preRootIndex + 1;  // the index of the root of left child tree in preorder array
        int rightPreRootIndex = preRootIndex + 1 + (leftPostRootIndex - postStart + 1);  // the index of the root of right child tree in preorder array
        /* construct the tree */
        TreeNode root = new TreeNode(rootVal);
        root.left = build(pre, leftPreRootIndex, post, postStart, leftPostRootIndex);  // left child tree in postorder array is [postStart:leftPostRootIndex]
        root.right = build(pre, rightPreRootIndex, post, leftPostRootIndex + 1, postEnd - 1);  // right child tree in postorder array is [leftPostRootIndex + 1:postEnd - 1]
        return root;
    }
    
    private int find(int[] post, int target) {
        for (int i = 0; i < post.length; i++) {
            if (post[i] == target) return i;
        }
        return -1;
    }
}