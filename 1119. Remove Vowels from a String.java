https://leetcode.com/problems/remove-vowels-from-a-string/

// 思路：遍历 + Java内置函数应用
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public String removeVowels(String S) {
        StringBuilder sb = new StringBuilder();
        String vowels = "aeiou";
        for (char c: S.toCharArray()) {
            if (vowels.indexOf(c) < 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}