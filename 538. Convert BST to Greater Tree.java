https://leetcode.com/problems/convert-bst-to-greater-tree/

// 解法一：Recursion + DFS
//        【翻转的inorder traversal】，先遍历右子树，将全局变量sum更新，此时sum和所有右子树节点有关。
//        利用sum更新root.val，然后再更新sum。
//        然后遍历左子树。

class Solution {
    int sum = 0;  // global variable
    
    public TreeNode convertBST(TreeNode root) {
        convert(root);
        return root;
    }
    
    private void convert(TreeNode root) {
        if (root == null) return;
        
        convert(root.right);
        root.val += sum;
        sum = root.val;
        convert(root.left);
    }
}


解法二：Iteration with Stack


解法三：Morris Traversal