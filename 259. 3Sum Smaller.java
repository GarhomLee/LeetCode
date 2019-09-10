https://leetcode.com/problems/3sum-smaller/

// 思路：Sort + Two pointers
//         step1:排序
//         step2:遍历nums数组，确定每一个triplet的第一个数为nums[i]，计算nums[i+1:len)有多少可以和nums[i]
//             形成总和小于target的triplet。
//             记target - nums[i]为temp，维护指针left和right。由于已经排好序，如果nums[left] + nums[right] < temp，
//             那么nums[left]和所有的nums[left+1:right]都小于temp，共有right-left个，全部加进res中，同时更新
//             left++。
//             如果nums[left] + nums[right] >= temp，说明nums[right]太大，所以左移right--。
//         step3:最后结果返回res。
// 时间复杂度：O(n^2)
// 空间复杂度：O(n) or O(1)

class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int res = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            int temp = target - nums[i];
            while (left < right) {
                if (nums[left] + nums[right] < temp) {
                    res += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return res;
    }
}