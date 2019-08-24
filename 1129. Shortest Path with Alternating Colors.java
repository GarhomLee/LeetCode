https://leetcode.com/problems/shortest-path-with-alternating-colors/

// 思路：Two-pass BFS
//         step1: 维护两个adjacent list，分别作为由红色边构成的graph和蓝色边构成的graph。遍历两个edges数组
//                 将两个graph建立起来。
//         step2: 维护两个数组redPath和bluePath，分别表示某节点在红边和在蓝边时和节点0的距离，初始化为自定义的
//                 最大值MAX。
//                 从节点0，利用helper method分别先走红边和先走蓝边，将数组redPath和bluePath的值更新。
//         step3: 在helper method中，总是从g1开始，根据变量isG1判断要从g1还是g2取边，以及要更新的是path1还是
//                 path2。
//                 常规BFS。
//         step4: 对红边和蓝边共进行了两次BFS后，遍历数组redPath和bluePath，更新res数组。
//                 如果redPath[i]和bluePath[i]都是初始值MAX，说明没有被扫描到，因此res[i]=-1。
//                 否则，取redPath[i]和bluePath[i]的较小值。
// 时间复杂度：O(N+E)
// 空间复杂度：O(N+E)
// 犯错点：1.思路错误：只用一个res数组存储中间结果是不够的。因为经过同一个节点的可能是两条颜色不同的路径，有不同的距离。
//             如果只用一个数组存储距离，那么会对颜色不同的路径距离产生干扰，导致出错。
//             如：5
//             [[0,1],[1,2],[2,3],[3,4]]
//             [[1,2],[2,3],[3,1]]
//             因此，要用两个数组分别存储距离。
//         2.细节错误：要对当前层的节点同步进行操作，也就是要维护变量size。

class Solution {
    final int MAX = 10_000_000;
    
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        /* initialize two graphs as adjacent lists */
        List<Integer>[] redGraph = new List[n], blueGraph = new List[n];
        for (int i = 0; i < n; i++) {
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }

        /* build graphs with given edges */
        for (int[] edge: red_edges) {
            int src = edge[0], dest = edge[1];
            redGraph[src].add(dest);
        }
        for (int[] edge: blue_edges) {
            int src = edge[0], dest = edge[1];
            blueGraph[src].add(dest);
        }

        /* initialize paths with customized MAX value */
        int[] redPath = new int[n];
        Arrays.fill(redPath, MAX);
        int[] bluePath = new int[n];
        Arrays.fill(bluePath, MAX);

        /* first pass: start from red edges */
        bfs(redGraph, blueGraph, redPath, bluePath);
        /* second pass: start from blue edges */
        bfs(blueGraph, redGraph, bluePath, redPath);
        
        /* get results of each vertex from either red path or blue path */
        int[] res = new int[n];
        for (int i = 1; i < n; i++) {
            /* if distance of vertex i of both red and blue paths are MAX, it means vertex i is not reachable */
            res[i] = redPath[i] == MAX && bluePath[i] == MAX ? -1 : Math.min(redPath[i], bluePath[i]);
        }
        return res;
    }
    
    private void bfs(List<Integer>[] g1, List<Integer>[] g2, int[] path1, int[] path2) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        boolean isG1 = true;
        while (!queue.isEmpty()) {
            // {Mistake 2}
            int size = queue.size();
            while (size-- > 0) {  // {Correction 2}
                int[] curr = queue.poll();
                int src = curr[0], dist = curr[1];
                List<Integer>[] graph = isG1 ? g1 : g2;  // determine from which graph to choose edge
                int[] path = isG1 ? path1: path2;  // determine adding distance to which path
                for (int dest: graph[src]) {
                    if (path[dest] > dist + 1) {
                        path[dest] = dist + 1;
                        queue.offer(new int[]{dest, dist + 1});
                    }
                }
            }
            
            isG1 = !isG1;
        }
    }
}