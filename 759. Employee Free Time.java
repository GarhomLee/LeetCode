https://leetcode.com/problems/employee-free-time/

idea: Sweep Line + PriorityQueue
time complexity: O(m*n*log(m*n)), m=schedule.size(), n=schedule.get(i).size()
space complexity: O(m*n)

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        // if same absolute value, handle enter event first, and then exit event
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> 
                                                        Math.abs(a) == Math.abs(b) ? b - a : Math.abs(a) - Math.abs(b));
        for (List<Interval> list : schedule) {
            for (Interval inter : list) {
                pq.offer(inter.start);
                pq.offer(-inter.end);
            }
        }
        
        int count = 0, start = -1;
        while (!pq.isEmpty()) {
            int curr = pq.poll();
            if (count == 0 && start >= 0) {
                res.add(new Interval(start, Math.abs(curr)));
            }
            
            count += curr >= 0 ? 1 : -1;
            if (count == 0) {
                start = Math.abs(curr);
            }
        }
        return res;
    }
}