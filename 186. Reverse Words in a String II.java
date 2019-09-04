https://leetcode.com/problems/reverse-words-in-a-string-ii/

// 对比：151. Reverse Words in a String的follow-up，将input从String变成了char[]。

// 思路：常规遍历
//         维护变量：char[] copy，为原数组s的deep copy；指针i，表示在s中当前可填入的位置；指针start和end，构成
//         范围copy[start:end]，表示需要从s[i]开始填入的字符。
//         step1: start指针从copy[end]开始，从右往左扫描，直到遇到空格' '或者超出数组范围。
//         step2: 将找到的copy[start:end]从s数组的位置i开始复制到s[i+(end-start)]，同时i更新到下一个空格的位置，
//             即i += end - start。如果i没有越界，那么s[i]赋值为空格' '，且i++。
//         step3: 更新end为下一个可能的字符位置start - 1（因为是从右往左扫描）。
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.细节错误：将copy作为临时储存，改变原字符串数组s，而不是反过来。
//         2.细节错误+越界错误：要改变原字符串数组s，可能改变后是空格的位置原来有字符，因此需要显式地将这个位置赋值
//             为空格。只有当这个位置已经在s数组边界之内，才能赋值。

class Solution {
    public void reverseWords(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        
        int len = s.length;
        char[] copy = new char[len];
        System.arraycopy(s, 0, copy, 0, len);
        
        int i = 0, end = len - 1;
        while (end >= 0) {
            int start = end;
            //while (start >= 0 && s[start] != ' ')  // {Mistake 1}
            while (start >= 0 && copy[start] != ' ') {  // {Correction 1}
                start--;
            }
            /* now start points to a space ' ' or index -1 */
            System.arraycopy(copy, start + 1, s, i, end - start);
            
            i += end - start;  // update i to the next position of space
            // {Mistake 2}
            if (i < len) {
                s[i++] = ' ';
            }  // {Correction 2}

            end = start - 1;
        }
    }
}


// follow-up：如何in-place完成？
//     step1: first pass，翻转整个s字符数组。
//     step2: second pass，遍历s数组，以空格为分割，找到合法的字符串区域[start:end)，翻转每个区域。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public void reverseWords(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        
        int len = s.length;
        swap(s, 0, len - 1);
        
        int start = 0;
        while (start < len) {
            int end = start;
            while (end < len && s[end] != ' ') {
                end++;
            }
            swap(s, start, end - 1);
            start = end + 1;
        }
    }
    
    private void swap(char[] s, int i, int j) {
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}