https://leetcode.com/problems/word-break-ii/

// 解法一：Bottom-up Memoization
//         由于对于确定的字符串s，进行word break后能得到的所有sequence组合是确定的，所以可以用memoization来减少计算降低，时间复杂度。
//         例如：s = "pineapplepenapple"，wordDict = ["apple", "pen", "pineapple"]，无论前半部分是分割成["pineapple"]还是["pine apple"]，
//         后半部分只有["pen applen"]一种情况，所以可以用Map存储下来跟这个字符串对应的sequence情况。

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return find(s, set, new HashMap<String, List<String>>());
    }
    /** helper method to find all possible word break sequences related to String s and return a List */
    private List<String> find(String s, Set<String> set, Map<String, List<String>> map) {
        if (map.containsKey(s)) return map.get(s);  // use memoization result
        
        List<String> resultList = new ArrayList<>();
        
        /* 写法一：到达终止条件时，返回的是长度为0的空List，在后面对于substring是否为空进行判断来决定需不需要append空格 */
        if (s.isEmpty()) {
            return resultList;
        }
        for (String word: set) {
            if (s.startsWith(word)) {  // if no prefix in String s is in the dictionary, this if block will not be executed and will finally return an empty List
                String substring = s.substring(word.length());
                if (substring.isEmpty()) resultList.add(word);  // when it reaches the end of String s
                else {
                    List<String> tempList = find(substring, set, map);
                    for (String temp: tempList) {
                        resultList.add(word + " " + temp);  // when tempList is not empty, which means there is valid word break sequence from substring
                    }
                }
                
            }
        }
        /* 写法二：到达终止条件时，返回的是长度为1的包含字符串""的List，用来帮助后续append空格的判断 */
        /*if (s.isEmpty()) {
            resultList.add("");
            return resultList;
        }
        for (String word: set) {
            if (s.startsWith(word)) {  // if no prefix in String s is in the dictionary, this if block will not be executed and will finally return an empty List
                List<String> tempList = find(s.substring(word.length()), set, map);
                for (String temp: tempList) {  // if there is no valid sequence from s.substring(word.length()), tempList will be a empty List; 
                                              //however, if s.substring(word.length()) is empty, which means it reaches the end of String s, tempList will contain an empty String "" instead of being a empty List
                    resultList.add(word + (temp.isEmpty()? "" : " ") + temp);
                }
            }
        }*/

        map.put(s, resultList);  // memoization
        return resultList;
    }
}

// 解法二：Bottom-up Memoization + Backtracking
//         把这题当作139. Word Break的follow-up，然后套用Backtracking的模板。
//         Goal: String s的所有字符都扫描过了，start index到达s末尾
//         Choices: 从start index开始的s中所有长度的substring
//         Constraints: s[0:i-1]可被切分（即dp[i]==true），且s[start:i-1]在dictionary中，说明s[start:i-1]可以被切出来

class Solution {
    List<String> list = new ArrayList<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        /** copy from LeetCode 139. Word Break */
        boolean[] dp = new boolean[s.length() + 1];  // dp[i] indicates whether s[0:i-1] can be validly segemented
        dp[0] = true;
        Set<String> set = new HashSet<>(wordDict);
        for (int right = 1; right <= s.length(); right++) {
            for (int left = 0; left < right; left++) {
                /* 第一种可行的状态转移方程 */
                //if (dp[left]) dp[right] = dp[right] || set.contains(s.substring(left, right));

                /* 第二种可行的状态转移方程 */
                if (dp[left] && set.contains(s.substring(left, right))) {
                    dp[right] = true;
                    break;
                }
            }
        }
        
        /** backtracking */
        if(!dp[s.length()]) return list;
        
        find(s, set, dp, 0, "");
        return list;
    }
    
    private void find(String s, Set<String> set, boolean[] dp, int start, String temp) {
        if (start == s.length()) {  // Goal: when it reaches the end of the String s
            list.add(temp.trim());
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {  // Choices: all lengths of subtring s[start:i-1] starting from start index
            if (dp[i] && set.contains(s.substring(start, i))) {  // Constraints: String s can be segmented at index i, and s[start:i-1] is in the dictionary
                find(s, set, dp, i, temp + s.substring(start, i) + " ");
            }
        }
    }
}