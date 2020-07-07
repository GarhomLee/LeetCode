https://leetcode.com/problems/synonymous-sentences/

idea: HashMap + DisjointSet + Backtracking
time complexity: O(l + n + s*n), l=synonyms.size(), n=map.size(), s=split.length
space complexity: O(n + s), n=map.size(), s=split.length

class Solution {
    int id = 0;
    
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
            int rooti = find(i), rootj = find(j);
            if (size[rooti] > size[rootj]) {
                connect(rootj, rooti);
                return;
            }
            
            parent[rooti] = rootj;
            size[rootj] += size[rooti];
        }
        
        public int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }
        
        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }
    }
    
    private void dfs(String[] split, int idx, String[] sArr, Map<String, Integer> map, DisjointSet ds, List<String> res) {
        if (idx == split.length) {
            res.add(String.join(" ", sArr));
            return;
        }
        if (!map.containsKey(split[idx])) {
            sArr[idx] = split[idx];
            dfs(split, idx + 1, sArr, map, ds, res);
            return;
        }
        
        int ri = ds.find(map.get(split[idx]));
        for (String s : map.keySet()) {
            int rj = ds.find(map.get(s));
            if (ds.isConnected(ri, rj)) {
                sArr[idx] = s;
                dfs(split, idx + 1, sArr, map, ds, res);
            }
        }
    }
    
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        String[] split = text.split("\\s+");
        Map<String, Integer> map = new HashMap<>();
        for (List<String> list : synonyms) {
            String s1 = list.get(0), s2 = list.get(1);
            if (!map.containsKey(s1)) {
                map.put(s1, id++);
            }
            if (!map.containsKey(s2)) {
                map.put(s2, id++);
            }
        }
        
        DisjointSet ds = new DisjointSet(map.size());
        for (List<String> list : synonyms) {
            String s1 = list.get(0), s2 = list.get(1);
            ds.connect(map.get(s1), map.get(s2));
        }
        
        List<String> res = new ArrayList<>();
        dfs(split, 0, new String[split.length], map, ds, res);
        
        Collections.sort(res);
        return res;
    }
}