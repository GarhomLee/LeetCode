https://leetcode.com/problems/valid-parenthesis-string/

解法一：Greedy。参考：https://www.youtube.com/watch?v=h9Y3i7hhCpo

时间复杂度：O(n)
空间复杂度：O(1)

class Solution {
    public boolean checkValidString(String s) {
        int low = 0, high = 0;
        for (char c : s.toCharArray()) {
            low += c == '(' ? 1 : -1;
            high += c == ')' ? -1 : 1;
            if (high < 0) {
                return false;
            }
            
            low = Math.max(0, low);
        }
        
        return low == 0;
    }
}


解法二：DP。参考：https://www.youtube.com/watch?v=h9Y3i7hhCpo
时间复杂度：O(n^3)
空间复杂度：O(n^2)