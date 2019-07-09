https://leetcode.com/problems/tag-validator/

// 思路：Stack + String.startsWith() + String.indexOf()
//      遍历字符串code，主要考虑各种【tag的左半部分出现】时的情况，以下顺序按优先级从高到低排列：
//      1）首先考虑是否出现提前终止的情况，即遍历已经开始了，还没结束，Stack却空了的情况
//      2）String.startsWith()遇到"<![CDATA["，先考虑Stack是否为空，如果为空，不符合题意，直接返回false。
//         利用String.indexOf()查看后面部分的字符串是否出现相应的"]]>"，如果没有出现，返回false。
//         更新left。
//      3）String.startsWith()遇到"</"，先考虑Stack是否为空，如果为空，不符合题意，直接返回false。
//         利用String.indexOf()查看后面部分的字符串是否出现相应的">"，以及出现的位置，中间包含的content，
//         如果不合法，返回false。
//         从Stack中将合法的头部元素pop出来，更新left。
//      4）String.startsWith()遇到"<"。
//         利用String.indexOf()查看后面部分的字符串是否出现相应的">"，以及出现的位置，中间包含的content。
//         如果不合法，返回false。
//         将合法的content放进Stack，更新left。
//      5）其他情况，直接更新left++
// 犯错点：1.特殊情况考虑不全：如果出现<A></A><B></B>，即遍历已经开始了，还没结束，Stack却空了，这时也要返回false

class Solution {
    public boolean isValid(String code) {
        /* corner case */
        if (code.isEmpty()) return false;

        int len = code.length();
        Stack<String> stack = new Stack();
        int left = 0;
        while (left < len) {
            // {Mistake 1}
            if (left > 0 && stack.isEmpty()) return false;  // {Correction 1: catch early termination}
            
            if (code.startsWith("<![CDATA[", left)) {
                if (stack.isEmpty()) return false;  // this is necessary, because it includes left == 0
                
                left += 9;  // update left pointer to the right of "<![CDATA["
                /* check if String code contains corresponding ending "]]>" */
                if (left >= len || code.indexOf("]]>", left) < 0) return false;
                left = code.indexOf("]]>", left) + 3;  // update left
            } else if (code.startsWith("</", left)) {
                if (stack.isEmpty()) return false;  // this is necessary, because it includes left == 0

                left += 2;  // update left pointer to the right of "</"
                if (left >= len) return false;
                int right = code.indexOf(">", left);
                /* check if String code contains corresponding ending ">", and if the content between "</" and ">" is valid*/
                if (right < 0 || right == left || right - left > 9 || !stack.peek().equals(code.substring(left, right))) return false;
                stack.pop();  // remove valid top element from Stack
                left = right + 1;  // update left
            } else if (code.startsWith("<", left)) {
                left += 1;  // update left pointer to the right of "<"
                if (left >= len) return false;
                int right = code.indexOf(">", left);
                /* check if String code contains corresponding ending ">", and if the content between "<" and ">" is valid*/
                if (right < 0 || right == left || right - left > 9) return false;
                StringBuilder sb = new StringBuilder();
                for (int i = left; i < right; i++) {
                    char c = code.charAt(i);
                    if (c < 'A' || c > 'Z') return false;
                    sb.append(c);
                }
                /* put the valid content between "<" and ">" into Stack */
                stack.push(sb.toString());
                left = right + 1;  // update left
            } else {
                left++;
            }
        }
        return stack.isEmpty();  // catch leftover
    }
}