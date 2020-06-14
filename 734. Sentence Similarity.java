https://leetcode.com/problems/sentence-similarity/

idea: HashMap
time complexity: O(p + n*l), p=pairs.size(), n=words1.length, l=words1[i].length
space complexity:O(p)

class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        
        Map<String, Set<String>> simMap = new HashMap<>();
        for (List<String> pair : pairs) {
            String w1 = pair.get(0), w2 = pair.get(1);
            simMap.putIfAbsent(w1, new HashSet<>());
            simMap.get(w1).add(w2);
            simMap.putIfAbsent(w2, new HashSet<>());
            simMap.get(w2).add(w1);
        }
        
        int n = words1.length;
        for (int i = 0; i < n; i++) {
            String w1 = words1[i], w2 = words2[i];
            if (!w1.equals(w2) 
                && !simMap.getOrDefault(w1, new HashSet<>()).contains(w2) 
                && !simMap.getOrDefault(w2, new HashSet<>()).contains(w1)) {
                return false;
            }
        }
        
        return true;
    }
}