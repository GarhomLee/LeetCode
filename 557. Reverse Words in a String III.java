https://leetcode.com/problems/reverse-words-in-a-string-iii/

// 思路：考察对Java中String和StringBuilder的使用。

class Solution {
    public String reverseWords(String s) {
        String[] split = s.trim().split("\\s+");
        StringBuilder res = new StringBuilder();
        for  (String str: split) {
            StringBuilder sb = new StringBuilder(str);
            res.append(sb.reverse().toString()).append(" ");
        }
        
        return res.toString().trim();
    }
}