https://leetcode.com/problems/rearrange-spaces-between-words/

idea: Brute Force
time complexity: O(n)
space complexity: O(n)

class Solution {
    public String reorderSpaces(String text) {
        int n = text.length();
        String[] split = text.trim().split("\\s+");
        int space = n;
        for (String s : split) {
            space -= s.length();
        }
        
        int intermediate = split.length == 1 ? 0 : space / (split.length - 1);
        int extra = space - intermediate * (split.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            sb.append(split[i]);
            if (i == split.length - 1) {
                // last word
                for (int j = 0; j < extra; j++) {
                    sb.append(' ');
                }
            } else {
                // intermediate words
                for (int j = 0; j < intermediate; j++) {
                    sb.append(' ');
                }
            }
        }
        
        return sb.toString();
    }
}