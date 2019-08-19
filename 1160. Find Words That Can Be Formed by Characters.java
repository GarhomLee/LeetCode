https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/

// 思路：遍历 + indexOf()
// 时间复杂度：O(n*l), n=words.length, l=average length of each word
// 空间复杂度：O(1)

class Solution {
    public int countCharacters(String[] words, String chars) {
        int res = 0;
        int[] count = new int[26];
        for (char c: chars.toCharArray()) {
            count[c - 'a']++;
        }
        
        for (String word: words) {
            int[] clone = count.clone();
            boolean canForm = true;
            for (char c: word.toCharArray()) {
                clone[c - 'a']--;
                if (word.indexOf(c) < 0 || clone[c - 'a'] < 0) {
                    canForm = false;
                    break;
                }
            }
            res += canForm ? word.length() : 0;
        }
        
        return res;
    }
}