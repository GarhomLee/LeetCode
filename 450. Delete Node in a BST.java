https://leetcode.com/problems/delete-node-in-a-bst/

// 思路：Recursion，总的来说就是用中序遍历的predecessor（或者successor）替换要被删除的节点，然后把
//      predecessor（或者successor）从树中删除。
//      递归函数定义：delete(TreeNode root, int key)，删除以root为根的树当中值为key的节点，
//             最后返回更新后的根root。
//      终止条件：root == null
//      递归过程：因为是BST，所以如果key!=root.val，那么就相应地更新左子树或右子树。
//             对于key==root.val，要删除当前节点root。根据子树个数，有三种可能：
//             1）没有右子树（左子树有or没有），那么直接返回左子树的根就是删除了当前节点的树的根
//             2）没有左子树（右子树有or没有），那么直接返回右子树的根就是删除了当前节点的树的根
//             3）既有左子树又有右子树，那么需要【找到其中序遍历的predecessor，用来替换当前节点root.val】，
//                然后转化为【删除以root.left为根的树当中的predecessor节点】。
//                巧妙之处在于，删除predecessor的过程依然可以调用当前的递归函数，而且属于左右子树都没有
//                的情况。

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        return delete(root, key);
    }
    
    private TreeNode delete(TreeNode root, int key) {
        if (root == null) return null;
        
        if (key < root.val) {
            root.left = delete(root.left, key);
        } else if (key > root.val) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            TreeNode predecessor = findPredecessor(root);
            root.left = delete(root.left, predecessor.val);
            root.val = predecessor.val;
        }
        
        return root;
    }
    
    private TreeNode findPredecessor(TreeNode node) {
        if (node == null || node.left == null) return null;
        TreeNode temp = node.left;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp;
    }
}