https://leetcode.com/problems/shortest-unsorted-continuous-subarray/

// 解法一：Sort
//        复制原nums数组到copy，然后对copy排序，找到最左和最右的不同元素的位置，分别记为left和right，那么需要排序
//        的最小范围就是[left:right]，对应最短长度right - left + 1。如果两个数组没有差别，那么返回长度为0.
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] copy = new int[nums.length];
        System.arraycopy(nums, 0, copy, 0, nums.length);
        Arrays.sort(copy);
        int left = -1, right = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != copy[i]) {
                left = i;
                break;
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] != copy[i]) {
                right = i;
                break;
            }
        }
        return left == -1 ? 0 : right - left + 1;
    }
}

// 解法一的另一种写法：来自官方解答

public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] snums = nums.clone();
        Arrays.sort(snums);
        int start = snums.length, end = 0;
        for (int i = 0; i < snums.length; i++) {
            if (snums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return (end - start >= 0 ? end - start + 1 : 0);
    }
}


// 解法二：Stack，详见官方解答
// 时间复杂度：O(n)
// 空间复杂度：O(n)