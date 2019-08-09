https://leetcode.com/problems/license-key-formatting/

// 思路：常规遍历，one pass
//         从后往前遍历S，跳过'-'，将小写字母变大写，依次加入StringBuilder。
//         每K个有效字符加入一个'-'，遍历结束后将可能多余的'-'去掉，返回reverse。
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.特殊情况判断错误：如果出现"----"的情况，sb.charAt(sb.length() - 1)会报错。因此需要额外做一个判断。

class Solution {
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int count = K;
        for (int i = S.length() - 1; i >= 0; i--) {
            char c = S.charAt(i);
            if (c == '-') continue;
            
            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                sb.append(Character.toUpperCase(c));
            }
            
            count--;
            if (count == 0) {
                sb.append('-');
                count = K;
            }
            
            
        }
        
        //if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '-')  // {Mistake 1}
        if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '-') {  // {Correction 1}
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.reverse().toString();
    }
}