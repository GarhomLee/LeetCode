https://leetcode.com/problems/satisfiability-of-equality-equations/

idea: Union Find (disjoint set)
time complexity: amortized O(n), n=equations.length
space complexity: O(26)=O(1)

class Solution {
    final int CHAR_NUM = 26;
    
    class DisjointSet {
        int[] parent;
        int[] size;
        
        public DisjointSet(int len) {
            parent = new int[len];
            size = new int[len];
            for (int i = 0; i < len; i++) {
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
    
    public boolean equationsPossible(String[] equations) {
        DisjointSet ds = new DisjointSet(CHAR_NUM);
        for (String s : equations) {
            int v1 = s.charAt(0) - 'a', v2 = s.charAt(3) - 'a';
            if (s.charAt(1) == '=') {
                ds.connect(v1, v2);
            }
        }
        
        for (String s : equations) {
            int v1 = s.charAt(0) - 'a', v2 = s.charAt(3) - 'a';
            if (s.charAt(1) == '!' && ds.isConnected(v1, v2)) {
                return false;
            }
        }
        
        return true;
    }
}