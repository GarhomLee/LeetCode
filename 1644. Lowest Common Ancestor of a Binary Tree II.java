https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/

idea: DFS (postorder traversal)
time complexity: O(n)
space complexity: O(n)

class Solution {
    int count = 0;  // increase when each time a query node is found
    
    public TreeNode helper(TreeNode curr, TreeNode p, TreeNode q) {
        if (curr == null) return null;
        
        TreeNode left = helper(curr.left, p, q);
        TreeNode right = helper(curr.right, p, q);
        
        if (curr == p || curr == q) {
            count++;
            return curr;
        }

        if (left == null) return right;
        if (right == null) return left;
        return curr;
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = helper(root, p, q);
        return count == 2 ? res : null; // LCA is truly found only when 2 query nodes are found
    }
}


二刷：
idea2: 2-pass，先验证是否两个node都存在，如果是，再直接用LC 236的方法。

class Solution {
    int count = 0;
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        validate(root, p, q);
        if (count < 2) return null;
        
        return dfs(root, p, q);
    }
    
    // LC 236
    public TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        if (root == p || root == q) return root;
        
        TreeNode left = dfs(root.left, p, q);
        TreeNode right = dfs(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }
    
    private void validate(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return;
        
        if (node == p || node == q) count++;
        validate(node.left, p, q);
        validate(node.right, p, q);
    }
}


idea3: traverse from child to parent, incorporating LC 1650

class Solution {
    Map<TreeNode, TreeNode> parentMap = new HashMap<>();
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        buildMap(root);
        if (!parentMap.containsKey(p) || !parentMap.containsKey(q)) return null;
        
        return dfs(p, q);
    }
    
    // LC 1650
    public TreeNode dfs(TreeNode p, TreeNode q) {
        Set<TreeNode> visited = new HashSet<>();
        TreeNode curr = p;
        while (curr != null) {
            if (curr == q) return q;
            
            visited.add(curr);
            curr = parentMap.containsKey(curr) ? parentMap.get(curr) : null;
        }
        
        curr = q;
        while (curr != null) {
            if (curr == p) return p;
            if (visited.contains(curr)) return curr;
            
            curr = parentMap.containsKey(curr) ? parentMap.get(curr) : null;
        }
        
        return null;
    }
    
    private void buildMap(TreeNode node) {
        if (node == null) return;
        
        if (node.left != null) {
            parentMap.put(node.left, node);
            buildMap(node.left);
        } 
        if (node.right != null) {
            parentMap.put(node.right, node);
            buildMap(node.right);
        }
    }
}