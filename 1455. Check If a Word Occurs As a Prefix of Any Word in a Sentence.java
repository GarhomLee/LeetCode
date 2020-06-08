https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/

time complexity: O(n * l), n=sentence.length, l=split[i].length
space complexity: O(n * l)

class Solution {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] split = sentence.split("\\s+");
        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith(searchWord)) {
                return i + 1;
            }
        }
        return -1;
    }
}