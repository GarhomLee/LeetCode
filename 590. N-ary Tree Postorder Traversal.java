https://leetcode.com/problems/n-ary-tree-postorder-traversal/submissions/

// 解法一：Recursion
//        类似Binary Tree的模版。

class Solution {
    List<Integer> res = new ArrayList();
    
    public List<Integer> postorder(Node root) {
        traverse(root);
        return res;
    }
    
    private void traverse(Node root) {
        if (root == null) return;
        
        for (Node child: root.children) {
            traverse(child);
        }
        res.add(root.val);
    }
}


解法二：Iteration with two Stacks
       详见：https://leetcode.com/problems/n-ary-tree-postorder-traversal/discuss/174665/Java-Iterative-Solution-Using-Two-Stacks.