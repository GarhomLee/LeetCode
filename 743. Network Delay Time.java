https://leetcode.com/problems/network-delay-time/

// 解法一：经典的Dijkstra's single source all destinations shortest path算法，背模版。类似BFS
//         step0: 维护变量：List[] graph数组，数组中每个元素graph[src]都是一个List，List存储int[]，包含一个节点dest和
//                 从src到dest需要的时间（权重）time。graph的表示形式为adjacent list。
//                 HashMap dist，key为节点dest，value为从src到当前节点dest所需的最短时间。
//                 PriorityQueue pq，存放接下来可以搜索的dest节点以及从src到这个dest节点的距离。PriorityQueue是minHeap，
//                 以距离排序，每次取的是距离最短的节点。
//         step1: 初始化graph，所有元素都new一个新的List。然后遍历times数组，将graph构建起来。
//         step2: 将节点K放入PriorityQueue作为起始元素，同时更新HashMap。
//         step3: 不断从PriorityQueue取出顶部元素，得到src，比较当前情况下src到所有相邻的节点dest的距离total+time和已经搜索过
//                 的距离dist.get(dest)。如果total+time更小，或者dist不存在dest元素表示没有被搜索过，那么要更新dist中的信息，
//                 将dest对应的距离更新为total+time，同时将dest和对应的距离放入PriorityQueue中以便接下来的搜索。
//         step4: 将所有从src可以到达的节点都搜索过以后，如果dist中的key个数小于N，说明有的节点没有被遍历到，那么返回-1.
//         step5: 遍历dist中的所有values，表示各个节点到节点K的最短距离，那么题目要求的结果是所有最短距离中最大的值。
// 时间复杂度：O(N log N + E)
// 空间复杂度：O(N + E)

class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        /* initialize the graph as adjacent list */
        List<int[]>[] graph = new List[N + 1];
        for (int src = 1; src <= N; src++) {
            graph[src] = new ArrayList<>();
        }
        /* build the graph */
        for (int[] edge: times) {
            int src = edge[0], dest = edge[1], time = edge[2];
            graph[src].add(new int[]{dest, time});
        }
        /* initialize HashMap and PriorityQueue */
        Map<Integer, Integer> dist = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair1[1] - pair2[1];
            }
        });
        
        /* set the initial value of searching */
        pq.offer(new int[]{K, 0});
        dist.put(K, 0);
        /* search all vertices with shortest distance by taking advantages of PriorityQueue */
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int src = curr[0], total = curr[1];
            /* scan through all adjacent neigbors */
            for (int[] next: graph[src]) {
                int dest = next[0], time = next[1];
                /* the distance from src to dest can be updated */
                if (!dist.containsKey(dest) || dist.get(dest) > total + time) {
                    dist.put(dest, total + time);  // update before adding into PriorityQueue
                    pq.offer(new int[]{dest, total + time});  // add to PriorityQueue for future search
                }
            }
        }
        /* some vertices are not reachable */
        if (dist.size() < N) {
            return -1;
        }
        /* find the max value among all shortest path */
        int res = 0;
        for (int time: dist.values()) {
            res = Math.max(res, time);
        }
        return res;
    }
}


解法二：Bellman-Ford算法，类似动态规划。视频讲解：https://www.youtube.com/watch?v=vwLYDeghs_c
时间复杂度：O(N*E)
空间复杂度：O(N)