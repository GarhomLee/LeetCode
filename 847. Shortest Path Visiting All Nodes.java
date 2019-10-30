https://leetcode.com/problems/shortest-path-visiting-all-nodes/

思路：BFS + Bit Manipulation。视频讲解：https://www.youtube.com/watch?v=Vo3OEN2xgwk

时间复杂度：O(n * 2^n)
空间复杂度：O(n * 2^n)

class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        if (n == 0) {
            return 0;
        }
        
        boolean[][] visited = new boolean[n][1 << n];
        Queue<Node> queue = new LinkedList<>();
        /* put all nodes and its initial state into queue */
        for (int i = 0; i < n; i++) {
            int state = (1 << i);
            queue.offer(new Node(i, state));
            visited[i][state] = true;
        }
        
        int path = 0;
        int goal = (1 << n) - 1;  // the goal state to reach
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node curr = queue.poll();
                if (curr.state == goal) {
                    return path;
                }
                
                for (int next : graph[curr.index]) {
                    int nextState = curr.state | (1 << next);
                    if (visited[next][nextState]) continue;
                    
                    queue.offer(new Node(next, nextState));
                    visited[next][nextState] = true;
                }
            }
            
            path++;
        }
        
        return path;
    }
    
    class Node {
        int index, state;
        public Node(int i, int s) {
            index = i;
            state = s;
        }
    }
}