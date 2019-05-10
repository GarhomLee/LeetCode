https://leetcode.com/problems/implement-strstr/

// 解法一：Brute force
//         每次发现mismatch，在haystack向右移动一位，和needle从第0位开始重新匹配。
//         时间复杂度：O(m * n), m = haystack.length(), n = needle.length()

class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        for (int i = 0; i < haystack.length(); i += 1) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                if (i + needle.length() > haystack.length()) return -1;
                if (haystack.substring(i, i + needle.length()).equals(needle)) return i;
            }
        }
        return -1;
    }
}

// 解法二：KMP算法
//       先用KMP算法构建lps(longest proper suffix)数组，实际上是利用prefix作为pattern，去跟suffix做匹配。
//       然后再用KMP算法，把needle作为pattern，去跟haystack做匹配。
//       时间复杂度：O(m + n), m = haystack.length(), n = needle.length()

class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int[] lps = new int[needle.length()];  // the longest proper suffix array, lps[i] indicates the longest length of suffix (including needle[i]) which is also the length of prefix (including needle[0])
        // the suffix does not include needle[0], and the prefix does not include needle[i]

        getLps(lps, needle);  // construct lps array
        return kmp(haystack, needle, lps);  // find the index of the first occurrence of needle in haystack
    }
    
    /* construct lps array by KMP algorithm, which is essentially self-matching */
    private void getLps(int[] lps, String needle) {
        int i = 1, j = 0;  // i indicates the suffix part, j indicates the prefix part
        while (i < needle.length()) {
            if (needle.charAt(i) == needle.charAt(j)) {
                lps[i++] = ++j;
            } else if (j == 0) {
                lps[i++] = 0;
            } else {
                j = lps[j - 1];
            }
        }
    }
    
    /* match string with pattern by KMP algorithm */
    private int kmp(String haystack, String needle, int[] lps) {
        int i = 0, j = 0;  // i points to chars in haystack, j points to chars in needle
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == needle.length()) return i - needle.length();  // match found
            } else if (j == 0){
                i++;
            } else {
                j = lps[j - 1];
            }
        }
        
        return -1;  // match not found
    }
}