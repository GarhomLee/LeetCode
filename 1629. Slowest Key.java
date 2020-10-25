https://leetcode.com/problems/slowest-key/

idea: Info Cache
time complexity: O(n)
space complexity: O(1)

class Solution {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int n = keysPressed.length();
        int maxDuration = releaseTimes[0];
        char maxKey = keysPressed.charAt(0);
        for (int i = 1; i < n; i++) {
            int duration = releaseTimes[i] - releaseTimes[i-1];
            char curr = keysPressed.charAt(i);
            if (duration > maxDuration) {
                maxDuration = duration;
                maxKey = curr;
            } else if (duration == maxDuration && maxKey < curr) {
                maxKey = curr;
            }
        }
        
        return maxKey;
    }
}