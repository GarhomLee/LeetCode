https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/

思路：转成String，检查是否含有'0'
时间复杂度：O(n log n), considering String construction and char search
空间复杂度：O(log n)

class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int i = 1; i < n; i++) {
            int j = n - i;
            String s1 = String.valueOf(i), s2 =  String.valueOf(j);
            if (s1.indexOf('0') >= 0 || s2.indexOf('0') >= 0) continue;
            
            return new int[]{i, j};
        }
        
        return new int[2];
    }
}