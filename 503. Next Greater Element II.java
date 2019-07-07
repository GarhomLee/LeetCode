https://leetcode.com/problems/next-greater-element-ii/

// 思路：Stack，和496. Next Greater Element I的思路类似。
//      由于数组是循环数组，因此只需要增加一次遍历即可。
//      首先初始化整个结果数组res为-1。同时，利用array+top指针模拟Stack，存放的是【位置index】，应对重复数的情况。
//      第一次遍历，从左向右遍历nums数组，如果当前数nums[i]比Stack顶部元素nums[stack[top]]大，那么不断pop出顶部
//      元素直至nums[i]比顶部元素小或Stack为空，将nums[i]放进Stack。在这个过程中，对于结果数组的相应元素
//      res[stack[top]]就是nums[i]，符合题目的定义。
//      然后进行第二次遍历，此时Stack中可能存在有一些元素，因此可以利用之，实现循环数组的性质。过程和第一次遍历一样。
//      这次遍历是为了查漏。
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.第二次遍历nums数组时，不需要对当前位置i的结果res[i]设为-1，否则效果和第一次遍历一样，这样就没有循环数组的效果。

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        int[] stack = new int[nums.length + 10];
        int top = -1;
        for (int i = 0; i < nums.length; i++) {
            while (top != -1 && nums[stack[top]] < nums[i]) {
                res[stack[top--]] = nums[i];
            }
            stack[++top] = i;
        }
        for (int i = 0; i < nums.length; i++) {
            while (top != -1 && nums[stack[top]] < nums[i]) {
                res[stack[top--]] = nums[i];
            }
            //res[i] = -1;  // {Mistake 1}
            // {Correction 1}
            stack[++top] = i;
        }
       
        return res;
    }
}