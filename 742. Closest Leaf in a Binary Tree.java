https://leetcode.com/problems/closest-leaf-in-a-binary-tree/

解法一：转化成Graph，用BFS

时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    private void dfs(TreeNode node, TreeNode pre, Map<Integer, Set<Integer>> graph) {
        if (node == null) return;
        
        graph.put(node.val, new HashSet<>());
        if (pre != null) {
            graph.get(node.val).add(pre.val);
        }
        if (node.left != null) {
            graph.get(node.val).add(node.left.val);
        }
        if (node.right != null) {
            graph.get(node.val).add(node.right.val);
        }
        dfs(node.left, node, graph);
        dfs(node.right, node, graph);
    }
    
    public int findClosestLeaf(TreeNode root, int k) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        dfs(root, null, graph);
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(k);
        set.add(k);
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (graph.get(curr).size() == 1 && curr != root.val) {
                // the root can also have only 1 neighbor, thus it should be excluded
                return curr;
            }
            for (int next : graph.get(curr)) {
                if (!set.contains(next)) {
                    queue.offer(next);
                    set.add(next);
                }
            }
        }
        
        return root.val; // no other leaf nodes but root node 
    }
}