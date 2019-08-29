https://leetcode.com/problems/degree-of-an-array/

// 思路：HashMap，Two-pass
//         HashMap的key为nums数组中出现的数字，value为相关的信息组成的3个元素的数组，包含该数字出现的第一个位置、最后
//         一个位置、出现的次数。
//         第一次遍历：构建HashMap。
//         第二次遍历：根据HashMap的信息，找到出现次数最多的元素所能构成的最小窗口。
// 时间复杂度：O(N)
// 空间复杂度：O(N)
// 犯错点：1.思路错误：不能在第一次遍历的时候就更新minLen，因为是取最小值，而maxCount==1的时候minLen就会更新到最小值1，
//             导致结果永远为1。因此，更新minLen需要在第一次遍历完nums数组后，再一次遍历HashMap。

class Solution {
    public int findShortestSubArray(int[] nums) {
        int maxCount = 0, minLen = Integer.MAX_VALUE;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new int[]{i, i, 0});
            }
            
            int[] info = map.get(nums[i]);
            info[1] = i;
            info[2] += 1;
            if (info[2] >= maxCount) {
                maxCount = info[2];
                //minLen = Math.min(minLen, info[1] - info[0] + 1);  // {Mistake 1}
            }
            
        }
        
        for (int[] info: map.values()) {
            if (info[2] == maxCount) {
                minLen = Math.min(minLen, info[1] - info[0] + 1);
            } 
        }  // {Correction 1}
        
        return minLen;
    }
}