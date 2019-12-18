https://leetcode.com/problems/tree-diameter/

// 解法一：double BFS (Greedy)。证明：https://stackoverflow.com/questions/20010472/proof-of-correctness-algorithm-for-diameter-of-a-tree-in-graph-theory
//         第一次BFS：从任意点A出发，找到离这个点最远的点B
//         第二次BFS：从找到的最远点B出发，找到它的最远点C的最远距离
// 时间复杂度：O(2n) = O(n)
// 空间复杂度：O(n)

class Solution {
    public int treeDiameter(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int v = edge[0], w = edge[1];
            graph.putIfAbsent(v, new HashSet<>());
            graph.get(v).add(w);
            graph.putIfAbsent(w, new HashSet<>());
            graph.get(w).add(v);
        }
        
        int curr = edges[0][0];
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(curr);
        set.add(curr);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                curr = queue.poll();
                for (int next : graph.get(curr)) {
                    if (set.contains(next)) continue;
                    
                    queue.offer(next);
                    set.add(next);
                }
            }
        }
        
        int dist = -1;
        queue.clear();
        queue.offer(curr);
        set.clear();
        set.add(curr);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                curr = queue.poll();
                for (int next : graph.get(curr)) {
                    if (set.contains(next)) continue;
                    
                    queue.offer(next);
                    set.add(next);
                }
            }
            dist++;
        }
        
        return dist;
    }
}


// 解法二：DFS
//         用dfs求解从某一点的相邻点出发的【两条最长路径】，用这两条路径的和更新全局变量diameter。
//         递归函数定义：int dfs(Set<Integer>[] graph, int curr, boolean[] visited)，表示求得从curr
//             节点出发的到【还没被搜索过的连通节点】的最长路径。
//         终止条件：无特殊终止条件。如果curr没有还没被搜索过的连通节点，那么返回0。
//         递归过程：维护变量max1和max2，分别表示curr的相邻点调用dfs后得到的最长路径和次长路径，初始化都为-1
//             （为了在curr没有还没被搜索过的连通节点时更新diameter为0和返回0）。
//             搜索完所有相邻节点，更新diameter为自己和2 + max1 + max2的较大值。
//             返回值只需要1+max1。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    int diameter = 0;
    
    private int dfs(Set<Integer>[] graph, int curr, boolean[] visited) {
        int max1 = -1, max2 = -1;
        visited[curr] = true; // mark as visited
        for (int next : graph[curr]) {
            if (visited[next]) continue;
            
            int len = dfs(graph, next, visited);
            if (len > max1) {
                max2 = max1;
                max1 = len;
            } else if (len > max2) {
                max2 = len;
            }
        }
        
        diameter = Math.max(diameter, 2 + max1 + max2);
        return 1 + max1;
    }
    
    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        Set<Integer>[] graph = new Set[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] edge : edges) {
            int v = edge[0], w = edge[1];
            graph[v].add(w);
            graph[w].add(v);
        }
        
        dfs(graph, edges[0][0], new boolean[n]);
        return diameter;
    }
}