https://leetcode.com/problems/find-longest-awesome-substring/submissions/

idea: Prefix + Bit Manipulation. Refer to: https://leetcode.com/problems/find-longest-awesome-substring/discuss/779893/C%2B%2BJavaPython3-with-picture-(similar-to-1371)
time complexity: O(n*10)
space complexity: O(2^10=1024)

class Solution {
    public int longestAwesome(String s) {
        int n = s.length();
        int[] firstSeen = new int[1 << 10];
        Arrays.fill(firstSeen, n);
        firstSeen[0] = -1;
        
        int maxLen = 0, curr = 0;
        for (int i = 0; i < n; i++) {
            curr ^= 1 << (s.charAt(i) - '0');
            maxLen = Math.max(maxLen, i - firstSeen[curr]);
            // allow one odd count char
            for (int j = 0; j < 10; j++) {
                maxLen = Math.max(maxLen, i - firstSeen[curr ^ (1 << j)]);
            }
            
            firstSeen[curr] = Math.min(firstSeen[curr], i);
        }
        
        return maxLen;
    }
}