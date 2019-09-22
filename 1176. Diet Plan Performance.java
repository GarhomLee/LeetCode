https://leetcode.com/problems/diet-plan-performance/

// 思路：Sliding window
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int sum = 0, point = 0;
        for (int i = 0; i < calories.length; i++) {
            sum += calories[i];
            if (i >= k - 1) {
                if (sum < lower) {
                    point--;
                } else if (sum > upper) {
                    point++;
                }
                sum -= calories[i - k + 1];
            }
        }
        
        return point;
    }
}