https://leetcode.com/problems/detect-capital/

// 思路：理解题目意思，只有三种情况是合法的：
//      1）所有字母都大写
//      2）所有字母都小写
//      3）第一个字母大写，其他字母小写
//      因此，维护一个变量capigalCount，记录大写字母数量。
//      如果capigalCount == 0，表示所有字母都小写；如果capigalCount == word.length()，表示所有字母都大写；
//      如果capigalCount == 1 && word.charAt(0) >= 'A' && word.charAt(0) <= 'Z'，表示第一个字母大写，其他字母小写。
// 犯错点：1.当所有字母都是大写时也正确，这时capigalCount == word.length()。

class Solution {
    public boolean detectCapitalUse(String word) {
        if (word.length() < 2) return true;
        int capigalCount = 0;
        for (char c: word.toCharArray()) {
            if (c >= 'A' && c <= 'Z') capigalCount++;
        }
        //return capigalCount == 0 || (capigalCount == 1 && word.charAt(0) >= 'A' && word.charAt(0) <= 'Z');  // {Mistake 1}
        return capigalCount == 0 || capigalCount == word.length() || (capigalCount == 1 && word.charAt(0) >= 'A' && word.charAt(0) <= 'Z');  // {Correction 1}
    }
}