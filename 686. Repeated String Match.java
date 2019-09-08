https://leetcode.com/problems/repeated-string-match/

// 思路：找规律 + String.indexOf()，视频讲解：https://www.youtube.com/watch?v=tm0p3KE0KE8
//         step0: 特殊情况，直接返回-1.
//         step1: 维护变量count，表示当前用到了的A的个数。要使得B为A的子串，那么A至少为B的长度，因此不断重复
//             在末尾添加A直到A至少为B的长度，同时更新count。
//             添加完成后，如果调用indexOf()发现A中包含B，那么返回count。
//         step2: 如果此时A中不包含B，可能出现这种情况：A="abcd", B="cdab"，那么还需要多做一次添加。
//             如果再一次添加后A中包含B，那么返回step2中的count+1.
//             否则，在尝试过了以A中所有字符为开头的长度为B的长度的子串后，还不能找到B，说明不存在，返回-1.
// 时间复杂度：O(N)
// 空间复杂度：O(N)

class Solution {
    public int repeatedStringMatch(String A, String B) {
        if (A == null || B == null || A.length() == 0 || B.length() == 0) {
            return -1;
        }
        
        int count = 1;
        StringBuilder sb = new StringBuilder(A);
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }
        
        if (sb.indexOf(B) >= 0) {
            return count;
        }
        
        sb.append(A);
        if (sb.indexOf(B) >= 0) {
            return count + 1;
        }
        
        return -1;
    }
}

