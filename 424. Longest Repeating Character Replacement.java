https://leetcode.com/problems/longest-repeating-character-replacement/

// 思路：sliding window，详见https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91271/Java-12-lines-O(n)-sliding-window-solution-with-explanation
//      维护变量maxLen，表示最终结果；maxCount，表示【window里】最多的相同字母的个数；count数组，表示window里
//      出现的各个字母的出现次数。
//      遍历字符串s，不断移动window右边缘，记录对应字母出现的次数count[c - 'A']++，同时更新maxCount。
//      如果遇到window大小（即right - left + 1）比出现最多的字母次数（即maxCount）大超过k个字符，因为k是允许
//      改变的最大限度，所以这意味着window过大了，需要缩小，左边缘右移一位，对应字母count[c - 'A']--。
//      这里不需要用while循环，只需要一次if判断，是因为只要right - left + 1 - maxCount == k + 1，left就
//      右移一位，就变成right - left + 1 - maxCount == k，也就是说即使是while循环也只进行了一次。这样实际上
//      是window保持大小不变整体右移。
//      根据当前window大小更新maxLen。
// 关键点：window只会扩大或者右移，不会缩小，即使window里的substring不合法。这是因为window的大小代表目前为止出现过
//     的最大长度，只有当maxCount更新时window大小才会变化，而maxCount的更新一定是因为右边缘对应的字母，对于这个字母
//     的统计count[c - 'A']记录的是window里的出现次数，所以这样的window大小变化是合法的。

class Solution {
    public int characterReplacement(String s, int k) {
        int maxLen = 0, maxCount = 0;
        int[] count = new int[26];
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            maxCount = Math.max(maxCount, ++count[c - 'A']);
            if (right - left + 1 - maxCount > k) {
                 count[s.charAt(left++) - 'A']--;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}