https://leetcode.com/problems/length-of-last-word/

class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int count = 0;  // return value

        for (int i = s.length() - 1; i >= 0; i--) {
            if (!Character.isLetter(s.charAt(i))) continue;  // skip the last couple chars if not letters
            
            while (i >= 0 && Character.isLetter(s.charAt(i))) {  // enter while loop when the last letter is found
                count++;
                i--;  // update i
            }

            break;  // when exit the while loop, exit the for loop as well
        }
        return count;
    }
}