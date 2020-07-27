https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/

idea: Math
time complexity: O(1)
space complexity: O(1)

class Solution {
    public int countOdds(int low, int high) {
        int ret = (high - low + 1) / 2;
        ret += low % 2 == 1 && high % 2 == 1 ? 1 : 0;
        return ret;
    }
}