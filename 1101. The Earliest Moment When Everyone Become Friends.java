https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/

idea: Sort + DisjointSet
time comp: O(n log n + logs.length)
space comp: O(n)

class Solution {
    class DisjointSet {
        int[] parent;
        int[] size;
        int component;
        public DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            component = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        public void connect(int i, int j) {
            int ri = find(i), rj = find(j);
            if (ri == rj) return;
            if (size[ri] > size[rj]) {
                connect(rj, ri);
                return;
            }
            
            parent[ri] = rj;
            size[rj] += size[ri];
            component--;
        }
        
        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }
        
        public int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            
            return i;
        }
    }
    
    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        DisjointSet ds = new DisjointSet(n);
        for (int[] log: logs) {
            ds.connect(log[1], log[2]);
            if (ds.component == 1) {
                return log[0];
            }
        }
        
        return -1;
    }
}