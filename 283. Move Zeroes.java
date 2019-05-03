https://leetcode.com/problems/move-zeroes/

// 1）维护left和right两个pointer。其中left指向左边第一个0，right指向第一个未被扫描的数，left和right之间都是扫描过的0，left左边是所有扫描过的被交换过去的非0数。
// 2）如果right是0，继续向前
// 3）如果right非0，交换left和right，然后left和right都向前一步
// 4）时间复杂度：O(n)；空间复杂度：O(1)

class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] == 0) right++;
            else swap(nums, right++, left++);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}