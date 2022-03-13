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


二刷：先找到合适的插入的位置；然后做merge


class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        boolean isInserted = false;

        // insert to the proper position before merging
        for (int[] interval: intervals) {
            if (!isInserted && newInterval[0] <= interval[0]) {
                list.add(newInterval);
                isInserted = true;
            }
            
            list.add(interval);
        }       
        
        // not inserted yet, should be added at the end
        if (!isInserted) {
            list.add(newInterval);
        }
        
        // do the merge
        return mergeIntervals(list);
    }
    
    private int[][] mergeIntervals(List<int[]> list) {
        List<int[]> ret = new ArrayList<>();
        int[] candidate = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int[] interval = list.get(i);
            if (interval[0] <= candidate[1]) {
                candidate[1] = Math.max(candidate[1], interval[1]);
            } else {
                ret.add(candidate);
                candidate = interval;
            }
        }
        
        ret.add(candidate);
        
        return ret.toArray(new int[0][0]);
    }
}