https://leetcode.com/problems/number-of-segments-in-a-string/

// 关键点：理解题意，只要被空格分割的都算segment，不一定只包含大小写字母。

// 解法一：直接遍历
//        维护变量count，表示segment个数；isSegment，表示是否找到了一个未被处理的segment。
//        遍历s的所有字符，有三种情况：
//        1）字符不为空字符，找到了一个未被处理的segment，那么isSegment更新为true；
//        2）字符为空字符，但isSegment为true，说明刚刚找到了未被处理的segment，所以count++，同时isSegment更新为false
//        3）字符为空字符，且isSegment为false，忽略不管
// 犯错点：1.细节错误：如果最后一个segment后面没有空格，那么跳出循环后还需要判断一下isSegment是否为true，否则会漏掉。

class Solution {
    public int countSegments(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int count = 0;
        boolean isSegment = false;
        for (char c: s.toCharArray()) {
            if (c != ' ') {
                isSegment = true;
            } else if (isSegment) {
                count++;
                isSegment = false;
            }
        }
        // {Mistake 1}
        if (isSegment) count++;  // {Correction 1: if String s is ended with a char, there will be one last segment}
        return count;
    }
}

//另一种写法：找到segment的左端字符，代表新的segment

        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                count++;
            }
        }


// 解法二：利用String内置函数
// 犯错点：1.内置函数理解错误：如果s只有空格，那么split和trim后都会留下一个空字符串，要特殊处理

class Solution {
    public int countSegments(String s) {
        if (s == null || s.length() == 0) return 0;
        
        String trimmed = s.trim();
        return trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
    }
}

