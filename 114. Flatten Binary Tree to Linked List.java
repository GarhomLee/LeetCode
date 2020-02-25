https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

// 解法一：Recursion as reverse preorder traversal，不太好理解
//        维护【全局变量pre】。
//        【先处理右子树，再处理左子树】，处理完后pre就会直接指向root.left，这个root.left已经是处理好的已经把root.right接过去了。
//        因此，只需要把pre指向的整个子树接到root.right，然后root.left设为null，再更新pre为当前root即可。

class Solution {
    TreeNode pre = null;
    
    public void flatten(TreeNode root) {
        /* corner case */
        if (root == null) return;
        
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }
}


// 解法二：Recursion by putting flattened left and right child tree together，比较好理解
//        先递归处理好左右子树（哪个子树先处理都可以）。
//        接下来，在左子树找到root.right的前驱节点，即root.left的右子树的最右leaf node。
//        然后，将root.right接在前驱节点，再用处理好的整个root.left替换root.right，把root.left设为null，那么以当前root
//        为根的树就处理完毕了。

class Solution {
    public void flatten(TreeNode root) {
        /* corner case */
        if (root == null) return;
        
        flatten(root.left);  // flatten left child tree
        flatten(root.right);  // flatten right child tree
        if (root.left != null) {
            TreeNode pre = root.left;
            while (pre.right != null) pre = pre.right;  // find the leaf node of flattened left child tree
            pre.right = root.right;  // link flattened right child tree to the leaf node of flattened left child tree
            root.right = root.left;  // put all flattened child tree to root.right
            root.left = null;  // set left child tree as null
        }
    }
}


// 解法三：Iteration，解法二的改写
//        类似Morris Traversal。
//        如果curr.left != null，那么需要把curr.right接到curr.left的右子树的最右节点（即inorder traversal predecessor），
//        然后把新的curr.left移到curr.right，然后curr.left赋值为null。
//        curr更新为curr.right，重复上述步骤，直至curr == null。

class Solution {
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode pre = curr.left;
                while (pre.right != null) pre = pre.right;
                pre.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            } 
            curr = curr.right;
        }
    }
}

解法四：
class Solution {
    private TreeNode dfs(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null && node.right == null) {
            return node;
        }
        
        TreeNode leftTail = dfs(node.left);
        TreeNode rightTail = dfs(node.right);
        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }
        
        return rightTail == null ? leftTail : rightTail;
    }
    
    public void flatten(TreeNode root) {
        dfs(root);
    }
}