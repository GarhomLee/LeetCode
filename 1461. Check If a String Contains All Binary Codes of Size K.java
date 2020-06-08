https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/

idea: HashSet
    -Record all substring with length k, and finally check if the size of HashSet is 2^k
time complexity: O(n*k)
space complexity: O(n*k)
optimization: Bit Manipulation

class Solution {
    public boolean hasAllCodes(String s, int k) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i + k <= s.length(); i++) {
            set.add(s.substring(i, i + k));
        }
        
        return set.size() == (1 << k);
    }
}