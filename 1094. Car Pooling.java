https://leetcode.com/problems/car-pooling/

idea: Sweeping Line
time comp: O(n log n)
space comp: O(n)

class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);  // {loc, start/end, #passenger}
        for (int[] trip: trips) {
            pq.offer(new int[]{trip[1], 1, trip[0]});
            pq.offer(new int[]{trip[2], -1, trip[0]});
        }
        
        int total = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            total += curr[1] * curr[2];
            if (total > capacity) return false;
        }
        
        return true;
    }
}