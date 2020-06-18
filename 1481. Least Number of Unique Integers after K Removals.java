https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/

idea: HashMap + PriorityQueue + Greedy

class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for (int num : map.keySet()) {
            int count = map.get(num);
            pq.offer(new int[]{num, count});
        }
        
        while (!pq.isEmpty() && k > 0) {
            int[] curr = pq.poll();
            int decr = Math.min(k, curr[1]);
            k -= decr;
            curr[1] -= decr;
            if (curr[1] > 0) {
                pq.offer(curr);
            }
        }
        
        return pq.size();
    }
}