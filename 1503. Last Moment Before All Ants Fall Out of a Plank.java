https://leetcode.com/problems/last-moment-before-all-ants-fall-out-of-a-plank/

idea: Math
    -Collision can actually be ignored.
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int getLastMoment(int n, int[] left, int[] right) {
        int res = 0;
        for (int i: left)
            res = Math.max(res, i);
        for (int i: right)
            res = Math.max(res, n - i);
        return res;
    }
}