https://leetcode.com/problems/longest-substring-without-repeating-characters/

// 虽然也是substring问题，但不能套用sliding window模板，因为【和字符出现的位置有关】。
// 1）维护一个int array来模拟Map，index为字符（对应的ASCII码），value为字符【最近出现过的位置】，初始化为-1
// 2）维护两个pointer：left表示当前无重复字符的window的最左边的字符（包含），right表示当前扫描到的字符
// 3）遍历String的元素，如果位置<0，说明第一次出现，直接赋值为当前位置
// 4）如果如果位置>=0，说明已经出现过，需要更新为当前的位置。在更新之前，需要做一个判断：
//     （a）如果更新前字符的位置在当前window中（由left位置判断），那么需要更新left为字符位置的下一位，这样在更新为当前位置时才不会有重复元素
//     （b）如果更新前字符的位置在当前window之外（left的左边），那么意味着当前window其实不包含这个字符，left不需要更新
// 5）在遍历过程中，不断更新maxLen

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] mapping = new int[128];
        Arrays.fill(mapping, -1);
        int maxLen = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (mapping[c] < 0) mapping[c] = right;
            else {
                if (mapping[c] >= left) left = mapping[c] + 1;
                mapping[c] = right;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}