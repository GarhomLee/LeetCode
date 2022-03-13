https://leetcode.com/problems/longest-word-with-all-prefixes/

class Solution {
    class TrieNode {
        TrieNode[] next;
        boolean isWord;
        
        public TrieNode() {
            isWord = false;
            next = new TrieNode[128];
        }
    }
    
    TrieNode root = new TrieNode();
    
    // helper function
    private void addWord(String word) {
        TrieNode curr = root;
        for (char c: word.toCharArray()) {
            if (curr.next[c] == null) curr.next[c] = new TrieNode();
            curr = curr.next[c];
        }
        curr.isWord = true;
    }
    
    // helper function
    private boolean isValid(String word) {
        TrieNode curr = root;
        for (char c: word.toCharArray()) {
            curr = curr.next[c];
            if (!curr.isWord) return false;
        }
        
        return true;
    }
    
    public String longestWord(String[] words) {
        for (String word: words) {
            addWord(word);
        }
        
        String ret = "";
        for (String word: words) {
            if (!isValid(word) || word.length() < ret.length() || (word.length() == ret.length() && word.compareTo(ret) > 0)) continue;
            
            ret = word;
        }
        
        return ret;
    }
}