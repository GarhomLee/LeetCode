https://leetcode.com/problems/number-of-students-doing-homework-at-a-given-time/

idea: Brute Force
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int n = startTime.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
                count++;
            }
        }
        return count;
    }
}