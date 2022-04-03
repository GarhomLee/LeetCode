https://leetcode.com/problems/single-threaded-cpu/

idea: Sort + PriorityQueue
    First, sort by enqueueTime
    Then, update boundary time to include all tasks that are able to start.
    Add all these available tasks to PriorityQueue, where the priority is the processingTime.
time comp: O(n log n)
space comp: O(n)

class Solution {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new int[]{i, tasks[i][0], tasks[i][1]});   // {id, enqueueTime, processingTime}
        }
        Collections.sort(list, (a, b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] == b[2] ? a[0] - b[0] : a[2] - b[2]);
        pq.offer(list.get(0));
        int[] ret = new int[n];
        
        int retIdx = 0, listIdx = 1;
        int time = list.get(0)[1];
        while (!pq.isEmpty() || listIdx < n) {
            if (pq.isEmpty()) {
                pq.offer(list.get(listIdx++));
            }
            
            int[] curr = pq.poll();
            ret[retIdx++] = curr[0];
            
            time = Math.max(time, curr[1]) + curr[2];
            while (listIdx < n && list.get(listIdx)[1] <= time) {
                pq.offer(list.get(listIdx++));
            }
        }
        
        return ret;
    }
}