https://leetcode.com/problems/palindromic-substrings/

// 对比：5. Longest Palindromic Substring，本题是求palindrome个数。

// 解法一：中心扩散法
//         遍历String s，分别以每个字符s[i]为中心点扩展为奇数个字符的palindrome，和以每两个字符s[i]和s[i+1]
//         为中心点扩展为偶数个字符的palindrome，统计能扩展出的所有palindrome个数。
// 时间复杂度：O(n^2)
// 空间复杂度：O(1)
// 犯错点：1.细节错误：要将s[left]==s[right]作为count++和更新left--和right++的条件，否则即使s[left]!=s[right]
//             也会执行left--和right++的话，会导致出错。

class Solution {
    public int countSubstrings(String s) {
        /* corner case */
        if (s.length() == 0) {
            return 0;
        }
        
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += count(s, i, i);
            count += count(s, i, i + 1);
        }
        return count;
    }
    
    private int count(String s, int left, int right) {
        int count = 0;
        /*while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                count++;
            }
            left--;
            right++;
        }*/  // {Mistake 1}

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }  // {Correction 1}
        
        return count;
    }
}


解法二：Manacher's algorithm，不懂
时间复杂度：O(n)
空间复杂度：O(n)