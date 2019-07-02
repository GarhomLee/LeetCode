https://leetcode.com/problems/array-nesting/

// 思路：Memoization
//      对nums数组每一个数，如果没有被标记过，那么以之为起点，进行题目给定的nesting遍历，类似走一个cycle。
//      在遍历时，不断标记经过的元素为-1，同时len++，直到回到起点，然后更新maxLen。
//      在同一个cycle的元素，从哪个元素开始遍历长度都一样，和起始位置无关，且不和别的cycle有联系，所以可以标记
//      遍历过的元素，后续遍历时跳过这些被标记的元素。道理和457. Circular Array Loop类似。
// 注意：如果不标记遍历过的元素，那么同一个cycle以不同元素为起始点都会走一次，会导致time limit exceed。
// 时间复杂度：O(n)，所有元素最多被遍历两次
// 空间复杂度：O(1)
// 犯错点：1.状态变化错误：如果while loop因为nums[curr] == i而跳出，说明nums[curr]也被访问了，需要标记为-1，
//          但因为没有进入到循环中，所以需要单独在循环结束后标记为-1。

class Solution {
    public int arrayNesting(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            /* skip visited elements, otherwise it will cause time limit exceed */
            if (nums[i] == -1) {
                continue;
            }

            int curr = i, len = 1;
            while (nums[curr] != i) {
                int next = nums[curr];
                nums[curr] = -1;
                curr = next;
                len++;
            }
            // {Mistake 1}
            nums[curr] = -1;  // {Correction 1: the while loop is terminated because nums[curr] == i where i != 1, thus nums[curr] should be assigned -1 because it is visited}
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }
}


// 另一种写法：while loop判断条件为标记数，见leetcode官方解答。