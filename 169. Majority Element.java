https://leetcode.com/problems/majority-element/

// 解法一：sort，取nums[nums.length / 2]。题目保证了majority element个数一定大于nums.length / 2，所以无论数组元素个数是奇数还是偶数，sort后的中间元素一定就是所求数

class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}

// 解法二：Boyer-Moore Voting Algorithm。
// 1）维护一个变量candidate，初始化为nums[0]；同时维护其对应的count，初始化为1
// 2）遍历数组，如果count == 0，那么candidate为空，所以将当前nums赋值为candidate，同时count = 1
// 3）如果遇到相同元素，count++；
// 4）如果遇到不同元素，candidate数量被抵消，count--。最后剩下的一定是有多余的未被抵消的candidate
// 5）时间复杂度：O(n)；空间复杂度：O(1)

class Solution {
    public int majorityElement(int[] nums) {
        int curr = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == curr) count++;
            else if (count == 0) {
                curr = nums[i];
                count++;
            } else count--;
        }
        return curr;
    }
}