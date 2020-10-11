https://leetcode.com/problems/maximal-network-rank/

idea: Graph indegree
    -Count the sum of degrees of any pair of vertices. Decrease by 1 if the pair of vertices are
     directly connected.
time complexity: O(E + V^2)
space complexity: O(V + E)

class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        int[] degree = new int[n];
        Set<Integer>[] graph = new HashSet[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();    
        }
        
        // build graph, count degree
        for (int[] edge : roads) {
            int v = edge[0], w = edge[1];
            graph[v].add(w);
            graph[w].add(v);
            degree[v]++;
            degree[w]++;
        }
        
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int curr = degree[i] + degree[j];
                if (graph[i].contains(j)) {
                    curr -= 1;
                }
                max = Math.max(max, curr);  // update
            }
        }
        
        return max;
    }
}
