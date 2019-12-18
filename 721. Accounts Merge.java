https://leetcode.com/problems/accounts-merge/

思路：UnionFind + HashMap + HashSet

时间复杂度：O(n), n=total number of emails
空间复杂度：O(n), n=total number of emails

class Solution {
    int id = 0;
    
    class UnionFind {
        int[] parent;
        int[] size;
        public UnionFind(int n) {
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
            }
            
            parent[ri] = rj;
            size[rj] += size[ri];
        }
        
        public int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            
            return i;
        }
    }
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        /* assign id to each email */
        Map<String, Integer> emailToId = new HashMap<>(); // map email->id
        Map<Integer, String> idToOwner = new HashMap<>(); // map id->owner
        for (List<String> list : accounts) {
            String owner = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String email = list.get(i); // get the email
                
                if (!emailToId.containsKey(email)) {
                    emailToId.put(email, id++); // associate an email with an int id
                }
                
                idToOwner.putIfAbsent(emailToId.get(email), owner); // associate an id with an owner
            }
        }
        
        /* union emails by id */
        UnionFind uf = new UnionFind(id);
        for (List<String> list : accounts) {
            String sentinel = list.get(1);
            
            for (int i = 1; i < list.size(); i++) {
                String email = list.get(i);
                uf.connect(emailToId.get(sentinel), emailToId.get(email));
            }
        }
        
        /* find the emails within the same group/component */
        Map<Integer, Set<String>> components = new HashMap<>(); // map id->emails in the same component
        for (List<String> list : accounts) {
            String owner = list.get(0);
            String sentinel = list.get(1);
            int rootId = uf.find(emailToId.get(sentinel));
            for (int i = 1; i < list.size(); i++) {
                String email = list.get(i); // get the email
                components.putIfAbsent(rootId, new HashSet<>());
                components.get(rootId).add(email); // associate emails in the same component with their sentinel id
            }
        }
        
        /* finalize the result */
        List<List<String>> res = new ArrayList<>();
        for (int id : components.keySet()) {
            List<String> list = new ArrayList<>(components.get(id));
            Collections.sort(list);
            String owner = idToOwner.get(id);
            list.add(0, owner);
            res.add(list);
        }
        
        
        return res;
    }
}