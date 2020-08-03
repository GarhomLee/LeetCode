https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/

idea: DFS + HashMap
time complexity: O(n * d^2), d=max distance
space complexity: O(h * d), d=max distance

class Solution {
    int ret = 0;
    
    private Map<Integer, Integer> dfs(TreeNode node, int dist) {
        Map<Integer, Integer> map = new HashMap<>(); // dist -> count
        if (node == null) {
            return map;
        }
        if (node.left == null && node.right == null) {
            map.put(0, 1);
            return map;
        }
        
        Map<Integer, Integer> left = dfs(node.left, dist), right = dfs(node.right, dist);
        for (int leftDist : left.keySet()) {
            int leftCount = left.get(leftDist);
            for (int rightDist : right.keySet()) {
                int rightCount = right.get(rightDist);
                if (leftDist + rightDist + 2 <= dist) {
                    ret += leftCount * rightCount;
                }
            }
        }
        
        for (int leftDist : left.keySet()) {
            int leftCount = left.get(leftDist);
            map.put(leftDist + 1, map.getOrDefault(leftDist + 1, 0) + leftCount);
        }
        for (int rightDist : right.keySet()) {
            int rightCount = right.get(rightDist);
            map.put(rightDist + 1, map.getOrDefault(rightDist + 1, 0) + rightCount);
        }
        return map;
    }
    
    public int countPairs(TreeNode root, int distance) {
        dfs(root, distance);
        
        return ret;
    }
}