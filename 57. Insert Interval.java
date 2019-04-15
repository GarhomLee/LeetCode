// https://leetcode.com/problems/insert-interval/

// 56题的follow-up。根据56题思路，因为题目已经给定了排好序的list，所以应该是先插入到相应位置，然后merge。
// 1）注意corner cases：【如果原列表为空，那么需要先插入然后返回新列表】。
// 2）插入时需要注意，【先判断是不是插入到list最后一位】。
// 3）插入完成后，立即break，否则可能造成memory exceed limit。
// 4）按照56题的思路merge。

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> mergeList = new ArrayList<>();
        if (intervals == null || intervals.isEmpty()) {
            mergeList.add(newInterval);
            return mergeList;
        }
        if (newInterval == null) return intervals;
        
        for (int i = 0; i <= intervals.size(); i++) {
            if (i == intervals.size()) {
                intervals.add(i, newInterval);
                break;
            }
            if (intervals.get(i).start > newInterval.start) {
                intervals.add(i, newInterval);
                break;
            }
        }
        
        
        Interval curr = intervals.get(0);
        for (int i = 0; i < intervals.size(); i++) {
            if (curr.end < intervals.get(i).start) {
                mergeList.add(curr);
                curr = intervals.get(i);
            } else curr.end = Math.max(curr.end, intervals.get(i).end);
            if (i == intervals.size() - 1) mergeList.add(curr);
        }
        return mergeList;
    }
}