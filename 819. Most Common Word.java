https://leetcode.com/problems/most-common-word/

// 思路：HashSet + HashMap
//         利用HashSet判断是否为合法的word，利用HashMap来记录出现的合法的word的小写形式以及出现的次数。
//         维护maxCount，记录当前出现的最大次数；res，记录出现最大次数的单词。
// 时间复杂度：O(p + b), p=paragraph.length(), b=banned.length
// 空间复杂度：O(p + b), p=paragraph.length(), b=banned.length

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        for (String s: banned) {
            set.add(s);
        }
        
        Map<String, Integer> map = new HashMap<>();
        int maxCount = 0;
        String res = "";
        int left = 0;
        while (left < paragraph.length()) {
            if (!Character.isLetter(paragraph.charAt(left))) {
                left++;
                continue;
            } 
            
            int right = left;
            while (right < paragraph.length() && Character.isLetter(paragraph.charAt(right))) {
                right++;
            }
            //String substring = paragraph.substring(left, right);  // {Mistake 1}
            String substring = paragraph.substring(left, right).toLowerCase();  // {Correction 1}
            if (!set.contains(substring)) {
                int count = map.getOrDefault(substring, 0) + 1;
                map.put(substring, count);
                if (count > maxCount) {
                    maxCount = count;
                    res = substring;
                }
            }
            // {Mistake 2}
            left = right;  // {Correction 2}
        }
        
        return res;
    }
}