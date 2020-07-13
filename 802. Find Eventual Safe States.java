https://leetcode.com/problems/find-eventual-safe-states/

idea: Graph
    -Detect cycle, similar to 207. Course Schedule
time complexity: O(V + E)
space complexity: O(V)

class Solution {
    private boolean isCyclic(int[][] graph, int curr, int[] visited, List<Integer> list) {
        if (visited[curr] == 1 || visited[curr] == 3) {
            return true;
        }
        if (visited[curr] == 2) {
            return false;
        }
        
        visited[curr] = 1;
        for (int next : graph[curr]) {
            if (isCyclic(graph, next, visited, list)) {
                visited[curr] = 3;
                return true;
            }
        }
        visited[curr] = 2;
        return false;
    }
    
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] visited = new int[n];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isCyclic(graph, i, visited, list)) {
                list.add(i);
            }
        }
        
        return list;
    }
}