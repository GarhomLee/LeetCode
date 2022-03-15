https://leetcode.com/problems/create-binary-tree-from-descriptions/

idea: HashMap + HashSet
time comp: O(n), n=descriptions.length

class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> nodeMap = new HashMap<>(); // node val -> node
        Set<Integer> childrenSet = new HashSet<>();
        for (int[] edge: descriptions) {
            int parent = edge[0], child = edge[1];
            nodeMap.putIfAbsent(parent, new TreeNode(parent));
            nodeMap.putIfAbsent(child, new TreeNode(child));
            
            if (edge[2] == 1) {
                nodeMap.get(parent).left = nodeMap.get(child);
            } else {                
                nodeMap.get(parent).right = nodeMap.get(child);
            }
            
            childrenSet.add(child);
        }
        
        int root = -1;
        for (int parent: nodeMap.keySet()) {
            if (!childrenSet.contains(parent)) {
                root = parent;
            }
        }
        
        return nodeMap.get(root);
    }
}