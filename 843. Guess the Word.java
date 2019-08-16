https://leetcode.com/problems/guess-the-word/

// 思路：Minimax。参考：https://leetcode.com/problems/guess-the-word/discuss/134251/Optimal-MinMax-Solution-(+-extra-challenging-test-cases)
//         关键点：1）总体过程：在当前数组中选某个单词调用guess()猜匹配字符数，然后求出数组其他单词这个单词的匹配字符数，
//                     将和guess()结果不同的单词全部排除，只留下和guess()结果相同的单词，同时将整个数组更新。
//                 2）怎么选“某个单词”：统计当前数组中每个单词分别和所有单词的匹配字符数，统计最多的匹配字符数出现的次数
//                     （如：和当前单词匹配0个字符的单词有2个，匹配1个字符的有7个…假设最多匹配的单词数为7个）。
//                     “某个单词”应该选择【最多匹配的单词数是最小的那个单词，即minimax，越小频率越接近1/6】。这样的选择，
//                     能在调用guess()猜匹配字符数后排除掉尽量多的单词。
//         step1: 调用自己写的findBestGuess()，找到当前wordlist中合适作为guess的单词
//         step2: 调用master.guess()，了解到目标单词和当前被选中的单词有多少字符匹配，记为match
//         step3: 将当前wordlist和被选中的单词有match个匹配字符的单词取出来，放进List，再转成数组并用来【更新wordlist数组】。
//         step4: 将上述过程循环【最多10次】。
//         实现以下函数：
//         1）String findBestGuess(String[] wordlist)，找到当前wordlist中合适作为guess的单词。
//             实现过程见关键点2）。
//         2）int getMatchCount(String s1, String s2)，统计s1和s2中字符匹配且位置匹配的字符个数。
//             直接遍历即可。
// 时间复杂度：O(N^2 * log N)
// 空间复杂度：O(N)
// 犯错点：1.题意理解错误：需要把findSecretWord()中master.guess()的调用次数限制在10次以内。题目要求在10次master.guess()
//             中只要有一次返回值为6即可。

class Solution {
    private static final int WORD_LEN = 6;
    
    public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0; i < 10; i++) {
        String guess = findBestGuess(wordlist);
        int match = master.guess(guess);
        List<String> list = new ArrayList<>();
        for (String word: wordlist) {
            if (getMatchCount(guess, word) == match) {
                list.add(word);
            }
        }
        wordlist = list.toArray(new String[0]);
        }
    }
    
    private String findBestGuess(String[] wordlist) {
        int n = wordlist.length;
        int minPeak = Integer.MAX_VALUE;
        int bestIndex = 0;
        for (int i = 0; i < n; i++) {
            int[] count = new int[WORD_LEN + 1];
            int peakCount = 0;
            for (int j = 0; j < n; j++) {
                int match = i == j ? WORD_LEN : getMatchCount(wordlist[i], wordlist[j]);
                count[match]++;
                peakCount = Math.max(peakCount, count[match]);
            }
            
            if (peakCount < minPeak) {
                minPeak = peakCount;
                bestIndex = i;
            }
        }
        return wordlist[bestIndex];
    }
    
    private int getMatchCount(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}