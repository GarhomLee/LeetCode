https://leetcode.com/problems/bulb-switcher-iii/

idea: Find the running max where max equals its index.
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int numTimesAllBlue(int[] light) {
        int res = 0, max = 0;
        for (int i = 0; i < light.length; i++) {
            max = Math.max(max, light[i]);
            if (max == i + 1) {
                res++;
            }
        }
        return res;
    }
}