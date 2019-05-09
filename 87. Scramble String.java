https://leetcode.com/problems/scramble-string/

// 总体思路：递归，也可以用三维DP，但比较复杂，所以直接用递归，中间加入一点优化。
//         终止条件：s1和s2长度不等，返回false；s1和s2相等，返回true。
//         在递归前，可以进行优化，检查s1和s2包含的字符和个数是否相等，如果不等直接返回false。
//         观察可得Scramble String的性质，必定存在以某个位置i作为s1的分界，s1的左半边和s2的相等长度的左半边，及s1的右半边和s2的相等长度的右半边，都为Scramble String；
//         或者，由于分割交换之后，左右半边互换，所以s1的左半边和【s2的相等长度的右半边】，及s1的右半边和【s2的相等长度的左半边】，都为Scramble String；
//         遍历完所有可能的位置i后，如果都没有找到合适的位置形成Scramble String，那么就返回false。

class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;  // termination condition
        
        /* optimization: check if letter number matches in two strings */
        int[] counts = new int[128];
        for (int i = 0; i < s1.length(); i++) {
            counts[s1.charAt(i)]++;
            counts[s2.charAt(i)]--;
        }
        for (int i = 0; i < 128; i++) {
            if (counts[i] != 0) return false;
        }
        
        /* recursion */
        for (int i = 1; i < s1.length(); i++) {  // do NOT equal to s1.length(), because we need the last char
            String s1Left = s1.substring(0, i), s1Right = s1.substring(i);  // partition s1 into two parts
            String s2Left = s2.substring(0, i), s2Right = s2.substring(i);  // partition s2 into two parts
            if (isScramble(s1Left, s2Left) && isScramble(s1Right, s2Right)) return true;  // two paired parts with same length of s1 and s2 are both scramble strings
            s2Left = s2.substring(0, s2.length() - i);
            s2Right = s2.substring(s2.length() - i);  // partition s2 into another possible two parts
            if (isScramble(s1Left, s2Right) && isScramble(s1Right, s2Left)) return true;  // two paired parts with same length of s1 and s2 are both scramble strings
        }

        return false;  // none of any partitioning works
    }
}