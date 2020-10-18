https://leetcode.com/problems/graph-connectivity-with-threshold/

idea: DisjointSet
    -Directly used GCD calculation will get TLE. 
    -Optimize my solution by using the threshold to increasingly find the 
     possible connected vertices, rather than GCD calculation.
time complexity: O(n^2)
space complexity: O(n)

class Solution {
    class DisjointSet {
        int[] parent;
        int[] size;
        public DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = i;
            }
        }
        
        public void connect(int i, int j) {
            int p1 = find(i), p2 = find(j);
            if (p1 == p2) return;
            
            if (size[p1] > size[p2]) {
                connect(p2, p1);
                return;
            }
            
            parent[p1] = p2;
            size[p2] += size[p1];
        }
        
        public int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }
        
        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }
    }
    
    public int calculateGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        
        return calculateGCD(b, a % b);
    }
    
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        DisjointSet ds = new DisjointSet(n);
        for (long t = threshold + 1; t <= (long) n; t++) {
            for (long i = 1; t * i <= (long) n; i++) {
                ds.connect((int) (t-1), (int) ((t * i) - 1));
            }
        }
        
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            int i = q[0], j = q[1];
            res.add(ds.isConnected(i-1, j-1));
        }
        
        return res;
    }
}