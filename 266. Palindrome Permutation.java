https://leetcode.com/problems/palindrome-permutation/

// 思路：Count，转化为判断奇数个的字符个数是否小于2个。
//         维护变量：count数组，记录每个字符出现的次数；odd，记录当前出现了奇数次的字符个数。
//         遍历String s，对于每个字符c，不断更新count[c]和odd。如果s中存在某个permutation为palindrome，那么出现
//         奇数次的字符个数一定小于等于1个。
// 时间复杂度：O(n)
// 空间复杂度：O(1)
// 犯错点：1.题意理解错误：题目没有指明只有英文小写字母，因此128个ASCII字符都有可能出现。
//         2.题意理解错误：不是single number问题，没有指明每个字符最多出现两次，因此判断的是奇偶而不是1或2

class Solution {
    public boolean canPermutePalindrome(String s) {
        //int[] count = new int[26];   // {Mistake 1}
        int[] count = new int[128];  // {Correction 1}
        int odd = 0;
        for (char c: s.toCharArray()) {
            //if (count[c] > 2) return false;  // {Mistake 2}
            count[c]++;  // {Correction 2}
            odd += count[c] % 2 == 0 ? -1 : 1;
        }
        
        return odd < 2;
    }
}