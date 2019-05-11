https://leetcode.com/problems/longest-common-prefix/

// 总体思路：有比较多的解法，如Horizontal scanning，Vertical scanning，构建Trie，等等

// 解法一：Horizontal scanning，同时扫描所有单词的相同位置。当扫描到某个单词的末尾或者某个单词和其他单词有mismatch的时候返回。

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0 || strs[0].length() == 0) return "";  // corner case
        
        StringBuilder sb = new StringBuilder();
        int i = 0;  // i indicates current index
        while (true) {  // use this loop condition if we don't know the length of the longest word
            if (i == strs[0].length()) return sb.toString();  // if strs[0] is the shortest word
            char curr = strs[0].charAt(i);  // curr indicates the current char at index i
            for (String s: strs) {  // scan every word at index i
                if (i == s.length() || s.charAt(i) != curr) return sb.toString();  // i reaches the end of some word or there is a mismatch
            }
            sb.append(curr);  // curr exists at all words
            i++;  // update i
        }
        // return is not reachable
    }
}

// 解法二：Vertical scanning，逐个单词扫描，维护临时变量记录扫描过的单词的longest common prefix，跟下一个单词比较，然后更新longest common prefix。
//         利用String中的indexOf() function，如果来自于扫描过的单词的longest common prefix对于当前的单词匹配后的index != 0，说明不匹配，所以逐渐
//         缩小longest common prefix直至匹配，即indexOf()的值为0。

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0)    return "";
        String pre = strs[0];
        int i = 1;
        while(i < strs.length){
            while(strs[i].indexOf(pre) != 0)
                pre = pre.substring(0,pre.length()-1);
            i++;
        }
        return pre;
    }
}


// 解法三：利用Trie的特性，构建Trie