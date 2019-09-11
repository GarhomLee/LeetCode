https://leetcode.com/problems/sentence-screen-fitting/

// 思路：Greedy？DP？参考：https://leetcode.com/problems/sentence-screen-fitting/discuss/90845/21ms-18-lines-Java-solution
//         step1: 将所有sentence字符串数组中的单词以空格" "拼接成 新字符串str，注意最后一个单词后面也要带空格" "，
//             这样是为了模拟将所有字符按题意依次填入matrix。
//             同时，维护指针start，start % len指示当前扫描到的拼接后的字符位置。
//         step2: 将从start % len开始的cols个字符填入当前行r，也就是更新start += cols。此时可能有两种情况：
//             1）这个str[start % len + cols]为' '，那么更新前的str[start % len : start % len + cols)都能放进
//                 当前行r，而str[start % len + cols]是空格，且将会放入下一行r+1。所以，可以将start++，指向下一个非空格
//                 的字符，用来放入下一行。
//             2）这个str[start % len + cols]不为空格，那么更新前的str[start % len : start % len + cols)并不能都放进
//                 同一行，所以start指针要回退到当前单词的第一个非空字符，假设为i，那么只有str[start % len : start % len + i)
//                 能放进当前行，而str[start % len + i]将从下一行开始放置。
//                 注意：如果str[start % len + cols]不为空格，但str[start % len + cols - 1]是空格，那么start并不会回退，
//                 因此并不影响结果。
//         step3: 最后结果为start / len。
// 时间复杂度：O(r * l), r=num of rows, l=sentence[i].length()
// 空间复杂度：O(n * l), n=num of words in sentence arr, l=sentence[i].length()

class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String str = "";
        for (String s: sentence) {
            str += s + " ";
        }
        
        int start = 0, len = str.length();
        for (int r = 0; r < rows; r++) {
            start += cols;
            if (str.charAt(start % len) == ' ') {
                start++;
            } else {
                while (start > 0 && str.charAt((start - 1) % len) != ' ') {
                    start--;
                }
            }
        }
        
        return start / len;
    }
}