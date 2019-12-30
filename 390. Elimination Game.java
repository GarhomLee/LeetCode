https://leetcode.com/problems/elimination-game/

思路：Math，找规律，参考：https://leetcode.com/problems/elimination-game/discuss/87119/JAVA%3A-Easiest-solution-O(logN)-with-explanation

时间复杂度：O(log n)
空间复杂度：O(1)

class Solution {
    public int lastRemaining(int n) {
        boolean fromLeft = true;
        int head = 1; // the first remaining element
        int step = 1; // the step to the next remaining element
        while (n > 1) {
            if (fromLeft || n % 2 == 1) {
                head += step; // the head should be updated because otherwise it will be eliminated
            }
            
            n /= 2;
            step *= 2;
            fromLeft = !fromLeft;
        }
        
        return head;
    }
}