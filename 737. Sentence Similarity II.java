https://leetcode.com/problems/sentence-similarity-ii/

solution1: Graph
time complexity: O(V + nE), V=vertices in pairs, E=edges in pairs, n=words1.length
space complexity: O(V)

class Solution {
    private boolean isSimilar(Map<String, Set<String>> graph, String w1, String w2) {
        if (!graph.containsKey(w1) || !graph.containsKey(w2)) {
            return false;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(w1);
        Set<String> seen = new HashSet<>();
        seen.add(w1);
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(w2)) {
                return true;
            }
            
            for (String next : graph.get(curr)) {
                if (seen.contains(next)) continue;
                
                queue.offer(next);
                seen.add(next);
            }
        }
        
        return false;
    }
    
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        
        // build the graph
        Map<String, Set<String>> graph = new HashMap<>();
        for (List<String> pair : pairs) {
            String w1 = pair.get(0), w2 = pair.get(1);
            graph.putIfAbsent(w1, new HashSet<>());
            graph.get(w1).add(w2);
            graph.putIfAbsent(w2, new HashSet<>());
            graph.get(w2).add(w1);
        }
        
        int n = words1.length;
        for (int i = 0; i < n; i++) {
            String w1 = words1[i], w2 = words2[i];
            if (!w1.equals(w2) && !isSimilar(graph, w1, w2)) {
                return false;
            }
        }
        
        return true;
    }
}


solution2: Disjoint Set
time complexity: O(V + n*α(E)) ≈ O(V + n), V=vertices in pairs, E=edges in pairs, n=words1.length
space complexity: O(V)

class Solution {
    class DisjointSet {
        int[] parent;
        int[] size;
        public DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        public void connect(int i, int j) {
            int ri = find(i), rj = find(j);
            if (ri == rj) return;
            if (size[ri] > size[rj]) {
                connect(rj, ri);
                return;
            }
            
            parent[ri] = rj;
            size[rj] += size[ri];
        }
        
        public int find(int i) {
            while (parent[i] != i) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            
            return i;
        }
        
        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }
    }
    
    private boolean isSimilar(DisjointSet ds, Map<String, Integer> map, String w1, String w2) {
        if (!map.containsKey(w1) || !map.containsKey(w2)) {
            return false;
        }
        
        int id1 = map.get(w1), id2 = map.get(w2);
        return ds.isConnected(id1, id2);
    }
    
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        
        int id = 0;
        Map<String, Integer> map = new HashMap<>();
        for (List<String> pair : pairs) {
            String w1 = pair.get(0), w2 = pair.get(1);
            if (!map.containsKey(w1)) {
                map.put(w1, id++);
            }
            if (!map.containsKey(w2)) {
                map.put(w2, id++);
            }
        }
        
        // build disjoint set
        DisjointSet ds = new DisjointSet(map.size());
        for (List<String> pair : pairs) {
            int id1 = map.get(pair.get(0)), id2 = map.get(pair.get(1));
            ds.connect(id1, id2);
        }
        
        int n = words1.length;
        for (int i = 0; i < n; i++) {
            String w1 = words1[i], w2 = words2[i];
            if (!w1.equals(w2) && !isSimilar(ds, map, w1, w2)) {
                return false;
            }
        }
        
        return true;
    }
}