https://leetcode.com/problems/keys-and-rooms/

// 解法一：Graph BFS，模版
//         利用Queue进行BFS，用HashSet记录遍历过的节点，最后检查遍历过的节点数visited.size()是否为所有节点数n。
// 时间复杂度：O(N+E)
// 空间复杂度：O(N)

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited.add(0);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next: rooms.get(curr)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }
        
        
        return visited.size() == n;
    }
}


// 解法二：Graph DFS，模版
// 时间复杂度：O(N+E)
// 空间复杂度：O(N)

class Solution {
    Set<Integer> visited = new HashSet<>();
    
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        dfs(rooms, 0);
        
        return visited.size() == n;
    }
    
    private void dfs(List<List<Integer>> rooms, int curr) {
        if (visited.contains(curr)) {
            return;
        }
        
        visited.add(curr);
        for (int next: rooms.get(curr)) {
            dfs(rooms, next);
        }
    }
}