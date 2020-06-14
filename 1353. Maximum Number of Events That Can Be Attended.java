https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/

idea: Sort + TreeSet. Referring to: https://www.youtube.com/watch?v=NjF9JGDGxg8
    -Sort first by early end day and then by early start day.
    -Use a TreeSet to record all the available days.
    -For each sorted event, find the first available day inside the interval, and increase the count 
     and remove this day from TreeSet. If not found, skip this event.
time complexity: O(e log e + m log m + e log m), e=events.length, m=maxDay
space complexity: O(m)

class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        
        int maxDay = 0;
        for (int[] event : events) {
            maxDay = Math.max(maxDay, event[1]);
        }
        
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 1; i <= maxDay; i++) {
            treeSet.add(i);
        }
        
        int count = 0;
        for (int[] curr : events) {
            Integer avail = treeSet.ceiling(curr[0]);
            if (avail != null && avail <= curr[1]) {
                count++;
                treeSet.remove(avail);
            }
        }
        
        return count;
    }
}