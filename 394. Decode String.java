https://leetcode.com/problems/decode-string/

// 思路：两个Stack，一个存每个重复子串的重复次数，另一个存可能的重复子串的前半部分
//     遍历String s，可能出现4种情况：
//     1）遇到数字，更新count，表示接下来子串重复的次数
//     2）遇到'['，表示接下来的子串会被重复，那么把对应的重复次数count加入countStack，当前的字符串放入repeatStack，同时重置count和sb
//     3）遇到']'，表示重复子串已确定，那么将"[]"范围内的子串（即sb）重复countStack.top次，然后append在repeatStack.top后，然后更新为当前子串sb
//     4）其他情况，即遇到字母字符，直接append到当前字符串后
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.对于Stack的更新，即把当前计算结果临时储存在Stack中，是要在遇到'['后，表示要进行新的repeat
//        2.currCount要及时更新，否则会导致Memory limit exceed
// 注意：跟385. Mini Parser相比，本题如果出现'['（或']'），必然会出现']'（或'['）和数字，所以不用担心Stack为空。

class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack();  // stores a stack of the times of repeating substrings
        Stack<String> repeatStack = new Stack();  // stores a stack of the possible repeating substrings
        StringBuilder sb = new StringBuilder();  // stores the final result
        int count = 0;  // stores the times of repeating current substring
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {  // update current possible repeating times
                count = count * 10 + (c - '0');  
            } else if (c == '[') {  // when '[' encountered, push count and sb.toString() into Stack for temporary storage, and reset count and sb to initial value for processing next possible repeating substring
                // {Mistake 1} {Correction 1}
                countStack.push(count);
                count = 0;  // reset
                repeatStack.push(sb.toString());
                sb = new StringBuilder();  // reset
            } else if (c == ']') {  // when ']' encountered, it means the end of current repeating substring, merge it with its previous substring
                StringBuilder pre = new StringBuilder(repeatStack.pop());  // get the previous substring
                String currRepeat = sb.toString();
                Integer currCount = countStack.pop();  // get how many times currRepeat should repeat
                while (currCount > 0) {
                    pre.append(currRepeat);  // append currRepeat to the previous substring
                    currCount--;  // {Mistake 2: if currCount not updated, Memory limit exceed} {Correction 2}
                }
                sb = pre;  // update sb as the latest result. After this step, sb would be the result of current "[]" range
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}