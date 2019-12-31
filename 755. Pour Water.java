https://leetcode.com/problems/pour-water/

解法一：Simulation
        根据定义，先往左走到可能的尽头，再往右走到可能的尽头，然后从右往左走到离位置K尽量近。
时间复杂度：O(n * V)
空间复杂度：O(1)

class Solution {
    public int[] pourWater(int[] heights, int V, int K) {
        int len = heights.length;
        while (V-- > 0) {
            int curr = K;
            /* first move left */
            while (curr - 1 >= 0 && heights[curr - 1] <= heights[curr]) {
                curr--;
            }
            
            /* then move right */
            while (curr + 1 < len && heights[curr] >= heights[curr + 1]) {
                curr++;
            }
            
            /* then move as close as possible to K */
            while (curr > K && heights[curr - 1] <= heights[curr]) {
                curr--;
            }
            
            /* update height at this position */
            heights[curr]++;
        }
        return heights;
    }
}