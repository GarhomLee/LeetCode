https://leetcode.com/problems/largest-unique-number/

// 思路：HashMap
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int largestUniqueNumber(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: A) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        int maxNum = -1;
        for (int n: map.keySet()) {
            if (map.get(n) == 1) {
                maxNum = Math.max(maxNum, n);
            }
        }
        
        return maxNum;
    }
}