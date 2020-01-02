https://leetcode.com/problems/all-paths-from-source-to-target/

解法一：BFS

class Solution {
    class Node {
        int vertex;
        List<Integer> list;
        public Node(int v, List<Integer> l) {
            vertex = v;
            list = l;
        }
    }
    
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int len = graph.length;
        List<List<Integer>> res = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, new ArrayList<Integer>()));
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            curr.list.add(curr.vertex);
            if (curr.vertex == len - 1) {
                res.add(curr.list);
            } else {
                for (int nextVertex : graph[curr.vertex]) {
                    queue.offer(new Node(nextVertex, new ArrayList<Integer>(curr.list)));
                }
            }
        }
        return res;
    }
}


解法二：Backtracking

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    
    private void dfs(int[][] graph, int curr, List<Integer> list) {
        list.add(curr);
        if (curr == graph.length - 1) {
            res.add(new ArrayList<>(list));
        } else {
            for (int next : graph[curr]) {
                dfs(graph, next, list);
            }
        }
        list.remove(list.size() - 1);
    }
    
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        dfs(graph, 0, new ArrayList<Integer>());
        
        return res;
    }
}


解法三：Recursion with Memoization