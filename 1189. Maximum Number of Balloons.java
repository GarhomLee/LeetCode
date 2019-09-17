https://leetcode.com/problems/maximum-number-of-balloons/

// 思路：HashMap，利用数组模拟。
//         取'b'的个数，'a'的个数，'n'的个数，'l'的个数的一半，和'n'的个数的一半的最小值。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] count = new int[26];
        
        for (char c: text.toCharArray()) {
            count[c - 'a']++;
        }
        
        int res = Integer.MAX_VALUE;
        for (char c: "balon".toCharArray()) {
            int temp = count[c - 'a'] / (c == 'l' || c == 'o' ? 2 : 1);
            res = Math.min(res, temp);
        }
        
        return res;
    }
}