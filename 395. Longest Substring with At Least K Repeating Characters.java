https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/

// 思路：DFS (Recursion)。视频讲解：https://www.youtube.com/watch?v=GU-03VY12Ic
//         关键点：以String s中以出现小于k次的字符的位置作为【分割点】。
//         递归函数定义：int dfs(String s, int k)，表示求给定String s中包含每个字符出现超过k次的最长
//                 substring长度。
//         终止条件：1）s长度为0或k比s的长度还大，返回0
//                 2）遍历整个s，统计所有字符出现的次数后，所有出现过的字符的出现次数都大于等于k，返回s.length()
//         递归过程：维护两个指针left和right，[left:right]表示被出现小于k次的字符分割的substring。
//                 调用递归函数，处理[left:right]，用得到的结果更新res，然后更新left和right。最后返回res。
//                 注意，即使[left:right]中出现的字符在整个String s的字符统计中大于等于k，但是因为被
//                 分割成小的部分，在[left:right]这个范围中它们出现的次数不一定还是大于等于k，所以需要
//                 重新判断，而调用递归函数就可以得到结果。
// 犯错点：1.判断条件错误：判断是否整个字符串s都符合题意时，遍历26个字母，寻找分割点count[i] < k，同时还需要
//             保证count[i] > 0表示这个字符出现过。否则，如果字符没出现过而没有被排除掉，那么会干扰s是否符合
//             题意的判断，造成“false negative”。

class Solution {
    public int longestSubstring(String s, int k) {
        return dfs(s, k);
    }
    
    private int dfs(String s, int k) {
        /* base case 1 */
        if (s.length() == 0 || k > s.length()) {
            return 0;
        }
        
        int[] count = new int[26];
        for (char c: s.toCharArray()) {
            count[c - 'a']++;
        }
        boolean isValid = true;
        for (int i = 0; i < 26; i++) {
            //if (count[i] < k)  // {Mistake 1}
            if (count[i] > 0 && count[i] < k) {
                isValid = false;
            }
        }
        /* base case 2 */
        if (isValid) {  // the whole String s is valid
            return s.length();
        }
        
        int res = 0;
        int left = 0, right = 0;
        while (right <= s.length()) {
            if (right == s.length() || count[s.charAt(right) - 'a'] < k) {
                res = Math.max(res, dfs(s.substring(left, right), k));
                left = right + 1;
            }
            right++;
        }
        
        return res;
    }
}