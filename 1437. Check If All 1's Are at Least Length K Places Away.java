https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/

idea: Use a variable to record the nearest 1 to the left
time complexity: O(n)
space complexity: O(1)

class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int preIdx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            
            if (preIdx != -1 && i - preIdx <= k) {
                return false;
            }
            
            preIdx = i;
        }
        
        return true;
    }
}