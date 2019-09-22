https://leetcode.com/problems/number-of-days-in-a-month/

// 思路：常识题
// 时间复杂度：O(1)
// 空间复杂度：O(1)

class Solution {
    public int numberOfDays(int Y, int M) {
        int[] months = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeap(Y) && M == 2) {
            return 29;
        }
        
        return months[M];
    }
    
    private boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}