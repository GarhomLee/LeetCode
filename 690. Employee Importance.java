https://leetcode.com/problems/employee-importance/

// 解法一：BFS
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        
        
        
        int res = 0;
        Queue<Integer> queue = new LinkedList<>();
        if (map.containsKey(id)) {
            queue.offer(id);
        }
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            Employee e = map.get(curr);
            res += e.importance;
            for (int next : e.subordinates) {
                queue.offer(next);
            }
        }
        
        return res;
    }
}


解法二：DFS
时间复杂度：O(n)
空间复杂度：O(h)