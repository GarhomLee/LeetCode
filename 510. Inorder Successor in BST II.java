https://leetcode.com/problems/inorder-successor-in-bst-ii/

思路：先从子树找successor，即右子树的最左边节点；
        再从parent找successor，找到某个parent使得当前节点在该parent节点的左子树。
时间复杂度：O(n)
空间复杂度：O(1)

class Solution {
    public Node inorderSuccessor(Node node) {
        if (node == null) return null;
        
        if (node.right != null) {
            Node suc = node.right;
            while (suc.left != null) {
                suc = suc.left;
            }
            return suc;
        }
        
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        
        return node.parent;
    }
}