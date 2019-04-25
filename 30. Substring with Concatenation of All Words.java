https://leetcode.com/problems/substring-with-concatenation-of-all-words/

有两种解法，都是使用HashTable，解法二利用sliding window的思想进行进一步的优化。

// 解法一：
// 1）维护两个Map：第一个Map为记录words数组中出现的word及次数，key为word，value为出现的次数；第二个Map为遍历s时寻找到的word和出现的次数
// 2）从0开始，到s.length() - wordsNum * wordLen【包含这个位置，即取等】，搜索是否出现words及出现次数是否相符
// 3）维护临时变量num，表示当前遍历到的出现过的记录在allWords中的单词数量；同时维护临时变量start，表示每个单词的第一个字符位置，按wordLen更新
// 4）用while循环从当前位置i进行搜索。当循环结束，用num判断是否搜索到了所有记录在allWords中的单词，如果是，那么将当前i加入list

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if (s.length() == 0 || words.length == 0) return list;
        
        Map<String, Integer> allWords = new HashMap<>();
        for (String word: words) allWords.put(word, allWords.getOrDefault(word, 0) + 1);
        
        int wordsNum = words.length, wordLen = words[0].length();
        for (int i = 0; i <= s.length() - wordsNum * wordLen; i++) {
            Map<String, Integer> wordCounter = new HashMap<>();
            int start = i, num = 0;
            while (num < wordsNum) {
                String curr = s.substring(start, start + wordLen);
                if (!allWords.containsKey(curr) || wordCounter.getOrDefault(curr, 0) >= allWords.get(curr)) break;
                wordCounter.put(curr, wordCounter.getOrDefault(curr, 0) + 1);
                start += wordLen;
                num++;
            }
            if (num == wordsNum) list.add(i);
        }
        return list;
    }
}

// 解法二：利用sliding window的思想进行进一步的优化，详见https://leetcode.windliang.cc/leetCode-30-Substring-with-Concatenation-of-All-Words.html