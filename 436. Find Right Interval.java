https://leetcode.com/problems/find-right-interval/

// 总体思路：首先要理解题意。"right" interval是指start point大于或等于前一个interval的end point【而不是start point（这样就变成了overlap）】。
//         题目要求找到start point最小的right interval，符合Binary Search的性质。
//         利用TreeMap求ceiling的特性，key为所有intervals的start point，value为它们在intervals数组对应的index。因为题目规定start point不重复，
//         所以可以用Map。
//         先扫描一遍intervals数组构建TreeMap，然后再扫描intervals数组，对每个interval，根据题意，在TreeMap中找的是end point的ceiling，得到
//         对应的index。
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)
// 犯错点：1.求得ceiling后，ceiling为start point，想求的是index，所以还需要用get()得到index

class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int[] indices = new int[intervals.length];  // indicies[i] indicates the index of the interval "on the right" of intervals[i]
        TreeMap<Integer, Integer> map = new TreeMap<>();  // map the start point of an interval to its index in the intervals array
        /* build the mapping */
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i][0], i);
        }
        /* use binary search of TreeMap to find the right interval and store its start point in indices array */
        for (int i = 0; i < intervals.length; i++) {
            Integer ceiling = map.ceilingKey(intervals[i][1]);  // get the ceiling of the end point with binary search
            
            //indices[i] = ceiling == null? -1 : ceiling;// {Mistake 1}
            indices[i] = ceiling == null? -1 : map.get(ceiling);  // {Correction 1: get the index corresponding to that end point, instead of directly using the end point} 
        }
        return indices;
    }
}