https://leetcode.com/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list/

idea: HashMap + HashSet
time complexity: O(n^2 * m), n=favoriteCompanies.size(), m=favoriteCompanies.get(0).size()
space complexity: O(n * m)

class Solution {
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        Map<String, Integer> idMap = new HashMap<>();
        int id = 0;
        for (List<String> list : favoriteCompanies) {
            for (String s : list) {
                idMap.putIfAbsent(s, id++);
            }
        }
        
        int n = favoriteCompanies.size();
        Set<Integer>[] sets = new Set[n];
        for (int i = 0; i < n; i++) {
            sets[i] = new HashSet<>();
            for (String s : favoriteCompanies.get(i)) {
                sets[i].add(idMap.get(s));
            }
        }
        
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean isSubset = false;
            for (int j = 0; j < n && !isSubset; j++) {
                if (i == j || sets[i].size() > sets[j].size()) continue;
                
                isSubset |= sets[j].containsAll(sets[i]);
            }
            
            if (!isSubset) {
                res.add(i);
            }
        }
        
        
        return res;
    }
}