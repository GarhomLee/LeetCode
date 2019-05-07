https://leetcode.com/problems/word-ladder/

// 属于BFS搜索最短路径问题。有两种解法。

// 解法一：普通BFS，维护一个Queue，每次处理当前Queue存储的元素，看改变某一位置的字符后的String是否在wordDict里，直到找到endWord或Queue为空。每次要从wordDict中删除当前的word。

// 解法二：bidirectional BFS，从beginWord和endWord同时向中间搜索，直到找到相同元素。
//     维护两个Set，分别表示从beginWord和endWord开始搜索的队列，用Set是因为需要查找元素，Set的查找时间为O(1)。Trick：每次都会搜索size较小的Set，所以需要进行比较和swap。
//     搜索set1元素时，下一个level的元素存在临时的newSet中，当搜索完毕时，将set1更新为newSet，同时step++。
//     终止条件：set1和set2有共同元素，返回step；否则，set1和set2搜索完，都为空，返回0。

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        /* use a Set to store the dictionary */
        Set<String> wordDict = new HashSet<>();
        for (String s: wordList) {
            wordDict.add(s);
        }
        /* if endWord is not in the dictionary, simply return 0 */
        if (!wordDict.contains(endWord)) return 0;
        
        /* use Set to store words to be processed, easy to check if a word is in it */
        Set<String> set1 = new HashSet<>(), set2 = new HashSet<>();
        set1.add(beginWord);
        set2.add(endWord);
        
        /* process from both ends */
        int step = 2;  // both beginWord and endWord in the Sets, there are already 2 steps
        while (!set1.isEmpty() && !set2.isEmpty()) {  // if either Set is empty, they will not share common words */
            if (set1.size() > set2.size()) {  // optimization: swap two Sets only when set1 has more elements
                Set<String> temp = set1;
                set1 = set2;
                set2 = temp;
            }
            
            Set<String> newSet = new HashSet<>();
            for (String word: set1) {  // process each current word
                StringBuilder sb = new StringBuilder(word);  // use StringBuilder because we will change the String
                for (int i = 0; i < word.length(); i++) {  // check every letter
                    char letter = sb.charAt(i);
                    for (char c = 'a'; c <= 'z'; c++) {  // try every possible letter
                        sb.setCharAt(i, c);  // record the original letter at position i
                        if (set2.contains(sb.toString())) return step;  // common word in both Sets found
                        else if (!wordDict.contains(sb.toString())) continue;  // current word not in dictionary
                        wordDict.remove(sb.toString());
                        newSet.add(sb.toString());  // add current word to new Set for the next level
                    }
                    sb.setCharAt(i, letter);  // change the letter back at position i
                }
            }
            step++;  // step forward when done with this level
            set1 = newSet;  // update set1
        }
        return 0;
    }
}