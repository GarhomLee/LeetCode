https://leetcode.com/problems/remove-duplicate-letters/

// 思路：Stack+Greedy，最后的结果要最小的lexicographical order，类似402. Remove K Digits。可以用array指针代替Stack进行优化。
//     如果遇到没有使用过的char c，那么需要将临时结果[0:top]当中的从top开始往前删除同时满足下面条件的char：1）比c大；2）在后面还有
//     没用上的相同的char。这意味着，即使top比c大，如果后面没有跟top相同的char了，也不能删除。
//     删除停止后，将c变成新的top，同时更新used[c]为true，表示c在临时结果中已经使用了。
// 时间复杂度：O(n)
// 空间复杂度：O(1)? 转换成char[]
// 犯错点：1.当top回退时，相应元素的信息也要更新。在这一题中，要将used[top]重置为false，临时结果中没有这个char了，后面如果遇到了相同char
//         就可以加进来。

class Solution {
    public String removeDuplicateLetters(String s) {
        boolean[] used = new boolean[26];  // check and record whether a certain char is used
        int[] count = new int[26];  // record the frequency of chars in String s
        int top = -1;  // pointer which serves as a substitution of Stack
        
        /* count the frequency of chars in String s */
        char[] sArray = s.toCharArray();
        for (char c: sArray) {
            count[c - 'a']++;
        }
        
        for (char c: sArray) {
            count[c - 'a']--;  // update the count
            
            if (used[c - 'a']) continue;  // char c has been used, skip it
            
            while (top >= 0 && c < sArray[top] && count[sArray[top] - 'a'] > 0) {
                used[sArray[top] - 'a'] = false;  // {Mistake 1} {Correction 1: used[i] should reset to false if char c has been removed}
                top--;  // remove top char
            }
            sArray[++top] = c;
            used[c - 'a'] = true;
        }
        
        /* construct result */
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            sb.append(sArray[i]);
        }
        return sb.toString();
    }
}