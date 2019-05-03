https://leetcode.com/problems/3sum/

// 1）由于不考虑index的影响，所以可以先sort
// 2）先定三个数中的第一个数，剩下的两个数reduce为类2sum的问题。除了index == 0，第一个数的选取不能有重复
// 3）类2sum问题中，维护left和right两个pointer初始化为start + 1和nums.length - 1
// 4）比较target和nums[left] + nums[right]
// 5）left向右移动，right向左移动，注意避免搜索重复的数
// 6）Time complexity: O(n2);Space complexity: O(1)

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (nums == null || nums.length < 3) return resultList;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i == 0 || nums[i] != nums[i - 1]) {
                int target = 0 - nums[i], left = i + 1, right = nums.length - 1;
                while (left < right) {
                    if (target > nums[left] + nums[right]) left++;
                    else if (target < nums[left] + nums[right]) right--;
                    else {
                        resultList.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[left], nums[right])));
                        do left++; while (left < right && nums[left] == nums[left - 1]); 
                        do right--; while (left < right && nums[right] == nums[right + 1]);
                    }
                }
            }
        }
        return resultList;
    } 
}