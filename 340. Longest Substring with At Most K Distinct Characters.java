https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/

对比：159. Longest Substring with At Most Two Distinct Characters

思路：Sliding Window

时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s.isEmpty()) {
            return 0;
        }
        
        int maxLen = 0;
        int[] count = new int[128];
        int total = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            char cr = s.charAt(right);
            if (count[cr] == 0) {
                total++;
            }
            count[cr]++;
            
            while (total > k && left <= right) {
                char cl = s.charAt(left++);
                count[cl]--;
                if (count[cl] == 0) {
                    total--;
                }
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}