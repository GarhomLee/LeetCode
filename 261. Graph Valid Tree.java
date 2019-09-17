https://leetcode.com/problems/graph-valid-tree/

// 思路：Weighted Union Find with path compression
//         根据题意，无向图是树的充要条件是不成环且所有节点连通，因此可以转化为UnionFind，利用
//         isConnected()检测成环，利用component count统计有多少个component来判断是否所有
//         节点连通。
// 时间复杂度：connect(): amortized O(1); isConnected(): amortized O(1); find(): amortized O(1);
// 空间复杂度：O(n)
// 犯错点：1.细节错误：应该更新component的root的parent，而不是直接更新i或j的parent。

class Solution {
    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge: edges) {
            if (uf.isConnected(edge[0], edge[1])) {
                return false;
            }
            
            uf.connect(edge[0], edge[1]);
        }
        
        return uf.getCount() == 1;
    }
    
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
                size[i] = 1;
            }
        }
        
        public void connect(int i, int j) {
            int root1 = find(i), root2 = find(j);
            if (root1 == root2) {
                return;
            }
            
            if (size[i] > size[j]) {
                int temp = i;
                i = j;
                j = temp;
                // {Mistake 1}
                temp = root1;
                root1 = root2;
                root2 = temp;  // {Correction 1}
            }
            
            //parent[root1] = root2;
            //size[root2] += size[root1];  // {Mistake 1}
            parent[root1] = root2;
            size[root2] += size[root1];  // {Correction 1}
            count--;
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
        
        public int getCount() {
            return count;
        }
    }
}