https://leetcode.com/problems/critical-connections-in-a-network/

// 思路：Graph，转成有向连通图，再用类似Tarjan算法。视频讲解：https://www.youtube.com/watch?v=aZXi1unBdJA
//         维护全局变量：
//         1）timestamp，给每个节点做unique标记；
//         2）id数组，给每个节点按遍历的先后顺序加上timestamp标记，变成有向图，edge一定是从较小的id指向较大
//             的id。初始化为-1，表示没有搜索过的节点。
//         3）low数组，表示每个节点经过forward edge和最多一个backward edge（搜索到已经遍历过的节点）能到达
//             的最小id的节点。
//         4）visited数组，表示每个节点是否被搜索过。
//         根据Tarjan算法，构成critical connection的两个节点【满足id[from] < low[to]】。
//         递归函数定义：void dfs(int curr, int pre, List<Integer>[] graph, int[] id, int[] low, boolean[] visited)，
//             表示当前节点为curr（未被搜索过），上一个节点为pre，从curr开始更新id[curr]，low[curr]，且向下搜索，看是否有从
//             curr开始的相邻节点构成的边为critical connection。
//         终止条件：无特殊终止条件。
//         递归过程：因为curr一定未搜索过，所以要初始化id[curr]和low[curr]为当前的timestamp，将visited[curr]标记为true。
//             然后，遍历所有跟curr相邻的节点next，可能有3种情况：
//             1）next为上一个节点pre，直接跳过；
//             2）next为未遍历过的节点，即curr->next为forward edge，那么调用递归函数遍历之。
//                 在这个情况下，low[next]已经被更新了，所以可以用low[next]来更新low[curr]。
//                 同时，如果出现了id[curr] < low[next]，说明从curr到next的这条边就是critical connection，
//                 加入res列表。
//             3）next为已经遍历过的节点，即curr->next为backward edge，那么一定有id[next]比id[curr]小，且
//                 一定存在从next到curr的一条通路，所以curr->next一定不是critical connection，所以只需要用
//                 id[next]（而不是low[next]）来更新low[curr]。
// 时间复杂度：O(V + E)
// 空间复杂度：O(V + E)

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    int timestamp = 0;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        /* construct the graph */
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (List<Integer> edge: connections) {
            int from = edge.get(0), to = edge.get(1);
            graph[from].add(to);
            graph[to].add(from);
        }
        
        /* initialize */
        int[] id = new int[n];
        Arrays.fill(id, -1);
        int[] low = new int[n];
        boolean[] visited = new boolean[n];
        
        /* DFS to find the critical connections */
        for (int i = 0; i < n; i++) {
            if (id[i] == -1) {
                dfs(i, -1, graph, id, low, visited);
            }
        }
        
        return res;
    }
    
    private void dfs(int curr, int pre, List<Integer>[] graph, int[] id, int[] low, boolean[] visited) {
        /* initialization */
        id[curr] = timestamp;
        low[curr] = timestamp;
        timestamp++;
        visited[curr] = true;
        
        /* search neighbors */
        for (int next: graph[curr]) {
            if (next == pre) continue;  // avoid going back to where it comes from
            
            if (!visited[next]) {
                dfs(next, curr, graph, id, low, visited);
                low[curr] = Math.min(low[curr], low[next]);  // update low[curr]

                if (id[curr] < low[next]) {  // critical connection is found
                    res.add(Arrays.asList(curr, next));
                }
            } else {  // next vertex has been visited, this is a backward edge
                low[curr] = Math.min(low[curr], id[next]);
            }
        }
    }
}