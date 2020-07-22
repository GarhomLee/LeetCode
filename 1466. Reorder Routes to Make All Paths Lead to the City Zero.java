https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/

idea: Graph + BFS
    -Compare the different edges in directed and undirected graphs.
time complexity: O(V + E)
space complexity: O(V)

class Solution {
    public int minReorder(int n, int[][] connections) {
        Set<Integer>[] directedGraph = new Set[n], undirectedGraph = new Set[n];
        for (int i = 0; i < n; i++) {
            directedGraph[i] = new HashSet<>();
            undirectedGraph[i] = new HashSet<>();
        }
        
        for (int[] edge : connections) {
            int from = edge[0], to = edge[1];
            directedGraph[from].add(to);
            undirectedGraph[from].add(to);
            undirectedGraph[to].add(from);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        visited[0] = true;
        int ret = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : undirectedGraph[curr]) {
                if (visited[next]) continue;
                
                queue.offer(next);
                visited[next] = true;
                if (!directedGraph[next].contains(curr)) {
                    ret++;
                }
            }
        }
        
        return ret;
    }
}