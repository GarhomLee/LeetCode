https://leetcode.com/problems/valid-word-abbreviation/

time complexity: O(w+a)
space complexity: O(1)

class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }
            
            if (abbr.charAt(j) == '0') {
                return false;
            }
            
            int count = 0;
            while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                count = count * 10 + (abbr.charAt(j++) - '0');
            }
            
            if (count == 0) {
                return false;
            } else {
                while (count > 0) {
                    count--;
                    i++;
                }
            }
        }
        
        return i == word.length() && j == abbr.length();
    }
}