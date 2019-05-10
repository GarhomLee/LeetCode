https://leetcode.com/problems/count-and-say/

// 总体思路：因为要得到第n个sequence需要知道第n - 1个sequence，所以是一个recursion问题。
//         终止条件：n == 1，直接返回1。
//         先递归调用得到第n - 1个sequence，然后从第1个字符(0-based)开始数，当第i个和第i - 1个不相等，或i == length时，
//         根据count加入String。

class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";  // corner case: terminate at n == 1
        
        String lastSeq = countAndSay(n - 1);  // get the count-and-say sequence of n - 1
        StringBuilder sb = new StringBuilder();
        char currChar = lastSeq.charAt(0);  // store the current char, and initialize as the first char
        int count = 1;  // the first char has been counted
        for (int i = 1; i <= lastSeq.length(); i++) {
            if (i == lastSeq.length() || lastSeq.charAt(i) != lastSeq.charAt(i - 1)) {  // append to StringBuilder when it exhausts all chars or current char is different from the previous char, should verify i == lastSeq.length() first, otherwise it would be out of range
                sb.append(count).append(currChar);
                if (i < lastSeq.length()) {  // update currChar and count when not exhaust all chars
                    currChar = lastSeq.charAt(i);
                    count = 1;
                }
            } else {  // current char is the same as the previous char
                count++;
            }
        }
        return sb.toString();
    }
}