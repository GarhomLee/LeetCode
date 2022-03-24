https://leetcode.com/problems/shortest-palindrome/

// 总体思路：基于KMP算法的pattern matching。要在String s前加字符使其变为palindrome，那么必然会将s的末尾若干个字符翻转加在前面。
//         不妨先考虑将s翻转，那么显然翻转后的reverse末尾和翻转前s的开头会有重叠的相同子串。要得到最短palindrome，就转化为找到
//         最长的重叠子串，将这个子串去除，剩下的reverse加在s前面，即可得到最短palindrome。
            
//          为了求得最长的重叠子串，可以利用KMP算法中求取的longest proper suffix数组。要求得lps数组，需要匹配String的prefix和suffix。
//          因为要去除reverse末尾和s开头的重叠子串，所以concatenation时需要把s放在前面，即s + reverse，而不是reverse + s

// 1）concatenation时，【s和reverse之间需要加入一个特殊字符（如"#"）】，来避免KMP算法对于overlap的容许。
//     如：s = "aaba"，那么reverse = "abaa"，concate = "aabaabaa"，lps = [0, 1, 0, 1, 2, 3, 4, 5]，导致出错
// 2）利用helper method得到lps数组，然后用StringBuilder的delete()将reverse末尾的最长的重叠子串去除，放到s前面
// 3）lps数组中，lps[i]表示从0...i的String的以i为末尾包含i在内的suffix和以0为开头包含0在内的prefix的最长的重叠子串长度。
//     注意，重叠子串对于prefix和suffix是相同的，不是reverse的关系。
// 4）利用两个指针求取lps数组。实际上，【求取lps数组的过程也是KMP算法匹配的过程】，把指针i及之前的子串作为pattern，所以指针j
//     只会向右移动，指针i会不断调整位置，但不可能超过j。
// 5）如果i和j的字符相同，那么此时指针i也作为长度的指示，可以得到lps[j] = i + 1，同时更新i++，j++
// 6）如果i和j的字符不同，可能有两种情况
//     （a）如果i不是0，根据KMP算法匹配的性质，i - 1和j - 1必然相同，那么需要调整i，使得除了i以外，i - 1以前的子串仍然和j - 1
//         以前的子串匹配。根据KMP算法匹配的性质，i调整后的位置即为lps[i - 1]的值，所以直接更新i = lps[i - 1]
//     （b）如果i是0，i和j不匹配，说明lps[j] = 0（回顾lps数组的含义即可理解），同时需要更新j到下一位

class Solution {
    public String shortestPalindrome(String s) {
        int length = s.length();
        StringBuilder reverse = new StringBuilder(s).reverse();
        String concate = s + "#" + reverse.toString();
        
        int[] lps = new int[concate.length()];
        kmp(concate, lps);
        
        return reverse.delete(length - lps[lps.length - 1], length).append(s).toString();
    }
    private void kmp(String s, int[] lps) {
        int i = 0, j = 1;
        while (j < s.length()) {
            if (s.charAt(j) == s.charAt(i)) {
                lps[j] = i + 1;
                i++;
                j++;
            } else {
                if (i == 0) lps[j++] = 0;
                else i = lps[i - 1];
            }
        }
    }
}


二刷：Rolling Hash. See: https://www.bilibili.com/video/BV1pK411g7VB?spm_id_from=333.999.0.0, at 26min
    分别从左至右（正序）和从右至左（倒序）计算rolling hash value，如果hash相同，则为共同部分。

class Solution {
    public String shortestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        
        long MOD = Integer.MAX_VALUE;
        long base = 26;
        long hashLeft = 0, hashRight = 0, factor = 1L;
        int startIdx = 0;
        for (int i = 0; i < s.length(); i++) {
            hashLeft = ((base * hashLeft) % MOD + (s.charAt(i) - 'a')) % MOD;
            hashRight = (hashRight + ((s.charAt(i) - 'a') * factor) % MOD) % MOD;
            if (hashLeft == hashRight) {
                startIdx = i + 1;
            }
            
            factor = factor * base % MOD;
        }
        
        StringBuilder sb = new StringBuilder(s.substring(startIdx));
        sb.reverse().append(s);
        
        return sb.toString();
    }
}