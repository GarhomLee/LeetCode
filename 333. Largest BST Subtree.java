https://leetcode.com/problems/largest-bst-subtree/

// 思路：DFS
//         自建class Node，存以当前节点为根的树是否为BST，如果是，则再存左右边界和该树的所有节点数量。
//         递归函数定义：Node dfs(TreeNode tn)，表示得到以当前节点tn为根的子树是否为BST的信息，存在返回的Node里。
//         终止条件：tn == null，那么它的parent node有可能是BST，所以返回的Node里的isBST为true，且size为0。
//         递归过程：得到左右子节点DFS的结果，分别存在left和right两个Node里。只有当left和right返回的isBST都为true，
//             且left.upper < tn.val < right.lower，这样当前节点tn才能属于BST。在这种情况下，更新curr.isBST，
//             左右边界，和子树的所有节点数量。然后返回之。
//             否则，直接返回isBST都为false的Node。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    int max = 0;
    
    class Node {
        long upper, lower;
        int size;
        boolean isBST;
        public Node(boolean bst) {
            upper = Long.MIN_VALUE;
            lower = Long.MAX_VALUE;
            isBST = bst;
        }
    }
    
    private Node dfs(TreeNode tn) {
        if (tn == null) {
            return new Node(true);
        }
        
        Node left = dfs(tn.left);
        Node right = dfs(tn.right);
        Node curr = new Node(false);

        if (left.isBST && right.isBST && tn.val > left.upper && tn.val < right.lower) {
            curr.isBST = true;
            curr.size = 1 + left.size + right.size;
            max = Math.max(max, curr.size);
            
            curr.lower = tn.left == null ? tn.val : left.lower;
            curr.upper = tn.right == null ? tn.val : right.upper;
        }
        
        return curr;
    }
    
    public int largestBSTSubtree(TreeNode root) {
        dfs(root);
        return max;
    }
}