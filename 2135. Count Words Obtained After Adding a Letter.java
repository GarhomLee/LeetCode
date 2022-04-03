https://leetcode.com/problems/count-words-obtained-after-adding-a-letter/

idea: HashSet + Bit Map
time comp: O(s + t)
space comp: O(s)

class Solution {
    public int wordCount(String[] startWords, String[] targetWords) {
        Set<String> startSet = new HashSet<>();
        for (String start: startWords) {
            StringBuilder sb = new StringBuilder("0".repeat(26));   // string representation of the bit map, this can be optimized to the actual bit map
            for (char c: start.toCharArray()) {
                sb.setCharAt(c - 'a', '1');
            }
            startSet.add(sb.toString());
        }
        
        int count = 0;
        for (String target: targetWords) {
            StringBuilder sb = new StringBuilder("0".repeat(26));
            for (char c: target.toCharArray()) {
                sb.setCharAt(c - 'a', '1');
            } 
            
            for (int i = 0; i < 26; i++) {
                if (sb.charAt(i) == '0') continue;
                
                sb.setCharAt(i, '0');                
                if (startSet.contains(sb.toString())) {
                    count++;
                    break;
                }
                sb.setCharAt(i, '1');
            }
        }
        
        return count;
    }
}