https://leetcode.com/problems/repeated-substring-pattern/

// 解法一：常规遍历
//        从长度1到s.length() / 2，遍历String s。如果s长度不是片段长度的整数倍，那么直接跳过。
//        记当前片段s[0:len)为substring，从位置len开始向后遍历，比较长度为len的片段和substring是否相同。
//        如果有一个不相同，那么直接跳出循环。如果所有后续片段都和substring相同，那么返回true。
//        如果对于所有的长度len，都不能找到重复片段，返回false。
// 注意点：根据重复子串的性质，必然有字符串全长为重复子串长度的整数倍，即s.length() % len == 0。

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() < 2) return false;
        
        for (int len = 1; len <= s.length() / 2; len++) {
            if (s.length() % len != 0) continue;  // {Trick: skip those length of which the whole length is not integral multiple}
            
            String substring = s.substring(0, len);
            boolean isRepeated = true;
            for (int j = len; j < s.length(); j += len) {
                if (!substring.equals(s.substring(j, j + len))) {
                    isRepeated = false;
                    break;
                }
            }
            if (isRepeated) return true;
        }
        return false;
    }
}


// 解法二：利用String内置函数startsWith()
//        利用的是重复片段的性质。假设String s可以分为A+B+C三个部分，如果是重复的，那么A==B==C，也有A+B==B+C。
//        所以，取靠后的几个片段组成的子串s.substring(len, s.length())，即B+C，如果s也以这个子串起始，即A+B==B+C，
//        那么必然有A==B==C，所以返回true。

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() < 2) return false;
        
        for (int len = 1; len <= s.length() / 2; len++) {
            if (s.length() % len != 0) continue;

            String substring = s.substring(len, s.length());
            if (s.startsWith(substring)) return true;
            
        }
        return false;
    }
}


// 解法三：拼接两个字符串
//        将相同的String s拼接在原来的String s后面，然后最开头和最末尾各删除一个字符以破坏它们的重复性。
//        假设s==A+B，那么s+s==A+B+A+B，删除前后字符后，只剩下B+A维持原来的重复性。
//        然后利用String的内置函数contains(),看s==A+B是否在B+A里能找到。如果是，那么就可以知道A==B，因此
//        原字符串s有重复性。

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() < 2) return false;
        
        StringBuilder sb = new StringBuilder(s);
        sb.append(s);
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(0);
        return sb.toString().contains(s);
    }
}


// 解法四：利用KMP算法中的lps数组

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        char[] str = s.toCharArray();
        int i = 1, j = 0, n = str.length;
        int[] dp = new int[n + 1];
        while (i < n) {
            if (str[i] == str[j]) dp[++i] = ++j;
            else if (j == 0) ++i;
            else j = dp[j];
        }
        return dp[n] != 0 && (dp[n] % (n - dp[n]) == 0);
    }
}