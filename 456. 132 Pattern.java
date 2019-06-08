https://leetcode.com/problems/132-pattern/

// 总体思路：利用额外的空间来维护132 pattern的信息。
//         利用min数组来维护"1"的信息。先遍历一次nums数组，min[i]表示nums[0:i]的局部最小值。
//         利用Stack来维护"2"的信息。从后往前遍历nums数组，对于index i，维持stack.top具有"2"的性质，即stack.top>min[i]，所以将所有较小的元素pop出来。
//         从后往前遍历nums数组寻找合适的"3"。首先需要满足stack不为空。同时，因为stack存储的元素都在nums[i]右边，且已经满足stack.top>min[i]，所以需要nums[i]>stack.top。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        
        /* construct min array, which stores the "1"s in the "132" pattern */
        int[] min = new int[nums.length];  // min[i] indicates local min value in nums[0:i]
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(nums[i], min[i - 1]);
        }
        
        /* use Stack to find 132 pattern */
        Stack<Integer> stack = new Stack();  // Stack stores the "2"s in the "132" pattern
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == min[i]) continue;  // this means nums[i] is the local min value in nums[0:i], which cannot be "2" or "3", thus skip it
            
            while (!stack.isEmpty() && min[i] >= stack.peek()) {  // {Correction 1: first make sure that all values in the Stack are "2"s which are required to be greater than "1"}
                stack.pop();
            } 
            
            if (!stack.isEmpty() && stack.peek() < nums[i]) {  // find nums[i] as the "3" in the "132" pattern
                return true;
            }
            stack.push(nums[i]);
        }
        return false;
    }
}