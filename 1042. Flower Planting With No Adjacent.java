https://leetcode.com/problems/flower-planting-with-no-adjacent/

solution1: Brute force graph, iteration
    Given the constraints that each node has no more than 3 connected paths, we can
    simply search all 4 possible colors and find the first available color.
time complexity: O(V+E)
space complexity: O(V)

class Solution {
    public int[] gardenNoAdj(int N, int[][] paths) {
        int[] res = new int[N];
        List<Integer>[] graph = new List[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : paths) {
            int u = edge[0] - 1, v = edge[1] - 1;
            graph[u].add(v);
            graph[v].add(u);
        }
        
        for (int curr = 0; curr < N; curr++) {
            if (res[curr] != 0) continue;
            
            boolean[] usedColor = new boolean[5];
            for (int next : graph[curr]) {
                if (res[next] != 0) {
                    usedColor[res[next]] = true;
                }
            }
            for (int color = 1; color <= 4; color++) {
                if (!usedColor[color]) {
                    res[curr] = color;
                    break;
                }
            }
        }
        
        return res;
    }
}



solution2: Brute force graph, DFS
time complexity: O(V+E)
space complexity: O(V)

class Solution {
    private void dfs(int curr, List<Integer>[] graph, int[] res) {
        Set<Integer> usedColor = new HashSet<>();
        for (int next : graph[curr]) {
            if (res[next] != 0) {
                usedColor.add(res[next]);
            }
        }
        
        res[curr] = 0;
        for (int color = 1; color <= 4; color++) {
            if (!usedColor.contains(color)) {
                res[curr] = color;
                break;
            }
        }
        
        for (int next : graph[curr]) {
            if (res[next] == 0) {
                dfs(next, graph, res);
            }
        }
    }
    
    public int[] gardenNoAdj(int N, int[][] paths) {
        int[] res = new int[N];
        List<Integer>[] graph = new List[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : paths) {
            int u = edge[0] - 1, v = edge[1] - 1;
            graph[u].add(v);
            graph[v].add(u);
        }
        
        for (int i = 0; i < N; i++) {
            dfs(i, graph, res);
        }
        
        return res;
    }
}