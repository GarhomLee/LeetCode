// https://leetcode.com/problems/merge-intervals/

// 总体思路：先排序，然后merge。
// 1）用Collections.sort()，自己构建一个comparator（有三种方法，下面展示了两种），根据start由大到小排序。
// 2）维护一个curr，表示当前merge到的interval。
// 3）如果第i个interval和curr不重叠，那么把curr加入list，然后重置为当前的interval。
// 4）如果重叠，【取curr.end和intervals.get(i).end的较大值】，如[1,5]和[2,3]，merge完还是[1,5]
// 3）如果遍历到最后一个interval，那么就把curr加入list表示遍历完成。

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
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> mergeList = new ArrayList<>();
        if (intervals.isEmpty()) return mergeList;
         /* Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        }); */
        
        Collections.sort(intervals, (Interval i1, Interval i2) -> i1.start - i2.start);
        
        Interval curr = intervals.get(0);
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).start > curr.end) {
                mergeList.add(curr);
                curr = intervals.get(i);
            } else curr.end = Math.max(intervals.get(i).end, curr.end);
            if (i == intervals.size() - 1) mergeList.add(curr);
        }
        return mergeList;
    }
}


二刷：

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        List<int[]> list = new ArrayList<>();
        int[] candidate = intervals[0];
        
        
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            int start = Math.max(candidate[0], interval[0]), end = Math.min(candidate[1], interval[1]);
            if (start <= end) {
                candidate[0] = Math.min(candidate[0], interval[0]);
                candidate[1] = Math.max(candidate[1], interval[1]);                
            } else {
                list.add(candidate);
                candidate = interval;
            }
        }
        
        list.add(candidate);
        return list.toArray(new int[0][0]);
    }
}