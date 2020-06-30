https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/

idea: Simulation + Bit Manipulation
time complexity: O(log n)
space complexity: O(1)

class Solution {
    public int numberOfSteps (int num) {
        int count = 0;
        while (num > 0) {
            if ((num & 1) > 0) {
                num -= 1;
            } else {
                num >>= 1;
                
            }
            count++;
        }
        
        return count;
    }
}