https://leetcode.com/problems/my-calendar-i/

对比：731. My Calendar II

解法一：TreeMap


解法二：Binary Search

class MyCalendar {
    List<int[]> list;
    
    public MyCalendar() {
        list = new ArrayList<>();
    }
    
    private int binarySearch(int target) {
        int low = 0, high = list.size() - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int[] interval = list.get(mid);
            if (interval[0] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }
    
    public boolean book(int start, int end) {
        int index = binarySearch(start);
        boolean canBook = true;
        if (index - 1 >= 0) {
            canBook = canBook && list.get(index - 1)[1] <= start;
        }
        if (index < list.size()) {
            canBook = canBook && list.get(index)[0] >= end;
        }
        
        if (canBook) {
            list.add(index, new int[]{start, end});
        }
        
        return canBook;
    }
}