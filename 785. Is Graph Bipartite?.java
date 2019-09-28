https://leetcode.com/problems/is-graph-bipartite/

// 解法一：Graph + DFS。关键思路在于，给相邻的节点染上不同的颜色标记，同样标记的节点放到一组，看是否每个节点都只会有1个标记。
//         由于图的表示已经给定，所以不需要重新构建。
//         递归函数定义：boolean dfs(int[][] graph, int curr, int state, int[] visited)，表示如果对
//             当前节点curr做上标记state，并继续搜索，是否能满足每个节点都只会有1种标记。
//         终止条件：visited[curr] != 0，说明当前节点curr已经被标记过，返回当前标记state是否和之前的标记
//             visited[curr]相等。
//         递归过程：首先，标记当前节点curr为state，同时得到相邻的节点的标记nextState。
//                 搜索所有curr的相邻节点next，调用递归函数是否能满足题意，如果不能，那么直接返回false。
//                 如果所有相邻节点都满足题意，那么返回true。
// 时间复杂度：O(V + E)
// 空间复杂度：O(V)
// 犯错点：1.细节错误：对于搜索还没遍历过的点作为起始点，要跳过已经遍历过的节点。否则，对于遍历过的标记为2的节点，
//             会跟起始点的标记1冲突，导致返回false。

class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        
        int[] visited = new int[n];
        for (int i = 0; i < n; i++) {
            // {Mistake 1}
            if (visited[i] != 0) continue;  // {Correction 1}
            if (!dfs(graph, i, 1, visited)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean dfs(int[][] graph, int curr, int state, int[] visited) {
        if (visited[curr] != 0) {
            return visited[curr] == state;
        }
        
        visited[curr] = state;
        int nextState = state == 1 ? 2 : 1;
        for (int next: graph[curr]) {
            if (!dfs(graph, next, nextState, visited)) {
                return false;
            }
        }
        
        return true;
    }
}


解法二：Graph + BFS，不需要考虑入度，注意和269. Alien Dictionary的BFS做法有区别。