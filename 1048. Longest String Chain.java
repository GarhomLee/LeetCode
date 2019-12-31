https://leetcode.com/problems/longest-string-chain/

解法一：DP

时间复杂度：O(n log n + n^2 * l), n=words.length, l=words[i].length()
空间复杂度：O(n)

class Solution {
    private boolean isPredecessor(String s1, String s2) {
        int mismatch = 1;
        int i = 0, j = 0;
        while (i < s1.length() && j < s2.length()) {
            char c1 = s1.charAt(i), c2 = s2.charAt(j);
            if (c1 != c2) {
                if (mismatch == 0) {
                    return false;
                }
                mismatch--;
                j++;
            } else {
                i++;
                j++;
            }
        }
        
        return true;
    }
    
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        int len = words.length;
        int[] dp = new int[len];
        int max = 1;
        for (int i = 0; i < len; i++) {
            int currLen = words[i].length();
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (words[j].length() == currLen) continue;
                if (words[j].length() < currLen - 1) break;
                
                if (isPredecessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}


解法二：DP + HashMap

时间复杂度：O(n log n + n * l^2), n=words.length, l=words[i].length()
空间复杂度：O(n)

class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        Map<String, Integer> dp = new HashMap<>();
        dp.put(words[0], 1);
        int len = words.length;
        int max = 1;
        for (String s : words) {
            int currMax = 1;
            for (int i = 0; i < s.length(); i++) {
                String pre = s.substring(0, i) + s.substring(i + 1);
                if (!dp.containsKey(pre)) continue;
                
                currMax = Math.max(currMax, dp.get(pre) + 1);
            }
            
            dp.put(s, currMax);
            max = Math.max(max, currMax);
        }
        
        return max;
    }
}