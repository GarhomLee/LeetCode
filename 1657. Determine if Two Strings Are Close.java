https://leetcode.com/problems/determine-if-two-strings-are-close/

idea: Count&Sort
time complexity: O(n log n)
space complexity: O(26+n)

class Solution {
    public boolean closeStrings(String word1, String word2) {
        int[] count1 = new int[26], count2 = new int[26];
        for (char c1 : word1.toCharArray()) {
            count1[c1 - 'a']++;
        }
        for (char c2 : word2.toCharArray()) {
            count2[c2 - 'a']++;
        }
        
        // check if two words have same char occurrence
        for (int i = 0; i < 26; i++) {
            if (count1[i] != 0 && count2[i] != 0) continue;
            if (count1[i] != 0 || count2[i] != 0) {
                return false;
            }
        }
        
        // check if they have the same frequency distribution of chars
        Arrays.sort(count1);
        Arrays.sort(count2);
        for (int i = 0; i < 26; i++) {
            if (count1[i] != count2[i]) {
                return false;
            }   
        }
        
        return true;
    }
}