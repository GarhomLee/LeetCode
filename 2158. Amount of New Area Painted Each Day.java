https://leetcode.com/problems/amount-of-new-area-painted-each-day/

idea: TreeMap + Merge Intervals
    First, find the prev intervals that overlaps, and remove it from TreeMap.
    Then, find all the overlapped next intervals, and remove them from TreeMap.
    Then, update TreeMap with the latest merged interval.
time comp: amortized O(nlogn) ? 
space comp: O(n)

class Solution {
    public int[] amountPainted(int[][] paint) {
        TreeMap<Integer, Integer> intervalsMap = new TreeMap<>();   // start -> end
        int[] ret = new int[paint.length];
        for (int i = 0; i < paint.length; i++) {
            int start = paint[i][0], end = paint[i][1];
            ret[i] = end - start;   // initialize ret[i]
            
            Integer prevStart = intervalsMap.floorKey(start);
            if (prevStart != null && start <= intervalsMap.get(prevStart)) {
                ret[i] -= Math.min(end, intervalsMap.get(prevStart)) - start;   // update ret[i]
                
                start = prevStart;
                end = Math.max(end, intervalsMap.get(prevStart));
                intervalsMap.remove(prevStart); // remove the overlapped interval
            }
            
            Integer nextStart = intervalsMap.higherKey(start);
            while (nextStart != null && nextStart <= end) {
                ret[i] -= Math.min(end, intervalsMap.get(nextStart)) - nextStart;   // update ret[i]
                
                end = Math.max(end, intervalsMap.get(nextStart));
                intervalsMap.remove(nextStart); // remove the overlapped interval
                nextStart = intervalsMap.higherKey(start);
            }
            
            intervalsMap.put(start, end);
        }
        
        return ret;
    }
}