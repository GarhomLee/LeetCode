https://leetcode.com/problems/cheapest-flights-within-k-stops/



class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // int minCost = Integer.MAX_VALUE;
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<int[]>();
        }
        
        for (int[] flight : flights) {
            int from = flight[0], to = flight[1], cost = flight[2];
            graph[from].add(new int[]{to, cost});
        }
        
        // Queue<int[]> queue = new LinkedList<>();
        // Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int[][] cost = new int[K + 2][n];
        for (int i = 0; i <= K + 1; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }
        
        queue.offer(src);
        cost[0][src] = 0;
        int step = 0;
        while (!queue.isEmpty() && step < K + 1) {
            int size = queue.size();
            while (size-- > 0) {
                int currStop = queue.poll();
                // System.out.println("currStop="+currStop+",cost[currStop]="+cost[currStop]);
                if (currStop == dst) continue;
                
                for (int[] next : graph[currStop]) {
                    int nextStop = next[0], nextCost = next[1];
                    if (cost[step][currStop] + nextCost < cost[step + 1][nextStop]) {
                        cost[step + 1][nextStop] = cost[step][currStop] + nextCost;
                        // System.out.println("nextStop="+nextStop+",cost[nextStop]="+cost[nextStop]);
                        queue.offer(nextStop);
                    }
                }
            }
            
            step++;
        }
        
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < K + 2; i++) {
            minCost = Math.min(minCost, cost[i][dst]);
        }
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }
}