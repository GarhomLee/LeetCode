https://leetcode.com/problems/palindrome-permutation-ii/

idea: Backtracking
time complexity: O((n/2 + 1)!)
space complexity: O(n)

class Solution {
    Set<String> res = new HashSet<>();
    
    private void dfs(Map<Character, Integer> countMap, String curr, int total) {
        if (curr.length() == total) {
            res.add(curr);
            return;
        }
        
        for (char c : countMap.keySet()) {
            if (countMap.get(c) % 2 == 1) return;
            if (countMap.get(c) == 0) continue;
            
            countMap.put(c, countMap.get(c) - 2);
            dfs(countMap, c + curr + c, total);
            countMap.put(c, countMap.get(c) + 2);
        }
    }
    
    public List<String> generatePalindromes(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        
        int total = s.length();
        if (total % 2 == 1) {
            for (char c : countMap.keySet()) {
                countMap.put(c, countMap.get(c) - 1);
                dfs(countMap, c + "", total);
                countMap.put(c, countMap.get(c) + 1);
            }
        } else if (total % 2 == 0) {
            for (char c : countMap.keySet()) {
                if (countMap.get(c) < 2) {
                    return new ArrayList<String>();
                }
                
                countMap.put(c, countMap.get(c) - 2);
                dfs(countMap, c + "" + c, total);
                countMap.put(c, countMap.get(c) + 2);
            }
        }
        
        return new ArrayList<String>(res);
    }
}