https://leetcode.com/problems/roman-to-integer/

// 1）因为是从Character到Integer的mapping，所以可以直接使用数组而不用Map
// 2）正常情况下，从左到右遍历String，根据mapping将value加和为sum
// 2）当给定的String是valid的时候，如果出现当前char的mapping value小于下一个位置的char的mapping value，说明遇到了4，9，etc的情况。
//     根据定义，只需将sum减去当前位置value即可。

class Solution {
    public int romanToInt(String s) {
        int[] mapping = new int[128];
        mapping['I'] = 1;
        mapping['V'] = 5;
        mapping['X'] = 10;
        mapping['L'] = 50;
        mapping['C'] = 100;
        mapping['D'] = 500;
        mapping['M'] = 1000;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int curr = mapping[s.charAt(i)];
            if (i + 1 < s.length() && mapping[s.charAt(i + 1)] > curr) curr = -curr;
            sum += curr;
        }
        return sum;
    }
}