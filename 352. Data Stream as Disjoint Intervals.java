https://leetcode.com/problems/data-stream-as-disjoint-intervals/

总体思路：找到interval的floor和ceiling，然后分类讨论。

class SummaryRanges {
    TreeSet<Interval> intervals;
    
    /** Initialize your data structure here. */
    public SummaryRanges() {
        intervals = new TreeSet(new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                if (i1.start != i2.start) return i1.start - i2.start;
                return i1.end - i2.end;
            }
        });
    }
    
    public void addNum(int val) {
        Interval curr = new Interval(val, val);
        Interval floor = intervals.floor(curr);
        Interval ceiling = intervals.ceiling(curr);
        if (floor == null && ceiling == null) {
            intervals.add(curr);
        } else if (ceiling == null) {
            if (floor.end < curr.start - 1) intervals.add(curr);
            else if (floor.end == curr.start - 1) floor.end++;
        } else if (floor == null) {
            if (ceiling.start > curr.end + 1) intervals.add(curr);
            else if (ceiling.start == curr.end + 1) ceiling.start--;
        } else {
            if (floor.end < curr.start - 1 && ceiling.start > curr.end + 1) intervals.add(curr);
            else if (floor.end < curr.start - 1){  // {Mistake 1}
                if(ceiling.start == curr.end + 1) ceiling.start--;
                //else do nothing;  // {Correction 1}
            }
            else if (ceiling.start > curr.end + 1){  // {Mistake 1}
                if (floor.end == curr.start - 1) floor.end++;
                //else do nothing;  // {Correction 1}
            }
            else if (!floor.equals(ceiling)) {
                floor.end = ceiling.end;
                intervals.remove(ceiling);
            }
        }
    }
    
    public int[][] getIntervals() {
        int[][] res = new int[intervals.size()][2];
        int i = 0;
        for (Interval interval: intervals) {
            res[i][0] = interval.start;
            res[i][1] = interval.end;
            i++;
        }
        return res;
    }
    
    class Interval {
        int start;
        int end;
        public Interval(int s, int e) {
            start = s;
            end = e;
        }
        public boolean equals(Interval other) {
            if (other == null) return false;
            return this.start == other.start && this.end == other.end;
        }
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */

 优化：根据结果操作来分类