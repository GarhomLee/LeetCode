https://leetcode.com/problems/last-substring-in-lexicographical-order/

// 思路：常规遍历
//         遍历String s，找到值最大的字符largestCharacter。再次遍历s，在每个largestCharacter的位置i，得到
//         s[i:s.length)子串，这个子串必定是以largestCharacter开头的最大子串之一。比较所有这样的子串，取最大
//         的那个。
//         优化：为了避免出现类似"aaaaaaaaaaaaa"的只有一个重复字符的情况，维护变量allCharsSame。如果出现只有
//         一个重复字符的情况，那么遍历结束后allCharsSame为true，那么直接返回s。否则，寻找子串并比较。

class Solution {
    public String lastSubstring(String s) {
        int largestCharacter = s.charAt(0);
        boolean allCharsSame = true;
        for(char c : s.toCharArray()){
            if(c != largestCharacter)
                allCharsSame = false;
            largestCharacter = Math.max(largestCharacter, c);
        }

        if(allCharsSame)
            return s;
        String result = "";
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == largestCharacter && s.substring(i).compareTo(result) > 0)
                result = s.substring(i);
        }
        return result;
    }
}