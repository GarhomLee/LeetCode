https://leetcode.com/contest/biweekly-contest-7/problems/optimize-water-distribution-in-a-village/

// 思路：Graph经典Minimum Spanning Tree问题。
//         关键点：思路转换，将“打井”的成本well[i]转化成从第0个house到第i个house的路径权重，也就是求解
//             包含n+1个节点[0:N]的Minimum Spanning Tree。

// 解法一：Prim's algo，用节点搜索边。注意和Kruskal's algo的不同之处。
//         step0: 构建graph，用List[]实现adjacent list。
//                 用wells数组【构建从节点0到其他节点的边长】，用pipes数组构建其他各节点之间的边长（权重）。
//                 维护Set<Integer> visited，记录已经搜索过的节点。
//                 维护PriorityQueue<int[]> pq，记录待搜索节点以及和该节点对应的当前搜索到的最短边。顶部元素为最短边及对应节点。
//                 维护cost数组，cost[i]表示和节点i相接的所有邻边中边长最小的值。
//         step1: 初始化cost数组为Integer.MAX_VALUE，注意cost[0]要单独初始化为0。
//                 将int[]{0, 0}放入PriorityQueue作为seed。
//         step2: 当visited中搜索过的节点个数不足n+1时，不断循环。
//                 从PriorityQueue顶部取元素，将节点src加入visited。
//                 遍历src的所有相邻节点dest，将没有被遍历过的且边长cost[dest]比从src到dest要长的节点dest
//                 加入PriorityQueue，同时更新cost[dest]为从src到dest的边长nextCost。
//         step3: 搜索完n+1个节点后，将cost数组的所有值相加，就能得到MST的权重和sum，即为所求。
// 时间复杂度：O(E log V)，见：https://en.wikipedia.org/wiki/Prim's_algorithm#Time_complexity
// 空间复杂度：O(E + V)
// 犯错点：1.细节错误：Prim's algo中，不是想shortest path的算法那样用从当前节点i到目标节点的总距离来更新cost[i]，
//             而只需要用节点i的所有邻边中的最小值来更新cost[i]。

class Solution {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        /* build graph as adjacent list */
        List<int[]>[] graph = new List[n + 1];
        graph[0] = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            graph[0].add(new int[]{i, wells[i - 1]});
            graph[i].add(new int[]{0, wells[i - 1]});
        }
        for (int[] edge: pipes) {
            int house1 = edge[0], house2 = edge[1], cost = edge[2];
            graph[house1].add(new int[]{house2, cost});
            graph[house2].add(new int[]{house1, cost});
        }
        /* initialization */
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                return p1[1] - p2[1];
            }
        });
        int[] cost = new int[n + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[0] = 0;  // set cost[0] to 0
        
        pq.offer(new int[]{0, 0});  // add a seed
        while (visited.size() < n + 1) {
            int[] curr = pq.poll();
            int src = curr[0], currCost = curr[1];
            visited.add(src);  // mark src as visited
            for (int[] next: graph[src]) {
                int dest = next[0], nextCost = next[1];
                if (visited.contains(dest)) continue;
                //if (cost[dest] > currCost + nextCost)  // {Mistake 1}
                if (cost[dest] > nextCost) {  // {Correction 1}
                    //cost[dest] = currCost + nextCost;  // {Mistake 1}
                    cost[dest] = nextCost;  // {Correction 1}
                    pq.offer(new int[]{dest, cost[dest]});
                }
            }
        }
        /* get the sum of all least-weighted edges by retrieving the edge weight info from the associated vertex */
        int sum = 0;
        for (int num: cost) {
            sum += num; 
        }
        
        return sum;
    }
}


// 解法二：Kruskal's algo，直接搜索边。注意和Prim's algo的不同之处。
//         step0: 用wells数组【构建从节点0到其他节点的边长】，用pipes数组构建其他各节点之间的边长（权重）。
//                 （不需要维护HashSet记录搜索过的节点，和Prim's algo不同）
//                 构建class DisjointSet，在取边时检查是否成环。（和Prim's algo不同）
//                 维护PriorityQueue<int[]> pq，记录【所有未搜索的边和两个端点】。顶部元素为最短边及对应的两个节点。（和Prim's algo不同）
//                 （不需要维护cost数组，【直接在取边时将边长加入变量sum】，和Prim's algo不同）
//         step1: 将【所有边和对应的两个端点】放入PriorityQueue作为seed。（和Prim's algo不同）
//         step2: pq不为空时，不断循环。（和Prim's algo不同）
//                 从PriorityQueue顶部取当前的最短边长，判断这条边的加入是否会导致成环。
//                 如果不会成环，那么将边长加入变量sum，将两个端点调用connect()连接，同时边数i++。
//         step3: 遍历完pq，结果返回sum。
// 时间复杂度：O(E log E) = O(E log V)，见：https://en.wikipedia.org/wiki/Kruskal%27s_algorithm#Complexity
// 空间复杂度：O(E log E)

class Solution {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        DisjointSet ds = new DisjointSet(n + 1);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);   // {v1, v2, cost}
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{0, i + 1, wells[i]});
        }
        for (int[] edge: pipes) {
            pq.offer(edge);
        }
        
        int ret = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (ds.isConnected(curr[0], curr[1])) continue;
            
            ds.connect(curr[0], curr[1]);
            ret += curr[2];
        }
        
        return ret;
    }
    
    class DisjointSet {
        int[] parent, size;
        
        public DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
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
        }
        
        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }
        
        public int find(int i) {
            while (parent[i] != i) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            
            return i;
        }
    }
}