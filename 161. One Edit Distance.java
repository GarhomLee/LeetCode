https://leetcode.com/problems/one-edit-distance/

// 解法一：String.substring()，参考：https://leetcode.com/articles/one-edit-distance/
//         step0:保持s的长度len1比t的长度了len2小。
//                 如果len1和len2相差大于1，那么一定不符合题意，返回false。
//         step1:同时遍历s和t，s[i]==t[j]时同时右移i和j。
//                 如果s[i]!=t[j]，那么要么是t比s多一个字符，因此i不变，j移到下一位；要么s[i]和t[j]是替换的关系，
//                 因此i和j都要移到下一位。
//                 更新i和j后，如果s[i:len1)和t[j:len2)不相同，那么直接返回false。
//         step2:如果遍历完s和t都没有返回，要么s==t，要么t多出来的一个字符在末尾，因此判断是否len2 - len1 == 1。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        
        if (len1 > len2) {
            return isOneEditDistance(t, s);
        }
        if (len2 - len1 > 1) {
            return false;
        }
        
        int i = 0, j = 0;
        while (i < len1 && j < len2) {
            char c1 = s.charAt(i), c2 = t.charAt(j);
            if (c1 != c2) {
                i += len1 == len2 ? 1 : 0;
                j++;
                return s.substring(i).equals(t.substring(j));
            } else {
                i++;
                j++;
            }
        }
        
        return len2 - len1 == 1;
    }
}


解法二：Count
时间复杂度：O(n)
空间复杂度：O(n)
犯错点：1.边界条件错误：需要考虑s="", t="a"时，while循环根本无法进入，count不能更新。或者当s="a", t="ac"时，
            如果只判断count是否为0会导致出错。因此，需要在返回前增加判断是否在count==0时len2 - len1 == 1。
        2.细节错误：当s[i]!=t[j]时，要更新count++。
        3.题意理解错误：一定需要【恰好1个不同字符】，不能s和t完全相同，也不能超过字符1个不同。

class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        /* optimization: always make s as the shorter one */
        if (len1 > len2) {
            return isOneEditDistance(t, s);
        }
        
        /* if too many differences */
        if (len2 - len1 > 1) {
            return false;
        }
        
        int count = 0;
        int i = 0, j = 0;
        while (i < len1 && j < len2) {
            char c1 = s.charAt(i), c2 = t.charAt(j);
            if (c1 != c2) {  // difference found 
                /* too many differences */
                if (count > 0) {
                    return false;
                } 
                // {Mistake 2}
                count++;  // {Correction 2}
                i += len1 == len2 ? 1 : 0;  // update i if len1 == len2, which means it is a replacement
            } else {  // difference not found
                i++;  // update i
            }
            j++;  // update j anyways
        }
        
        //return true;  // {Mistake 1} {Mistake 3}
        return count == 1 || len2 - len1 == 1;  // {Correction 1} {Correction 3}
                                                // check if there is exactly 1 difference either in the middle or at the end
    }
}
