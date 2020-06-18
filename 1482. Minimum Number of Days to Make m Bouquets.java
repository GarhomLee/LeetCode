https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/

idea: Binary Search
    -Search for the minimum days, so that any days greater than min can make 
     more than m bouquets with consecutive k flowers.
time complexity: O(n log m), n=bloomDay.length, m=max in bloomDay
space complexity: O(1)

class Solution {
    private int getBouquets(int[] bloomDay, int maxDay, int k) {
        int count = 0;
        for (int i = 0, flowers = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] > maxDay) {
                flowers = 0;
            } else {
                flowers++;
                if (flowers == k) {
                    flowers = 0;
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (m * k > n) {
            return -1;
        }
        
        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
        for (int d : bloomDay) {
            low = Math.min(low, d);
            high = Math.max(high, d);
        }
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (getBouquets(bloomDay, mid, k) >= m) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }
}