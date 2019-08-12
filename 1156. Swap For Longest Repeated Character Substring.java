https://leetcode.com/problems/swap-for-longest-repeated-character-substring/

// 思路：遍历计数，two-pass
//         step1: 第一次遍历，统计每个出现的字符出现的总次数，存在count数组中；同时，统计每个连续字符子串的长度，
//                 记录在该连续字符子串的最左边元素位置currLen[left]和最右边元素位置currLen[right]。
//         step2: 第二次遍历，利用count数组信息和连续字符子串长度信息currLen找到符合题意的最大长度maxLen，这个
//                 长度是最多交换1个字符能组成的最大长度。
//                 维护两个指针，left表示当前连续字符子串的左边缘位置，right表示当前连续字符子串的右边缘位置的下一位，
//                 即当前连续字符子串的范围为[left:right)
//                 可以分为两种情况：1）当前连续字符子串长度包含了该字符在整个字符串出现的所有字符，即count[c] == currLen[left]；
//                 2）在当前连续字符子串之外还有别的相同的字符，即count[c] > currLen[left]。
//         step3: 如果count[c] == currLen[left]，那么不能进行交换，直接利用currLen[left]更新maxLen
//         step4: 如果count[c] > currLen[left]，至少能从外面拿1个相同字符来交换，因此要比较换到left-1位置的长度
//                 更长，还是换到right位置的长度更长。即换到left-1位置的长度为leftLen，换到right位置的长度为rightLen。
//                 leftLen和rightLen都初始化为currLen[left]。
//                 对于leftLen，有2个判断：
//                 1）如果left-1 >= 0，那么能换一个字符到left-1位置，因此leftLen+=1。否则，left==0，不能放入任何字符，
//                 leftLen+=0.
//                 2）如果left - 2 >= 0且text.charAt[left - 2] == c，说明1）一定满足，而且前面还有一段同样的连续字符子串，
//                 被left-1隔开了，因此将字符交换到left-1后就能连成更长的子串。这时又需要判断，如果前面的连续字符子串长度
//                 currLen[left-2]==count[c] - currLen[left]，说明换过来的字符来自于前面的连续字符子串，而由于已经提前
//                 计入了一个换过来的字符，所以要排除掉一个字符，leftLen+=currLen[left-2]-1。否则，字符串的其他位置仍然有
//                 多余的字符来填补当前连续字符子串和前一个连续字符子串之间的空缺，那么可以计入整个currLen[left-2]，
//                 即leftLen+=currLen[left-2。
//                 对于rightLen，进行相同的判断，空缺的位置为right，下一段同样的连续字符子串的起始位置为right+1。
//                 利用leftLen和rightLen来更新maxLen。
//         step5: 返回结果maxLen
// 时间复杂度：O(n), n=text.length()
// 空间复杂度：O(n), n=text.length()

class Solution {
    public int maxRepOpt1(String text) {
        /* first pass: assign the length of same contiguous chars to the start and end position of this char in currLen */
        int[] count = new int[128];
        int[] currLen = new int[text.length()];
        int left = 0, right = 0;
        while (left < text.length()) {
            int currCount = 0;
            char c = text.charAt(left);
            while (right == left || (right < text.length() && text.charAt(right) == c)) {
                currCount++;
                currLen[right] = currCount;
                right++;
            }
            currLen[left] = currCount;  // record currCount in the start position
            count[c] += currCount;

            left = right;  // update left pointer
        }
        
        /* second pass: find the maxLen */
        int maxLen = 0;
        left = 0;  // reset left pointer
        right = 0;  // reset right pointer
        while (left < text.length()) {
            char c = text.charAt(left);  // current char
            right = left + currLen[left];  // the window with same chars is [left:right)
                        
            if (count[c] == currLen[left]) {  // no spare char c can be used to swap
                maxLen = Math.max(maxLen, currLen[left]);
            } else {  // at least 1 char c can be used to swap
                /* get the possible max length if char c is swapped with text[left-1] */
                int leftLen = currLen[left];
                leftLen += left - 1 >= 0 ? 1 : 0;  // borrow a char c and swap with text[left-1] anyway if text[left-1] is within the boundary
                if (left - 2 >= 0 && text.charAt(left - 2) == c) {  // check if we can make a longer substring
                    leftLen += currLen[left - 2] == count[c] - currLen[left] ? currLen[left - 2] - 1: currLen[left - 2];
                }
                /* get another possible max length if char c is swapped with text[right] */
                int rightLen = currLen[left];
                rightLen += right < text.length() ? 1 : 0;  // borrow a char c and swap with text[right] anyway if text[right] is within the boundary
                if (right + 1 < text.length() && text.charAt(right + 1) == c) {  // check if we can make a longer substring
                    rightLen += currLen[right + 1] == count[c] - currLen[left] ? currLen[right + 1] - 1: currLen[right + 1];
                }
                /* update maxLen with leftLen and rightLen */
                maxLen = Math.max(maxLen, Math.max(leftLen, rightLen));
            }

            left = right;  // update left pointer
        }
        
        return maxLen;
    }
}