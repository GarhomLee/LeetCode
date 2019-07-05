https://leetcode.com/problems/brick-wall/

// 思路：HashMap
//      HashMap中的key为从左到右的砖块的右边缘位置对应的长度，value为这个长度出现的次数。
//      根据题意可以发现，想要画一条线使得穿过的砖块数最少，那么需要线上接触的砖块边缘最多。因此，用HashMap记录所有
//      边缘长度出现的次数，得到出现最多的次数，那么用总行数减去边缘出现最多位置的次数（每一次对应的是一行），就得到
//      被穿过的最小砖块数。
// 小技巧：不需要记录每行的最大宽度来排除将线划在墙边的情况，只需要不遍历每行的最后一块砖即可。

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        /* corner case */
        if(wall.size() == 0) return 0;

        int maxCount = 0;
        Map<Integer, Integer> map = new HashMap();
       
        for (List<Integer> row: wall) {
            int len = 0;
            for (int col = 0; col < row.size() - 1; col++) {  // {Trick: no need to visit the last brick of the row}
                len += row.get(col);
                map.put(len, map.getOrDefault(len, 0) + 1);
                maxCount = Math.max(maxCount, map.get(len));
            }
        }
        
        return wall.size() - maxCount;
    }
}