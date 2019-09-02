https://leetcode.com/problems/meeting-rooms/

// 思路：Customized Sort
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)

class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] == i2[0] ? i1[1] - i2[1] : i1[0] - i2[0];
            }
        });
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        
        return true;
    }
}