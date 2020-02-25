https://leetcode.com/problems/reconstruct-itinerary

// 思路：Graph + DFS
//      先构建Graph，用Map<String, PriorityQueue<String>>来实现，key为出发地，value为按字母排序后的到达地。
//      注意，虽然每次只加入from->to这条边，但是还是需要在graph中给to先加入一个空的PriorityQueue，避免出现graph.get(to)不存在的情况。
//      从"JFK"开始递归搜索。
//      利用while循环，每次拿出一个PriorityQueue的顶部元素作为下一次的出发地，进行递归搜索。
//      当PriorityQueue为空，【从后往前】把出发地加入res。
//      注意：只有当循环结束后才能把出发地加入res。
// 犯错点：1.题目要求只需从JFK开始topological sort
//        2.如果用for loop遍历PriorityQueue，不能同时从中remove元素。实际上，只需要用while loop从中poll顶部元素即可。

class Solution {
    List<String> res = new LinkedList();
    
    public List<String> findItinerary(List<List<String>> tickets) {
        /* graph construction */
        Map<String, PriorityQueue<String>> graph = new HashMap();
        for (int i = 0; i < tickets.size(); i++) {
            String from = tickets.get(i).get(0), to = tickets.get(i).get(1);
            if (!graph.containsKey(from)) graph.put(from, new PriorityQueue());
            if (!graph.containsKey(to)) graph.put(to, new PriorityQueue());  // avoid checking null
            graph.get(from).offer(to);
        }
        
        /*for (String from : graph.keySet()) {
            if (graph.containsKey(from) && !graph.get(from).isEmpty()) {
                dfs(graph, from);
            }
        }*/  // {Mistake 1: topological sort just need to start from "JFK"}
        dfs(graph, "JFK");  // {Correction 1}
        
        return res;
    }
    
    private void dfs(Map<String, PriorityQueue<String>> graph, String from) {
        /*for (String to: graph.get(from)) {
            graph.get(from).remove(to);
            dfs(graph, to);
        }*/  // {Mistake 2: if using for loop to iterate PriorityQueue while removing element from it, it will cause Concurrent Modification Error}
        while (!graph.get(from).isEmpty()) {
            String to = graph.get(from).poll();
            dfs(graph, to);
        }  // {Correction 2: use while loop to check if PriorityQueue is empty and then poll element from it}
        res.add(0, from);
    }
}


二刷：HashMap + PriorityQueue

class Solution {
    private void dfs(Map<String, PriorityQueue<String>> graph, String curr, List<String> res) {
        PriorityQueue<String> pq = graph.get(curr);
        while (!pq.isEmpty()) {
            String next = pq.poll();
            dfs(graph, next, res);
        }
        
        res.add(0, curr);
    }
    
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0), to = ticket.get(1);
            graph.putIfAbsent(from, new PriorityQueue<>());
            graph.putIfAbsent(to, new PriorityQueue<>());
            graph.get(from).offer(to);
        }
        
        List<String> res = new ArrayList<>();
        dfs(graph, "JFK", res);
        
        return res;
    }
}