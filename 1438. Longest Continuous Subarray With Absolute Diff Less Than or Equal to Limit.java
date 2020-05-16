https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/

idea: Sliding Window + Monotonic Queue
    Use two pointers, left and right, to indicate the left and right boundary 
    of the sliding window.
    Use a increasingly monotonic queue to keep track of the minimum value in the 
    window. Use a decreasingly monotonic queue to keep track of the maximum value 
    in the window.
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> incrDeque = new LinkedList<>();
        Deque<Integer> decrDeque = new LinkedList<>();
        int maxLen = 0;
        for (int left = 0, right = 0; right < nums.length; right++) {
            while (!incrDeque.isEmpty() && nums[incrDeque.getLast()] > nums[right]) {
                incrDeque.removeLast();
            }
            incrDeque.addLast(right);
            
            while (!decrDeque.isEmpty() && nums[decrDeque.getLast()] < nums[right]) {
                decrDeque.removeLast();
            }
            decrDeque.addLast(right);
            
            while (nums[decrDeque.getFirst()] - nums[incrDeque.getFirst()] > limit) {
                if (incrDeque.getFirst() == left) {
                    incrDeque.removeFirst();
                }
                if (decrDeque.getFirst() == left) {
                    decrDeque.removeFirst();
                }
                
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}