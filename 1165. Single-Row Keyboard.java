https://leetcode.com/contest/biweekly-contest-7/problems/single-row-keyboard/

// 思路：常规遍历，利用indexOf()
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int calculateTime(String keyboard, String word) {
        int res = 0, pre = 0;
        for (char c: word.toCharArray()) {
            int curr = keyboard.indexOf(c);
            res += Math.abs(curr - pre);
            pre = curr;
        }
        
        return res;
    }
}