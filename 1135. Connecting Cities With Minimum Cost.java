https://leetcode.com/problems/connecting-cities-with-minimum-cost/

// 思路：Graph，经典MST模版，用Kruskal's algo直接搜索边。见1168. Optimize Water Distribution in a Village。 
// 注意：用Kruskal's algo时，graph的构建是【不必要的】，只需要直接将构成边的端点组成的int[]加入PriorityQueue。
//      关键是从PriorityQueue中得到不会成环的边。
// 时间复杂度：O(E log E) = O(E log V)，见：https://en.wikipedia.org/wiki/Kruskal%27s_algorithm#Complexity
// 空间复杂度：O(E log E)
// 犯错点：1.细节错误：总边数可能会少于构成MST的边数，即PriorityQueue可能为空，所以需要单独判断。

class Solution {
    public int minimumCost(int N, int[][] connections) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                return p1[2] - p2[2];
            }
        });
        for (int[] edge: connections) {
            int v1 = edge[0] - 1, v2 = edge[1] - 1, cost = edge[2];
            pq.offer(new int[]{v1, v2, cost});
        }
        
        int minCost = 0;
        UnionFind uf = new UnionFind(N);
        //while (uf.getCount() > 1 && !pq.isEmpty())  // {Mistake 1}
        while (uf.getCount() > 1 && !pq.isEmpty()) {  // {Correction 1}
            int[] curr = pq.poll();
            int v1 = curr[0], v2 = curr[1], cost = curr[2];
            if (uf.isConnected(v1, v2)) continue;
            
            uf.connect(v1, v2);
            minCost += cost;
        }
        
        if (uf.getCount() > 1) {
            return -1;
        }
        
        return minCost;
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
        
        public int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }
        
        public void connect(int i, int j) {
            int root1 = find(i), root2 = find(j);
            if (root1 == root2) {
                return;
            }
            if (size[root1] > size[root2]) {
                int temp = root1;
                root1 = root2;
                root2 = temp;
            }
            
            parent[root1] = root2;
            size[root2] += size[root1];
            count--;
        }
        
        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }
        
        public int getCount() {
            return count;
        }
    }
}