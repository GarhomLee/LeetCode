https://leetcode.com/problems/count-subtrees-with-max-distance-between-cities/

idea: Bit Mask + BFS
    -Since the maximum num of nodes is 15, we can use bits to indicate the possible sets of subgraphs.
    -For each subgraph (state), check if it is a connected graph.
    -Use BFS to find the max dist by gradually removing the leaf nodes at each step.
time complexity: O(2^n * (E + V))
space complexity: O(V + E)

class Solution {
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        int[] res = new int[n - 1];
        for (int i = 3; i < (1 << n); i++) {
            if ((i & (i - 1)) == 0) continue;   // exclude the bits with only one 1
            
            findSubgraph(i, res, edges, n);
        }
        
        return res;
    }
    
    // get the max dist in the given state. Need to check if this state can form a connected graph
    private void findSubgraph(int state, int[] res, int[][] edges, int n) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        int totalDegree = 0;
        int nodeCount = Integer.bitCount(state);    // num of 1s is num of nodes
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int u = edge[0] - 1, v = edge[1] - 1;
            if ((state & (1 << u)) == 0 || (state & (1 << v)) == 0) continue;
            
            totalDegree += 2;
            degree[u]++;
            degree[v]++;
            graph[u].add(v);
            graph[v].add(u);
        }
        
        // check whether this state forms a connected graph
        if (totalDegree < (nodeCount - 1) * 2) return;
        
        int dist = getDiameter(graph, degree, n, nodeCount);
        res[dist - 1]++;
    }
    
    // use bfs to find the longest distance between any two nodes
    private int getDiameter(List<Integer>[] graph, int[] degree, int n, int nodeCount) {
        int halfDist = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
                visited[i] = true;
            }
        }
        
        while (nodeCount > 2) {
            int size = queue.size();
            while (size-- > 0) {
                int curr = queue.poll();
                nodeCount--;
                degree[curr]--;
                for (int next : graph[curr]) {
                    if (visited[next]) continue;
                    degree[next]--;
                    if (degree[next] == 1) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }
            halfDist++;
        }
        
        return halfDist * 2 + (nodeCount == 2 ? 1 : 0);
    }
}