https://leetcode.com/problems/binary-number-with-alternating-bits/

idea: Bit Manipulation
time complexity: O(log n)
space complexity: O(1)

class Solution {
    public boolean hasAlternatingBits(int n) {
        int bit = n & 1;
        while (n > 0) {
            if ((n & 1) != bit) {
                return false;
            }
            bit = 1 - bit;
            n = n >> 1;
        }
        return true;
    }
}