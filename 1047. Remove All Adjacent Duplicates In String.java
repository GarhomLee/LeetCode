https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/

思路：Stack
时间复杂度：O(n), n=S.length()
空间复杂度：O(n)
follow-up:1209. Remove All Adjacent Duplicates in String II

class Solution {
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}