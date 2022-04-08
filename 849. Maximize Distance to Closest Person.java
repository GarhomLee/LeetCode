https://leetcode.com/problems/maximize-distance-to-closest-person/

idea: Two Pointer
    Record the previous occupied position prevIdx.
time comp: O(n)
space comp: O(1)

class Solution {
    public int maxDistToClosest(int[] seats) {
        int n = seats.length, prevIdx = -1, firstIdx = -1;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                if (firstIdx == -1) {
                    firstIdx = i;
                } else { 
                    // only update max for the second 
                    max = Math.max(max, (i - prevIdx) / 2);
                }
                prevIdx = i;                
            }
        }
        
        max = Math.max(max, firstIdx);  // the leftmost 1
        max = Math.max(max, n - 1 - prevIdx);  // the rightmost 1
        return max;
    }
}