https://leetcode.com/problems/construct-k-palindrome-strings/

idea: Greedy
    -Since the same characters can be grouped to be palindrome, the group with even count can certainly be
     palindrome, while the groups of odd count characters should be less than k. 
     If the groups of odd count characters are more than k, then there will be a final string with two extra
     single chars, which is not palindrome.
time complexity: O(n)
space complexity: O(26)

class Solution {
    public boolean canConstruct(String s, int k) {
        int n = s.length();
        if (k > n) return false;
        
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        int odd = 0;
        for (int i = 0; i < 26; i++) {
            odd += count[i] % 2 == 1 ? 1 : 0;
        }
        
        return odd <= k;
    }
}