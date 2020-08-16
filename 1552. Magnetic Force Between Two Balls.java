https://leetcode.com/problems/magnetic-force-between-two-balls/

idea: Binary Search
time complexity: O(n log n)
space complexity: O(1)

class Solution {
    private int getBallCount(int[] position, int dist) {
        int n = position.length;
        int count = 0;
        int nextEnd = position[0];
        for (int i = 0; i < n; i++) {
            if (nextEnd <= position[i]) {
                count++;
                nextEnd = position[i] + dist;
            }
        }
        
        return count;
    }
    
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length;
        int low = 1, high = position[n - 1] - position[0];
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (getBallCount(position, mid) < m) {  // find the smallest dist with which the ball count is {strictly less than} m
                high = mid - 1;
            } else {
                low = mid + 1;
            }
            
        }
        
        return low - 1; // since this dist will have ball count m - 1, then dist - 1 is the max dist to get ball count == m
    }
}