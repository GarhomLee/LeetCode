https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/

idea: Binary Search
time complexity: O(n log n)
space complexity: O(1)

class Solution {
    private int getDays(int[] weights, int capacity) {
        int days = 0, sum = capacity;
        for (int w : weights) {
            if (sum + w > capacity) {
                sum = 0;
                days++;
            }
            sum += w;
        }
        
        return days;
    }
    
    public int shipWithinDays(int[] weights, int D) {
        int low = Integer.MIN_VALUE, high = 0;
        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int days = getDays(weights, mid);
            if (days <= D) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }
}