https://leetcode.com/problems/wiggle-subsequence/

// 总体思路：DP问题。
//         状态函数：有两个，dpUp[i]表示nums[i]处于上升位时包含nums[i]在内的最长wiggle subsequence长度；
//                 dpDown[i]表示nums[i]处于下降位时包含nums[i]在内的最长wiggle subsequence长度。
//         状态转移方程：搜索nums[i]之前的所有数nums[j]，如果nums[j] < nums[i]，说明nums[i]处于上升位，而需要
//                 用到nums[j]处于下降位时的信息，所以更新dpUp[i]为dpDown[j] + 1；同理，如果nums[j] > nums[i]，
//                 说明nums[i]处于下降位，而需要用到nums[j]处于上升位时的信息，所以更新dpDown[i]为dpUp[j] + 1
//         初始值：所有dpUp[i]和dpDown[i]都初始化为1.
// 时间复杂度：O(n^2)，n = nums.length
// 空间复杂度：O(n)，n = nums.length

class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) return nums.length;
        
        int[] dpUp = new int[nums.length];
        int[] dpDown = new int[nums.length];
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            dpUp[i] = 1;
            dpDown[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) dpUp[i] = Math.max(dpUp[i], dpDown[j] + 1);
                if (nums[j] > nums[i]) dpDown[i] = Math.max(dpDown[i], dpUp[j] + 1);
            }
            maxLen = Math.max(dpUp[i], dpDown[i]);
        }
        return maxLen;
    }
}

// 优化：时间复杂度降为O(n)，one-pass
//     跟O(n^2)时间的方法的不同之处在于，状态方程含义不同：只有当nums[i] > nums[i - 1]，即nums[i]处于上升位时，
//     up[i]才会更新，表示nums[i]处于上升位时包含nums[i]在内的最长wiggle subsequence长度，【否则up[i]没有实际意义】，
//     且只需要更新为up[i] = up[i - 1]；对于down[i]同理。
//     另外，状态转移方程中，up[i]只与up[i - 1]或down[i - 1]有关，down[i]同理，即只需要比较nums[i]和nums[i - 1]，
//     而不需要遍历nums[i]之前的所有数。

class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        up[0] = down[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                down[i] = down[i - 1];
                up[i] = up[i - 1];
            }
        }
        return Math.max(down[nums.length - 1], up[nums.length - 1]);
    }
}

// 继续优化：空间复杂度降为O(1)
//         可以观察到，只有当nums[i] > nums[i - 1]时才需要更新up，且只依赖于当前down，对于nums[i] < nums[i - 1]同理，
//         所以可以只用两个变量，而不需要用数组。

class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) return nums.length;

        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) up = down + 1;
            else if (nums[i] < nums[i - 1]) down = up + 1;
        }
        return Math.max(down, up);
    }
}