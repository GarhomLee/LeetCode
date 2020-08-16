https://leetcode.com/problems/minimum-operations-to-make-array-equal/

solution1: Simulation + Greedy
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int minOperations(int n) {
        int low = 1, high = 2 * (n - 1) + 1;
        int res = 0;
        while (low < high) {
            res += (high - low) / 2;
            low += 2;
            high -= 2;
        }
        
        return res;
    }
}


solution2: Math
time complexity: O(1)
space complexity: O(1)

public int minOperations(int n) {
    int cnt = n / 2;
    return cnt * (cnt + n % 2);
}