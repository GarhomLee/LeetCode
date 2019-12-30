https://leetcode.com/problems/my-calendar-ii/

对比：729. My Calendar I

思路：两个List（或一个List一个TreeMap）

时间复杂度：
空间复杂度：

class MyCalendarTwo {
    TreeMap<Integer, Integer> twoOverlaps;
    List<int[]> atLeastOneOverlap;
    
    public MyCalendarTwo() {
        twoOverlaps = new TreeMap<>();
        atLeastOneOverlap = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        Integer twoPre = twoOverlaps.floorKey(start), twoNext = twoOverlaps.ceilingKey(start);
        if ((twoPre != null && twoOverlaps.get(twoPre) > start) || (twoNext != null && twoNext < end)) {
            return false;
        }
        
        for (int[] pair : atLeastOneOverlap) {
            int key = pair[0], value = pair[1];
            int newStart = Math.max(start, key), newEnd = Math.min(end, value);
            if (newStart < newEnd) {
                twoOverlaps.put(newStart, newEnd);
            }
        }
        
        atLeastOneOverlap.add(new int[]{start, end});
            
        return true;
    }
}