https://leetcode.com/problems/3sum-closest/

// 可以作为3Sum的follow-up。区别在于这题不需要找出相应的组合，只需找到closest sum。

// 1）由于不考虑index的影响，所以可以先sort
// 2）维护两个变量：closestSum，表示最终返回的结果；minDiff，用来找到closestSum
// 3）循环同上
// 4）在类2sum问题中，，维护一个临时变量sum表示当前三个数的和
// 5）如果target == sum，那么不可能有比它更接近target的数了，直接返回
// 6）比较target和nums[left] + nums[right]，left向右移动，right向左移动。此处可以不用避免搜索重复的数。同时更新closestSum和minDiff
// 7）Time complexity: O(n2);Space complexity: O(1)

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        Arrays.sort(nums);
        int closest = 0, minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (Math.abs(target - sum) < minDiff) {
                        closest = sum;
                        minDiff = Math.abs(target - sum);
                    }
                     
                    if (target == sum) return sum;
                    else if (target > sum) left++;
                    else right--;
                }
            }
        }
        return closest;
    }
}