https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/

idea: Sliding Window
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length, total = 0;
        for (int p : cardPoints) {
            total += p;
        }
        if (k == n) {
            return total;
        }
        
        int subSum = 0, minSum = Integer.MAX_VALUE;
        for (int left = 0, right = 0; right < n; right++) {
            subSum += cardPoints[right];
            if (right - left + 1 == n - k) {
                minSum = Math.min(minSum, subSum);
                subSum -= cardPoints[left++];
                
            }
        }
        
        return total - minSum;
    }
}