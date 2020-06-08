https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/

idea: Sliding Window
    -Keep a window with length k, and record the vowels num inside the window.
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int maxVowels(String s, int k) {
        int count = 0, maxCount = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            char cRight = s.charAt(right);
            if ("aeiou".indexOf(cRight) >= 0) {
                count++;
            }
            maxCount = Math.max(maxCount, count);
            
            if (right - left + 1 >= k) {
                char cLeft = s.charAt(left++);
                if ("aeiou".indexOf(cLeft) >= 0) {
                    count--;
                }
            }
        }
        
        return maxCount;
    }
}