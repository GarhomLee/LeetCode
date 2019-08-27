https://leetcode.com/problems/summary-ranges/

// 思路：常规遍历，two pointers
//         遍历nums数组，用两个指针left和right指示当前连续数列的窗口的左右边缘，nums[left:right]是连续数列。
//         step1: 将左边缘nums[left]加入String s
//         step2: right指针从left位置开始，不断寻找直到到达了nums数组边界，或者nums[right]和nums[right + 1]
//                 不连续。
//         step3: 判断窗口所含元素个数，即left和right是否为同一个位置。如果不是，说明窗口左右边缘元素不同，根据题意
//                 需要加入"->"和nums[right]。否则，什么都不用做。
//         step4: 将当前窗口转化成的String s加入res列表，同时更新left为right+1.
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int left = 0;
        while (left < nums.length) {
            String s = nums[left] + "";
            int right = left;
            while (right < nums.length - 1 && nums[right] == nums[right + 1] - 1) {
                right++;
            }
            if (left != right) {
                s += "->" + nums[right];
            }
            res.add(s);
            left = right + 1;
        }
        
        return res;
    }
}