https://leetcode.com/problems/kill-process/

idea: HashMap + BFS
time complexity: O(n)
space complexity: O(n)

class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        int n = pid.size();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int process = pid.get(i), parent = ppid.get(i);
            map.putIfAbsent(parent, new ArrayList<>());
            map.putIfAbsent(process, new ArrayList<>());
            map.get(parent).add(process);
        }
        
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(kill);
        res.add(kill);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (!map.containsKey(curr)) {}
            for (int next : map.get(curr)) {
                queue.offer(next);
                res.add(next);
            }
        }
        
        return res;
    }
}