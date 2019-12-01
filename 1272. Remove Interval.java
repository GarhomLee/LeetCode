https://leetcode.com/problems/remove-interval/

思路：Line Sweep

时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] >= toBeRemoved[0] && intervals[i][1] <= toBeRemoved[1]) continue;
            
            List<Integer> list1 = new ArrayList<>();
            if (intervals[i][0] < toBeRemoved[0]) {
                list1.add(intervals[i][0]);
                int end = intervals[i][1] <= toBeRemoved[0] ? intervals[i][1] : toBeRemoved[0];
                list1.add(end);
                res.add(list1);
            }
            
            List<Integer> list2 = new ArrayList<>();
            if (intervals[i][1] > toBeRemoved[1]) {
                int start = intervals[i][0] >= toBeRemoved[1] ? intervals[i][0] : toBeRemoved[1];
                list2.add(start);
                list2.add(intervals[i][1]);
                res.add(list2);
            }
            
            
        }
        
        return res;
    }
}