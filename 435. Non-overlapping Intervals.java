https://leetcode.com/problems/non-overlapping-intervals/

// 对比：56. Merge Intervals
// 思路：Greedy + Sort。转化为求最大的non-overlapping intervals的个数。
//         关键点：【只需要按end排序】。
//         以end作为分割点，是一种“贪心”的策略。假设当前分割点end为4，排序中下一个end为6，共有4个interval：
//         [1,6],[3,6],[4,6],[5,6]，无论这几个interval之间怎么排序，在当前分割点end和下一个end之间都只能
//         最多放下1个interval，当且仅当至少有一个interval的start >= 当前分割点end时。而start < 当前分割点end，
//         即会覆盖前一个interval，就不会被考虑。然后，更新分割点为下一个end。
//         在不断更新分割点的过程中，记录non-overlapping intervals的个数count，就可以得到最大的non-overlapping 
//         intervals的个数。
//         最后结果为intervals.length - count。
// 时间复杂度：O(n log n)
// 时间复杂度：O(1)
// 犯错点：1.思路错误：先按start排序再按end排序是不行的，这样会造成[[1,6],[2,3],[3,4],[4,5]]中后3个被删除。
//         2.边界条件错误：intervals.length == 0的情况要特殊考虑，因为要保证将end赋值为intervals[0][1]

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // {Mistake 2}
        if (intervals.length == 0) {  // {Correction 2}
            return 0;
        }
        
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[1] - i2[1];
            }
        });

        int count = 1;  // count of non-overlapping intervals
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                count++;
                end = intervals[i][1];
            }
        }
        
        return intervals.length - count;
    }
}


二刷：
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            return a[1] == b[1] ? a[0] - b[0] : a[1] - b[1];
        });
        int[] curr = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        int ret = 0;    // count of overlapped ones that need to remove
        for (int[] interval: intervals) {
            int start = Math.max(curr[0], interval[0]), end = Math.min(curr[1], interval[1]);
            if (start >= end) {
                curr = interval;
            } else {
                ret++;
            }
        }
        
        return ret;
    }
}