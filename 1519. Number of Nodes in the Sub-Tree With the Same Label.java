https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/

idea: Graph + DFS
time complexity: O(V + E)
space complexity: O(V)

class Solution {
    private int[] dfs(List<Integer>[] graph, int curr, boolean[] visited, String s, int[] ret) {
        if (visited[curr]) {
            return new int[26];
        }
        
        visited[curr] = true;
        int[] count = new int[26];
        count[s.charAt(curr) - 'a']++;
        for (int next : graph[curr]) {
            int[] sub = dfs(graph, next, visited, s, ret);
            for (int i = 0; i < 26; i++) {
                count[i] += sub[i];
            }
        }
        
        
        ret[curr] = count[s.charAt(curr) - 'a'];
        return count;
    }
    
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        int[] ret = new int[n];
        dfs(graph, 0, new boolean[n], labels, ret);
        
        return ret;
    }
}