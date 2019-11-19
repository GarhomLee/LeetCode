https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/

// 思路：Union Find，模版题
// 时间复杂度：O(n) with weighted components and path compression
// 空间复杂度：O(n)

class Solution {
    class UnionFind {
        int[] parent;
        int[] size;
        int count;
        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            count = n;
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
            count--;
        }
        
        public int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }
        
        public int getCount() {
            return count;
        }
    }
    
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.connect(edge[0], edge[1]);
        }
        
        return uf.getCount();
    }
}