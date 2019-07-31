https://leetcode.com/problems/concatenated-words/

对比：140. Word Break II，做法基本类似。

// 解法一：DP
//         状态函数：对于每一个String word，都有对应长度的dp数组。dp[i]表示当前word[0:i)是否能从words字符串
//                 数组中除了自己以外的其他word拼接而来，如果是，dp[i]=true
//         状态转移方程：用right指针遍历word的每个字母，确定dp[right]取值。目标是判断dp[len]的取值。
//                     再利用left指针，扫描word[0:right)。如果dp[left]==true且word[left:right)存在于
//                     words字符串数组，那么dp[right]==true，表示word[0:right)都可以由其他字符串拼接而来。
//                     只要有一个left能使得dp[right]==true，就可以break结束当前循环，right指针右移继续扫描。
//                     扫描完当前word，如果dp[len]==true，说明整个word是可以由其他字符串拼接而来的，符合题意，
//                     因此加入res列表。
//         初始值：dp[0]=true
// 技巧：在搜索每一个word前，将这个word从HashSet中拿掉，这样可以避免搜索到自己。搜索结束后，再放回HashSet。
// 时间复杂度：O(n*l^2), n=num of words, l=average length of words
// 空间复杂度：O(n+l), n=num of words, l=average length of words

class Solution {
    Set<String> set = new HashSet<>();
    List<String> res = new ArrayList<>();
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        set.addAll(Arrays.asList(words));
        for (String word: words) {
            if (word.isEmpty()) continue;
            set.remove(word);  // {Trick: avoid detecting itself}
            search(set, word);  // not a dfs
            set.add(word);
        }
        
        return res;
    }
    
    private void search(Set<String> set, String word) {
        int len = word.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int right = 1; right <= len; right++) {
            for (int left = 0; left < right; left++) {
                /* if both word[0:left) and word[left:right) can be formed from words in HashSet */
                if (dp[left] && set.contains(word.substring(left, right))) {
                    dp[right] = true;  // word[0:right) can be formed from words in HashSet
                    break;
                }
            }
        }
        
        if (dp[len]) res.add(word);
    }
}


解法二：Trie + DFS