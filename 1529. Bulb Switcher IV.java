https://leetcode.com/problems/bulb-switcher-iv/

idea: Greedy
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int minFlips(String target) {
        int n = target.length(), ret = 0;
        char prev = '0';
        for (int i = 0; i < n; i++) {
            char curr = target.charAt(i);
            if (curr != prev) {
                ret++;
            }
            prev = curr;
        }
        
        return ret;
    }
}