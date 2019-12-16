https://leetcode.com/problems/remove-covered-intervals/

// 思路：Customized sort for intervals
//         先对【左端点从小到大】排序，再对【右端点从大到小】排序，这样在排序后的数组中只有左边的元素有可能
//         覆盖右边的元素。也就是说，右边元素的左端点一定>=左边元素左端点，但右边元素的右端点和左边元素右端点
//         的大小关系不确定。
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)

class Solution {
    private boolean isCovered(int[] i1, int[] i2) {
        return i1[0] >= i2[0] && i1[1] <= i2[1];
    }
    
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] == i2[0] ? i2[1] - i1[1] : i1[0] - i2[0];
            }
        });
        
        int count = 0, len = intervals.length;
        int left = 0;
        while (left < len) {
            int right = left + 1;
            while (right < len && isCovered(intervals[right], intervals[left])) {
                count++;
                right++;
            }
            left = right;
        }
        
        return len - count;
    }
}


优化：只需要比较当前遍历到的interval右端点和当前记录的右端点最大值。因为右边元素的左端点一定>=左边元素左端点，
        但右边元素的右端点和左边元素右端点的大小关系不确定，因此只需要比较右端点即可。
参考：https://leetcode.com/problems/remove-covered-intervals/discuss/451277/JavaPython-Sort