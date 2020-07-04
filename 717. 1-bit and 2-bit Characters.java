https://leetcode.com/problems/1-bit-and-2-bit-characters/

time complexity: O(n)
space complexity: O(1)

class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length, i = 0;
        for (; i + 1 < n; i++) {
            if (bits[i] == 1) {
                i++;
            }
        }
        
        return i == n - 1 && bits[i] == 0;
    }
}