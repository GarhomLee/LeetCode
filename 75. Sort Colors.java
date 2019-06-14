https://leetcode.com/problems/sort-colors/

// 思路：简化版的Quick Sort，因为已经知道只有1，2，3三个数，所以可以直接确定pivot为1。
//      确定了pivot为1后，维护三个pointer：left指向最后一个0的下一个元素（即第一个1），mid指向最后一个1的下一个元素（即第一个未扫描到的元素），
//      right指向第一个2的前一个元素（即最后一个未扫描到的元素）。扫描空间为[mid:right]，当mid > right时扫描结束。
//      有3种情况：
//      1）nums[mid] > 1，即nums[mid] == 2，交换到right的位置，同时因为right被填充，所以right--。这时被交换到mid的元素大小不确定，所以mid不能动。
//      2）nums[mid] < 1，即nums[mid] == 0，交换到left的位置，同时因为left被填充，所以left++。这时，由于left原来指向的是第一个1，而这个1确定被交换到mid了，所以mid++。
//      3）nums[mid] == 1，直接mid++
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public void sortColors(int[] nums) {
        int left = 0, mid = 0, right = nums.length - 1;
        while (mid <= right) {
            if (nums[mid] > 1) {  // meaning nums[mid] == 2
                swap(nums, mid, right--);
            } else if (nums[mid] < 1) {  // meaning nums[mid] == 0
                swap(nums, left++, mid++);
            } else {  // meaning nums[mid] == 1
                mid++;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}