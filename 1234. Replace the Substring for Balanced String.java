https://leetcode.com/problems/replace-the-substring-for-balanced-string/

思路：Sliding Window

时间复杂度：O(s), s=s.length()
空间复杂度：O(128) = O(1)
犯错点：1.思路错误：看清题意，不是简单的统计需要改变的字符数，而是要求出包含这些字符的最短substring长度。
        2.边界条件错误：要先判断total是否为0，即是不是有需要改变的字符。如果为0表示不需要改变，直接返回0。
        3.细节错误：不能跟0比较判断是否将多余的char囊括在window里，而应该跟balance比较来判断，以balance
            为标准线。
        4.细节错误：因为窗口为[left:right]，所以应该先用窗口的大小right-left+1更新了minLen，然后再
            更新左边界left++。如果先更新了left++，那么窗口的大小就应该是right-left+2。

class Solution {
    public int balancedString(String s) {
        int[] count = new int[128];
        for (char c: s.toCharArray()) {
            count[c]++;
        }
        
        int balance = s.length() / 4, total = 0;
        for (int i = 0; i < 128; i++) {
            total += count[i] - balance > 0 ? count[i] - balance : 0;
        }

        //{Mistake 2}
        /* edge case */  // {Correction 2}
        if (total == 0) {
            return 0;
        }
        
        /* sliding window */
        int minLen = Integer.MAX_VALUE;
        for (int left = 0, right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            count[rightChar]--;
            //if (count[rightChar] >= 0)  // {Mistake 3}
            if (count[rightChar] >= balance) {  // {Correction 3}
                total--;  // update total
            }
            
            while (total == 0) {  // window consists of [left:right]
                char leftChar = s.charAt(left);
                count[leftChar]++;
                if (count[leftChar] > balance) {
                    total++;
                }
                //left++;  // {Mistake 4}
                minLen = Math.min(minLen, right - left + 1);
                left++;  // {Correction 4}
            }
        }
        
        return minLen;
    }
}