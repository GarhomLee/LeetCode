https://leetcode.com/problems/jump-game/

// 有四种解法：backtracking, DP top-down, DP bottom-up, greedy。其中greedy method时间复杂度可以达到O(n)。

// greedy method也分两种：
// 解法一：bottom up，这个方法和第45题有共同之处
// 1）维护farthest表示从当前位置能跳到的最远位置
// 2）遍历数组，更新farthest
// 3）在遍历的过程中，判断是否到达farthest。如果是，且已经位于最后一个元素，那么可以达到，返回true。
//     否则，仍处于数组中间，那么意味着不能到达最后元素，返回false。

class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length < 2) return true;
        int farthest = -1;
        for (int i = 0; i < nums.length; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest == i) return i == nums.length - 1;
        }
        return true;
    }
}

// 解法二：top down，从后往前遍历
// 1）维护jumpPos表示可以跳达最后的第一个位置。
// 2）如果从当前位置能跳到jumpPos，那么再从jumpPos跳就能跳到最后，所以从当前位置也可以跳到最后，因此更新jumpPos为当前位置
// 3）看第0位是不是jumpPos，即从第0位能不能跳到最后

class Solution {
    public boolean canJump(int[] nums) {
        int jumpPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= jumpPos) jumpPos = i;
        }
        return jumpPos == 0;
    }
}