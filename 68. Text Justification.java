https://leetcode.com/problems/text-justification/

// 对比：类似lc？找不到题号了

// 思路：Greedy
//         step1:将words所有字符串用一个空格' '连接起来，最后一个字符串后面也有空格，新字符串记为str。
//         step2:维护变量start，指示当前str搜索窗口的起始位置。
//         step3:如果当前start合法，不断循环。
//                 根据最大窗口长度maxWidth，得到窗口结束位置end。
//                 如果end不是空格，那么回退到最近的空格。
//         step4:维护临时变量count，统计当前子串str[start:end)中的空格个数。
//             可能有3种情况：
//             1）end为str的最后一位，那么根据题意，只需要将当前子串str[start:end)原样放入StringBuilder，
//                 然后在后面剩余位置填充空格即可。
//             2）当前子串str[start:end)中不包含空格，那么和情况1）相同，只需要在后面剩余位置填充空格。
//             3）当前子串str[start:end)中包含1个以上空格，那么需要填入字符，还要填入空格，直到start到达end位置。
//                 根据最大窗口长度maxWidth，子串长度，以及子串中的空格数，可以得到maxWidth中除字母外可放入的空格数spaces。
//                 遍历str[start:end)，如果当前字符c为字母，正常放入StringBuilder；
//                 如果c为空格，那么向StringBuilder中放入spaces / count个空格。如果不能整除，那么还需+1个空格，
//                 记为addSpace。
//                 放入完当前的空格后，更新count--，同时更新剩余空格数spaces -= addSpace。
//         step5:处理完当前子串str[start:end)，将StringBuilder的字符加入res列表，然后更新start = end + 1.
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.细节错误：如果当前substring中只有1个单词，没有空格，那么处理的方式应该和最后一行相同。
//         2.细节错误：对于substring中只有1个单词或为最后一行的后续空格，空格个数应该为每行的最大长度maxWidth
//             减去已经填入的substring长度，而不是减去end位置。

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        String str = "";
        for (String word: words) {
            str += word + " ";
        }
        
        List<String> res = new ArrayList<>();
        int start = 0;  // start index of current word in str
        while (start < str.length()) {
            /* find the substring in str which can fit in one line */
            int end = Math.min(start + maxWidth, str.length() - 1);
            while (end > start && str.charAt(end) != ' ') {
                end--;
            }
            String substring = str.substring(start, end);

            /* fill the current line */
            StringBuilder sb = new StringBuilder();
            int count = 0;  // num of spaces in str[start:end)
            int index = substring.indexOf(' ');  // next index of space int str[start:end)
            while (index >= 0) {  // count the num of spaces in str[start:end)
                count++;
                index = substring.indexOf(' ', index + 1);
            }
            
            //if (end == str.length() - 1)  // {Mistake 1}
            if (end == str.length() - 1 || count  == 0) {  // at the last line or only fit one word  // {Correction 1}
                sb.append(substring);
                //for (int i = 0; i < maxWidth - end; i++)  // {Mistake 2}
                for (int i = 0; i < maxWidth - substring.length(); i++) {  // {Correction 2}
                    sb.append(' ');
                }
            } else {  // not at the last line
                int spaces = maxWidth - (end - start - count);  // num of spaces to be filled in current line with capacity of chars of maxWidth
                while (start < end) {  // scan str[start:end)
                    char c = str.charAt(start);
                    if (c != ' ') {  // put one char into this line
                        sb.append(c);
                    } else {
                        int addSpace = spaces / count + (spaces % count == 0 ? 0 : 1);
                        for (int i = 0; i < addSpace; i++) {  // put appropriate num of spaces between words
                            sb.append(' ');
                        }
                        count--;  // one space in str[start:end) has been filled
                        spaces -= addSpace;  // spaces between two words in current line has been filled
                    }

                    start++;  // move to next char in str[start:end)
                }
            }
            
            res.add(sb.toString());  // finish current line
            start = end + 1;  // start to process next line
        }
        
        return res;
    }
}