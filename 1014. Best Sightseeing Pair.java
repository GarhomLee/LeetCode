https://leetcode.com/problems/best-sightseeing-pair/

// 思路：Dynamic Programming??? 参考答案：https://leetcode.com/problems/best-sightseeing-pair/discuss/260850/JavaC%2B%2BPython-One-Pass
//         维护变量max，表示当前搜索到的最大的score；变量curr，表示当前元素左边范围的所有元素中“辐射”到当前位置时的最大值。
//         当搜索到新的元素n，利用“辐射”的最大值curr和当前元素n的和更新max。
//         然后，更新“辐射”的最大值curr为curr和当前元素n两者中的较大值，同时需要减去1，表示当“辐射”到达下一个元素时的值。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int maxScoreSightseeingPair(int[] A) {
        int max = 0, curr = 0;
        for (int n: A) {
            max = Math.max(max, curr + n);
            curr = Math.max(curr, n) - 1;
        }
        
        return max;
    }
}