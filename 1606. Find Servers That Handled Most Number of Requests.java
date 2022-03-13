https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/

idea: TreeSet + HashMap + PriorityQueue
    TreeSet: available servers;
    HashMap: occupied servers with request id;
    PriorityQueue: sweeping line to determine if the request starts or ends
time comp: O(n log n + k log k)
space comp: O(n + k)

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> avail = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            avail.add(i);
        }
        Map<Integer, Integer> occupied = new HashMap<>();   // request i -> server j
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] count = new int[k];
        int maxCount = 0;
        
        for (int i = 0; i < arrival.length; i++) {
            pq.offer(new int[]{arrival[i], 1, i});  // {startTime, startMark, request i}
            pq.offer(new int[]{arrival[i] + load[i], -1, i});   // {endTime, endMark, request i}
        }
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            
            if (curr[1] == 1) { // request start
                if (avail.isEmpty()) continue;
                
                Integer server = avail.ceiling(curr[2] % k);
                if (server == null) {
                    server = avail.first();
                }
                avail.remove(server);
                occupied.put(curr[2], server);
                count[server]++;
                maxCount = Math.max(maxCount, count[server]);
            } else {    // request end
                if (!occupied.containsKey(curr[2])) continue;
                int server = occupied.get(curr[2]);
                occupied.remove(curr[2]);
                avail.add(server);
            }
        }
        
        
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxCount) {
                ret.add(i);
            }
        }
        return ret;
    }
}