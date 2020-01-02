https://leetcode.com/problems/goat-latin/

思路：String API

class Solution {
    public String toGoatLatin(String S) {
        String[] split = S.split("\\s+");
        for (int i = 0; i < split.length; i++) {
            StringBuilder sb = new StringBuilder(split[i]);
            char c = sb.charAt(0);
            if (!"aeiouAEIOU".contains(c + "")) {
                sb.deleteCharAt(0);
                sb.append(c);
            }
            
            sb.append("ma");
            for (int j = 0; j < i + 1; j++) {
                sb.append("a");
            }
            split[i] = sb.toString();
        }
        
        return String.join(" ", split);
    }
}