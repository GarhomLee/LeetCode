https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/

idea: Count indegree of each node, and the result is the ones with 0 indegree.
time complexity: O(V + E)
space complexity: O(V)

class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<Integer> res = new ArrayList<>();
        
        int[] indegree = new int[n];
        for (List<Integer> edge : edges) {
            int from = edge.get(0), to = edge.get(1);
            indegree[to]++;
        }
        
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                res.add(i);
            }
        }
        
        return res;
    }
}