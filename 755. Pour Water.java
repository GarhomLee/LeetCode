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


solution2: Intuition
    First, check if it can go left; if so, update the height at correct position.
    If the check returns false, it then check if it can go right; if so, update the height at correct position.
    If it can go neither left nor right, just update the height of current position.
time complexity: O(n * V)
space complexity: O(1)

class Solution {
    private boolean goLeft(int[] heights, int start) {
        boolean left = false;
        int index = start - 1, lowest = index; 
        while (index >= 0 && heights[index] <= heights[index + 1]) {
            if (heights[index] < heights[index + 1]) {
                left = true;
                lowest = index;
            }
            index--;
        }
        
        if (left) {
            heights[lowest]++;
        }
        
        return left;
    }
    
    private boolean goRight(int[] heights, int start) {
        boolean right = false;
        int index = start + 1, lowest = index;
        while (index < heights.length && heights[index] <= heights[index - 1]) {
            if (heights[index] < heights[index - 1]) {
                right = true;
                lowest = index;
            }
            index++;
        }
        
        if (right) {
            heights[lowest]++;
        }
        
        return right;
    }
    
    public int[] pourWater(int[] heights, int V, int K) {
        int len = heights.length;
        
        while (V-- > 0) {
            boolean left = goLeft(heights, K), right = false;
            if (!left) {
                right = goRight(heights, K);
                if (!right) {
                    heights[K]++;
                }
            }
        }
        return heights;
    }
}