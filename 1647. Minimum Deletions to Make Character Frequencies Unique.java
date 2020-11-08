https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/

idea: Sort + Greedy
time complexity: O(n log n)
space complexity: O(n) for sort

class Solution {
    public int minDeletions(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        int res = 0;
        Arrays.sort(count);

        // iterate from big to small count
        int curr = count[25] - 1;
        for (int i = 24; i >= 0; i--) {
            int diff = Math.max(count[i] - curr, 0);
            res += diff;
            count[i] -= diff;
            curr = Math.max(count[i] - 1, 0);
        }
        
        return res;
    }
}