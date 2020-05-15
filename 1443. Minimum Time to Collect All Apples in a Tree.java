https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/

idea: Graph + DFS
time complexity: O(n)
space complexity: O(n)

class Solution {
    /* return how many steps it takes to get all apples in the subtree if starting from node curr */
    private int dfs(int curr, List<Integer>[] graph, boolean[] visited, List<Boolean> hasApple) {
        int total = 0;
        visited[curr] = true;
        for (int next : graph[curr]) {
            if (visited[next]) continue;
            
            int count = dfs(next, graph, visited, hasApple);
            total += count;

            // if there are steps to take further, there should be 2 more steps, to go to
            // the next node and then come back
            if (count > 0 || hasApple.get(next)) {
                total += 2;
            }
        }
        
        return total;
    }
    
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            int v = edge[0], w = edge[1];
            graph[v].add(w);
            graph[w].add(v);
        }
        
        return dfs(0, graph, new boolean[n], hasApple);
    }
}