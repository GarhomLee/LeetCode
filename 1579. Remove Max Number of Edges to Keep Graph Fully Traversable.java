https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/

idea: UnionFind + Greedy
    -First connect type 3 that is common path for Alice and Bob, then connect the paths specific
     for Alice or Bob.
    -Count the paths which have no helps for connecting two separate sub-compoenents.
time complexity: amortized O(n)
space complexity: O(n)

class Solution {
    public class DisjointSet {
        int[] parent;
        int[] size;
        int component;
        
        public DisjointSet(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 1; i <= n; i++) {
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
    
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int res = 0;
        DisjointSet dsA = new DisjointSet(n), dsB = new DisjointSet(n);
        
        // fisrt connect commonly used edge with type 3
        for (int[] edge : edges) {
            int type = edge[0], u = edge[1], v = edge[2];
            if (type == 3) {
                if (dsA.isConnected(u, v) || dsB.isConnected(u, v)) {
                    res++;
                } else {
                    dsA.connect(u, v);
                    dsB.connect(u, v);
                }
            }
        }
        
        // then connect only the edges specific to A or B
        for (int[] edge : edges) {
            int type = edge[0], u = edge[1], v = edge[2];
            if (type == 1) {
                // specific to A
                if (dsA.isConnected(u, v)) {
                    res++;
                } else {
                    dsA.connect(u, v);
                }
            } else if (type == 2) {
                // specific to B
                if (dsB.isConnected(u, v)) {
                    res++;
                } else {
                    dsB.connect(u, v);
                }
            }
        }
        
        if (dsA.component != 1 || dsB.component != 1) {
            return -1;
        }
        
        return res;
    }
}