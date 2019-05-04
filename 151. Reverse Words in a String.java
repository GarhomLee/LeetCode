https://leetcode.com/problems/reverse-words-in-a-string/

// 总体思路：找到被空格分隔的每一个单词，然后从后往前排列，中间插入空格

// 解法一：利用String自带的trim和split function
// 注意：最后要用trim()把多余的空格去掉

class Solution {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append(" ");
        } 
        return sb.toString().trim();
    }
}

// 解法二：用Two pointers，不用String自带的trim和split function
// 1）维护List来存找到的word
// 2）维护两个指针：left指向单词的第一个字母，right指向最后一个字母的下一位
// 3）先移动left指针，在s.length()范围内找到非空格的字符
// 4）再将right赋值为left + 1，找到当前单词的最后一个字母，在下一位的空格或者s.length()边界处停下
// 5）判断当前的left和right之间是否为合法的单词，判断方法是看是否有right <= s.length()。因为如果left已经到达边界，
//     即s.length()，那么right = left + 1必然超出边界，if语句block不执行。
// 6）从后往前将List中的单词加入StringBuilder，同时加上空格，最后留下第一个单词，因为它后面不会接空格
// 7）注意：因为可能出现List中没有单词的情况，所以在【取第一个单词前需要进行if判断】

class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        int left = 0, right = 1;
        while (left < s.length()) {
            while (left < s.length() && s.charAt(left) == ' ') left++;
            right = left + 1;
            while (right < s.length() && s.charAt(right) != ' ') right++;
            if (right <= s.length()) list.add(s.substring(left, right));
            left = right + 1;
        }

        for (int i = list.size() - 1; i >= 1; i--) {
            sb.append(list.get(i)).append(" ");
        }
        if (!list.isEmpty()) sb.append(list.get(0));
         
        return sb.toString();
    }
}

// 解法三：调用String的其他api，如lastIndexOf()

public class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int index = s.length() - 1;
        while (index >= 0) {
            if (s.charAt(index) == ' ') {
                index--;
                continue;
            }
            int start = s.lastIndexOf(' ', index) + 1;
            sb.append(' ');
            sb.append(s.substring(start, index + 1));
            index = start - 1;
        }
        if (sb.length() > 0) sb.deleteCharAt(0);
        return sb.toString();
    }
}