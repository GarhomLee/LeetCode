https://leetcode.com/problems/split-a-string-in-balanced-strings/

// 思路：Greedy
//         依次遍历所有字符，统计'L'和'R'的出现次数，当次数相等时就可以进行切割，res++。最后返回res。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int balancedStringSplit(String s) {
        int LCount = 0;
        int res = 0;
        for (char c : s.toCharArray()) {
            LCount += c == 'L' ? 1 : -1;
            if (LCount == 0) {
                res++;
            }
        }
        
        return res;
    }
}