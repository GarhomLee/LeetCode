https://leetcode.com/problems/time-needed-to-inform-all-employees/

idea: Graph BFS
time complexity: O(V + E)
space complexity: O(V)

class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            
        }
        
        for (int i = 0; i < n; i++) {
            if (manager[i] == -1) continue;
            
            graph[manager[i]].add(i);
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{headID, informTime[headID]});
        int max = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            max = Math.max(max, curr[1]);
            for (int next : graph[curr[0]]) {
                queue.offer(new int[]{next, curr[1] + informTime[next]});
            }
        }
        
        return max;
    }
}