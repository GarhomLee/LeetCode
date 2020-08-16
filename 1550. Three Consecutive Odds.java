https://leetcode.com/problems/three-consecutive-odds/

idea: Brute Force (info cache)
time complexity: O(n)
space complexity: O(1)

class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int count = 0;
        for (int num : arr) {
            int curr = num % 2;
            if (curr == 0) {
                count = 0;
            } else {
                count++;
            }
            
            if (count >= 3) {
                return true;
            }
        }
        
        return false;
    }
}