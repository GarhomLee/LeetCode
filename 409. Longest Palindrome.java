https://leetcode.com/problems/longest-palindrome/

// 1）将s中character出现的次数用ASCII码长度的数组存储
// 2）不管某个character出现了奇数次还是偶数次，能作为palindrome的都只有【count / 2 * 2】次，用even变量表示
// 3）如果有character出现奇数次，那么单个元素也能形成palindrome，所以odd取值为0或1，当且仅当有character出现奇数次时取1
// 4）最长的palindrome长度即为even和odd的和

class Solution {
    public int longestPalindrome(String s) {
        int[] sCount = new int[128];
        for (char c: s.toCharArray()) {
            sCount[c]++;
        }
        int odd = 0, even = 0;
        for (int count: sCount) {
            even += count / 2 * 2;
            if (count % 2 == 1) odd = 1;
        }
        return odd + even;
    }
}