https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/

idea: Math
time complexity: O(n + k)
space complexity: O(k)

class Solution {
    public boolean canArrange(int[] arr, int k) {
        int[] count = new int[k];
        for (int num : arr) {
            num %= k;
            if (num < 0) {
                // transform the negative mod to a positive one
                num += k;
            }
            count[num]++;
        }
        
        if (count[0] % 2 != 0) {
            return false;
        }
        
        for (int i = 1; i < k; i++) {
            if (count[i] != count[k - i]) {
                return false;
            }
        }
        
        return true;
    }
}