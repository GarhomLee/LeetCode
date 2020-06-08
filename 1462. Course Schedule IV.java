https://leetcode.com/problems/course-schedule-iv/

idea: Graph + BFS
    -Build a mapping from each node to all the nodes it can reach to.
time complexity: O(V + E + V*E + q), q=num of queries
space complexity: O(V^2)

class Solution {
    private void buildPost(int i, List<Integer>[] graph, Set<Integer>[] post) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : graph[curr]) {
                if (post[i].contains(next)) continue;
                
                post[i].add(next);
                queue.offer(next);
            }
        }
    }
    
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        List<Boolean> res = new ArrayList<>();
        List<Integer>[] graph = new List[n];
        Set<Integer>[] post = new Set[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            post[i] = new HashSet<>();
        }
        for (int[] edge : prerequisites) {
            int from = edge[0], to = edge[1];
            graph[from].add(to);
        }
        
        for (int i = 0; i < n; i++) {
            buildPost(i, graph, post);
        }
        
        for (int[] q : queries) {
            int from = q[0], to = q[1];
            res.add(post[from].contains(to));
        }
        
        return res;
    }
}