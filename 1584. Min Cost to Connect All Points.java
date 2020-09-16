https://leetcode.com/problems/min-cost-to-connect-all-points/

idea: Minimum Spanning Tree (Kruskals algo)
time complexity: O(E log E)
space complexity: O(E)

class Solution {
    public class DisjointSet {
        int[] parent;
        int[] size;
        int component;
        
        public DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            component = n;
        }
        
        public int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            
            return i;
        }
        
        public void connect(int i, int j) {
            if (isConnected(i, j)) return;
            
            int rooti = find(i), rootj = find(j);
            if (size[rooti] > size[rootj]) {
                connect(rootj, rooti);
                return;
            }
            
            parent[rooti] = rootj;
            size[rootj] += size[rooti];
            component--;
        }
        
        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }
    }
    
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                pq.offer(new int[]{i, j, dist});
            }
        }
        
        DisjointSet ds = new DisjointSet(n);
        int res = 0;
        while (ds.component > 1) {
            int[] curr = pq.poll();
            int u = curr[0], v = curr[1], cost = curr[2];
            if (!ds.isConnected(u, v)) {
                ds.connect(u, v);
                res += cost;
            }
        }
        
        
        return res;
    }
}