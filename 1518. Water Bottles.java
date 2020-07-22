https://leetcode.com/problems/water-bottles/

idea: Greedy
time complexity: O(log n)
space complexity: O(1)

class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int full = numBottles, empty = 0, max = 0;
        while (full + empty >= numExchange) {
            max += full;
            int next = (full + empty) / numExchange;
            empty = (full + empty) - next * numExchange;
            full = next;
        }
        
        return max + full;
    }
}