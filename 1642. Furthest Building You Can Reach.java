https://leetcode.com/problems/furthest-building-you-can-reach/

idea: minHeap. Refer to: https://leetcode.com/problems/furthest-building-you-can-reach/discuss/918515/JavaC%2B%2BPython-Priority-Queue
time complexity: O(n log k), n=heights.length, k=ladders
space complexity: O(k)

class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i < n; i++) {
            int diff = heights[i] - heights[i - 1];
            if (diff <= 0) continue;
            
            pq.offer(diff);
            if (pq.size() > ladders) {
                int smallestDiff = pq.poll();
                if (bricks < smallestDiff) {
                    return i - 1;
                }
                bricks -= smallestDiff;
            }
        }
        
        return n - 1;
    }
}