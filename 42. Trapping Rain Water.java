https://leetcode.com/problems/trapping-rain-water/

// 属于two pointers问题。
// 1）维护两个pointer：left，right；同时维护两个变量：leftMax，表示当前能得到的最大height[left]；rightMax，表示当前能得到的最大height[right]
// 2）比较height[left]和height[right]，由于短板效应，最大盛水量由短板决定，所以先移动较小的height（极端情况：height从左至右递增）。
// 3）移动后，比较leftMax（或rightMax）和height[left]（或height[right]），并更新leftMax（或rightMax）为比较后的较大值，那么在这一步中可承载的水的unit即为leftMax - height[left]（或rightMax - height[right]）。
// 4）更新pointer，直至left和right相遇。

class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, res = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                res += leftMax - height[left++];
            } else {
                rightMax = Math.max(rightMax, height[right]);
                res += rightMax - height[right--];
            }
        }
        return res;
    }
}