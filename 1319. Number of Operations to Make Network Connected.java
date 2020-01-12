https://leetcode.com/problems/number-of-operations-to-make-network-connected/

思路：Union Find
        先判断connection数量，如果少于n-1，那么一定不能连接所有node，返回-1.
        当存在connection >= n-1，那么需要移动的最少edge数就等于union后的不连接的component个数-1，
        且一定有足够的edge可以移动。
时间复杂度：O(n)
空间复杂度：O(n)
犯错点：1.细节错误：在connect()中，如果size[ri] > size[rj]，递归调用connect()后，要加return语句，
            否则会继续运行后续语句。

class Solution {
    class UnionFind {
        int[] parent;
        int[] size;
        int component;
        
        public UnionFind(int n) {
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
        
        public int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            
            return i;
        }
    }
    
    public int makeConnected(int n, int[][] connections) {
        int len = connections.length;
        if (len < n - 1) {
            return -1;
        }
        
        UnionFind uf = new UnionFind(n);
        for (int[] edge : connections) {
            uf.connect(edge[0], edge[1]);
        }
        
        return uf.component - 1;
    }
}