https://leetcode.com/problems/container-with-most-water/

// Two pointers
// 关键：当height[left] < height[right]时，left右移，反之更新right左移，因为根据短板原理，只有这样才有
//     可能更新后的面积比原面积大

class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, max = (right - left) * Math.min(height[left], height[right]);
        while (left < right) {
            if (height[left] < height[right]) left++;
            else right--;
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
        }
        return max;
    }
}