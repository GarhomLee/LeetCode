https://leetcode.com/problems/redundant-connection/

// 对比：685. Redundant Connection II，本题为无向图

// 思路：乍一看，是Graph问题；实际上，可以转化为【用Union Find来解题】。
//         根据题意，无向图中n个节点只需要n-1条边就可以组成一棵树，额外多出的第n条边则需要被删除。由于是无向图，
//         额外多出的边一定会使得图成环。如果用DFS检测是否成环，时间复杂度O(n^2)。如果利用Union Find，如果最新
//         加进来的边的两个节点有共同的root，那么这条边加里进来后一定成环。
//         step0: 构建class UnionFind，维护数组root和数组size分别表示每个节点的root和每个union set component
//             的元素个数。
//             实现以下功能：
//             1）void connect(int i, int j)，将i和j连接在同一个union set中。
//                 利用find()分别找到i和j的root，将size较小的component连接到size较大的component下，更新root
//                 和size。
//             2）boolean isConnected(int i, int j)，判断i和j是否连接在同一个union set中。
//                 也就是判断i和j的root是否相同。
//             3）int find(int i)，找到i的root。
//                 利用while循环直到找到root为i本身。
//                 注意：可以用path compression优化，将root[i]更新为它自己的parent，即root[i]=root[root[i]]，
//                 然后i跳过原来的root[i]而直接和新的root[i]（即root[root[i]]，grandparent）比较判断。
//         step1: 遍历edges数组，找到第一条使得图成环的边，返回之。
//                 否则，调用connect()将两个顶点连接。
//         step2: 超出题目要求，返回null，或者throw execption。
// 时间复杂度：amortized O(n)
// 空间复杂度：O(n)

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length);
        for (int[] edge: edges) {
            if (uf.isConnected(edge[0], edge[1])) {
                return edge;
            }
            uf.connect(edge[0], edge[1]);
        }
        return null;
    }
    
    class UnionFind {
        int[] root;
        int[] size;
        public UnionFind(int n) {
            root = new int[n + 1];
            size = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                root[i] = i;
                size[i] = 1;
            }
        }
        
        public void connect(int i, int j) {
            if (isConnected(i, j)) {
                return;
            }
            int r1 = find(i), r2 = find(j);
            if (size[r1] < size[r2]) {
                root[r1] = r2;
                size[r2] += size[r1];
            } else {
                root[r2] = r1;
                size[r1] += size[r2];
            }
        }
        
        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }
        
        public int find(int i) {
            while (root[i] != i) {
                root[i] = root[root[i]];
                i = root[i];
            }
            return i;
        }
    }
}