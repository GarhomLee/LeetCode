https://leetcode.com/problems/jewels-and-stones/

// 思路：遍历，考察内置函数的使用
//         想判断某字符串是否含有某字符，可以用HashSet，【也可以用内置的indexOf()】
// 时间复杂度：O(j*s), j=J.length() by indexOf(), s=S.length()
// 空间复杂度：O(1)

class Solution {
    public int numJewelsInStones(String J, String S) {
        int res = 0;
        for (char c: S.toCharArray()) {
            if (J.indexOf(c) != -1) {
                res++;
            }
        }
        
        return res;
    }
}