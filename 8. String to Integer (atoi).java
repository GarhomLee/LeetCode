https://leetcode.com/problems/string-to-integer-atoi/

// 需要进行各种可能情况的判断。
// 1）str为空或长度为0，直接返回0
// 2）跳过所有空格，找到第一个不是空格的位置。
//     如果整个String都是空格，或者第一个是别的字符（即不是数字，也不是正负号），那么也直接返回0
// 3）如果第一个字符不是数字，那么经过上面的判断，只有可能为正号或者负号。根据情况给sign赋值，同时i++。
//     注意：test cases似乎移除了"+-1"和"-+1"，所以只需要考虑一个正负号的情况。
// 4）正常情况，正式进入while loop，将String表示的数字用long的形式表示。
//     当遇到第一个不是数字的字符，直接break。
//     注意：为了避免【最后数字比Long.MAX_VALUE还大】的情况，在while loop中可以直接进行判断，如果大于Integer.MAX_VALUE就可以根据sign返回Integer.MAX_VALUE或Integer.MIN_VALUE
// 5）根据sign返回相应的int

class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        int i = 0;
        while (i < str.length()) {
            if (str.charAt(i) != ' ') break;
            i++;
        }
        if (i == str.length() || (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '-' && str.charAt(i) != '+')) return 0;
        
        int res = 0, sign = 1;
        if (!Character.isDigit(str.charAt(i))) {
            sign = str.charAt(i++) == '-' ? -1 : 1;
        }
        
        long lres = 0;
        while (i < str.length()) {
            if (!Character.isDigit(str.charAt(i))) break;
            lres = lres * 10 + (str.charAt(i++) - '0');
            if (lres > Integer.MAX_VALUE) return sign > 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        
        return sign * (int) lres;
    }
}
// "+-1"
// "-+1"