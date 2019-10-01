https://leetcode.com/problems/unique-number-of-occurrences/

// 思路：HashMap + HashSet
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: arr) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        Set<Integer> set = new HashSet<>(map.values());
        return map.size() == set.size();
    }
}