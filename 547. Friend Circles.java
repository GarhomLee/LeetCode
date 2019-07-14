https://leetcode.com/problems/friend-circles/

// 解法一：Union Find (weighted quick-union)
//        由于元素是数字，且范围在[0:n)，因此可以使用UnionFind。
//        自建class UnionFind，维护以下内部变量：
//        1）int[] parent，parent[i]表示i的父节点。可知根节点parent[i]=i。初始化为parent[i]=i
//        2）int[] size，size[i]表示如果i为根节点的组内元素个数。初始化size[i]=1
//        3）int count，表示当前存在的组数。初始化为n。
//        实现以下功能：
//        1）find(int i)，找到i所处组的根
//        2）connect(int i, int j)，将i和j连接，放到同一组
//        3）isConnected(int i, int j)，查询i和j是否已经连接
//        利用UnionFind，只需要遍历矩阵M的上三角（或下三角），将M[row][col] == 1对应的两个元素连接，
//        最后返回count即可。

class Solution {
    public int findCircleNum(int[][] M) {
        int dimension = M.length;
        UnionFind uf = new UnionFind(dimension);
        for (int row = 0; row < dimension; row++) {
            for (int col = row + 1; col < dimension; col++) {
                if (M[row][col] == 1) uf.connect(row, col);
            }
        }
        return uf.count;
    }
    
    class UnionFind {
        int[] parent;
        int[] size;
        int count;
        /** constructor */
        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            count = n;
        }
        /** find the root of i */
        public int find(int i) {
            while (i != parent[i]) {
                i = parent[i];
            }
            return i;
        }
        /* connect i and j */
        public void connect(int i, int j) {
            /* base case */
            if (isConnected(i, j)) return; 
            /* append the root with smaller size to that with larger size */
            int iRoot = find(i), jRoot = find(j);
            if (size[iRoot] < size[jRoot]) {
                parent[iRoot] = jRoot;
                size[jRoot] += size[iRoot];
            } else {
                parent[jRoot] = iRoot;
                size[iRoot] += size[jRoot];
            }
            count--;  // update count
        }
        /** evaluate if i and j is connected */
        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }
    }
}


解法二：Recursion + DFS