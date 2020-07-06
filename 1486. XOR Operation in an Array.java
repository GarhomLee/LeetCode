https://leetcode.com/problems/xor-operation-in-an-array/

idea: Simulation + Bit Manipulation
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int xorOperation(int n, int start) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            res ^= start + 2 * i;
        }
        
        return res;
    }
}