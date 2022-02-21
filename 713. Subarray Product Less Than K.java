https://leetcode.com/problems/subarray-product-less-than-k/

// 思路：sliding window
//         step0: 如果k==0，根据题目限制，乘积不可能比0小，所以返回0
//         step1: 维护变量：count，表示乘积小于k的subarray个数；product，表示当前subarray的乘积，初始化为1.
//                 维护指针：left，right，分别指向当前window的左右边缘。
//                 用right指针依次遍历nums数组的各个元素，用当前nums[right]更新product。
//         step2: 如果遍历过程中，出现product >= k的情况，那么需要移动window左边缘，使得product变小，直到
//                 product < k或者left > right即窗口没有元素。
//                 更新right++。
//                 那么，对于每个这样的nums[right]，能组成的【以nums[right]为最右边元素的合法subarray个数】
//                 就是窗口大小right-left，用来更新count。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }
        
        int count = 0, product = 1;        
        for (int left = 0, right = 0; right < nums.length; right++) {
            product *= nums[right];
            while (product >= k && left <= right) {
                product /= nums[left++];
            }            
            count += (right - left - 1);
        }
        return count;
    }
}