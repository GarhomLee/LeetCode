https://leetcode.com/problems/implement-magic-dictionary/

idea: Trie + DFS
time complexity: O(n*l), n=word count, l=average length of words
space complexity: O(26^l)

class MagicDictionary {
    class TrieNode {
        TrieNode[] next;
        boolean isWord;
        
        public TrieNode() {
            next = new TrieNode[26];
            isWord = false;
        }
        
        public void add(char c) {
            if (next[c - 'a'] != null) return;
            next[c - 'a'] = new TrieNode();
        }
        
        public TrieNode next(char c) {
            return next[c - 'a'];
        }
    }
    
    TrieNode root;
    
    /** Initialize your data structure here. */
    public MagicDictionary() {
        root = new TrieNode();
    }
    
    public void buildDict(String[] dictionary) {
        for (String s : dictionary) {
            TrieNode curr = root;
            for (char c : s.toCharArray()) {
                curr.add(c);
                curr = curr.next(c);
            }
            curr.isWord = true;
        }
    }
    
    public boolean search(String searchWord) {
        return helper(searchWord, 0, root, 0);
    }
    
    private boolean helper(String word, int idx, TrieNode curr, int mismatch) {
        if (idx == word.length()) {
            return curr != null && curr.isWord && mismatch == 1;
        }
        if (curr == null || mismatch > 1) {
            return false;
        }
        
        boolean res = false;
        for (char c = 'a'; c <= 'z'; c++) {
            int nextMis = c != word.charAt(idx) ? mismatch + 1 : mismatch;
            res = res || helper(word, idx + 1, curr.next(c), nextMis); 
        }
        return res;
    }
}
