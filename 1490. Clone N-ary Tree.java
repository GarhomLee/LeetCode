https://leetcode.com/problems/clone-n-ary-tree/

idea: DFS
time complexity: O(n)
space complexity: O(n)

class Solution {
    private Node dfs(Node node) {
        if (node == null) return null;
        
        Node copy = new Node(node.val);
        for (Node child : node.children) {
            copy.children.add(dfs(child));
        }
        
        return copy;
    }
    
    public Node cloneTree(Node root) {
        return dfs(root);
    }
}