https://leetcode.com/problems/n-ary-tree-preorder-traversal/

// 解法一：Recursion
//        类似Binary Tree的模版。

class Solution {
    List<Integer> res = new ArrayList();
    
    public List<Integer> preorder(Node root) {
        traverse(root);
        return res;
    }
    
    private void traverse(Node root) {
        if (root == null) return;
        
        res.add(root.val);
        for (Node child: root.children) {
            traverse(child);
        }
    }
}


解法二：Iteration with Stack
       详见：https://leetcode.com/problems/n-ary-tree-preorder-traversal/discuss/147955/Java-Iterative-and-Recursive-Solutions