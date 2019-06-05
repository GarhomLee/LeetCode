https://leetcode.com/problems/add-and-search-word-data-structure-design/

// 总体思路：利用Trie存储字符串的信息。
//         当c!='.'时，只需接着搜索node.next[c]。
//         如果遇到通配符'.'，那么所有可能的node.next[i]都可能存有word[index+1]的信息，所以要全部遍历一次。
// 犯错点：1.当index==word.length()，再接着判断node.isWord，而不能把index==word.length()和node.isWord同时判断，
//         否则当index==word.length但node.isWord为false时后续但word.charAt(index)会出error
// 优化：Trie中的next数组可以只分配26个元素

class WordDictionary {
    TrieNode root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode curr = root;
        for (char c: word.toCharArray()) {
            curr.add(c);
            curr = curr.next[c];
        }
        curr.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, 0, root);
    }
    
    private boolean search(String word, int index, TrieNode node) {
        if (node == null) return false;

        //if (index == word.length() && node.isWord) return true;  // {Mistake 1: if index == word.length() but node.isWord is false, this block will not be executed, and word.charAt(index) will throw error}
        if (index == word.length()) return node.isWord;  // {Correction 1}
        
        char c = word.charAt(index);
        if (c != '.') return search(word, index + 1, node.next[c]);
        for (int i = 0; i < 128; i++) {
            if (search(word, index + 1, node.next[i])) return true;
        }
        return false;
    }
    
    class TrieNode {
        TrieNode[] next;
        boolean isWord;
        public TrieNode() {
            next = new TrieNode[128];
        }
        public void add(char c) {
            if (next[c] == null) next[c] = new TrieNode();
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */