https://leetcode.com/problems/angle-between-hands-of-a-clock/

idea: Math
time complexity: O(1)
space complexity: O(1)

class Solution {
    final double HOUR_PIECE = 30.0;
    
    public double angleClock(int hour, int minutes) {
        double portion = minutes / 60.0;
        double mDegree = portion * 360.0;
        double hDegree = (hour % 12) * HOUR_PIECE + portion * HOUR_PIECE;
        
        double d1 = Math.min(mDegree, hDegree), d2 = Math.max(mDegree, hDegree);
        
        return Math.min(d2 - d1, d1 + 360.0 - d2);
    }
}