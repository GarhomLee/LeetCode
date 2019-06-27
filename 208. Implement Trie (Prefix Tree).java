https://leetcode.com/problems/implement-trie-prefix-tree/

// 思路：要实现Trie的功能，还需要自己构建一个TrieNode，TrieNode包含两个部分：
//      1）TrieNode数组next，表示可能的下一个字符
//      2）isWord，表示是否能形成一个已知单词
//      利用TrieNode root，可以实现Trie的功能：
//      1）insert(String word)，从root开始，不断向next搜索下一个字符，直到遍历完整个word，将isWord设为true。
//      2）search(String word)，从root开始，不断向next搜索下一个字符，如果当中遇到next为null，说明word不在当前
//         Trie里，返回false。遍历完成后，判断curr.isWord是否为true。
//      3）startsWith(String prefix)，从root开始，不断向next搜索下一个字符，如果当中遇到next为null，说明word不在当前
//         Trie里，返回false。遍历完成，说明这个prefix存在，返回true。
//      可见，三个功能代码基本相同。

class Trie {
    TrieNode root;
    
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        for (char c: word.toCharArray()) {
            if (curr.next[c - 'a'] == null) curr.next[c - 'a'] = new TrieNode();
            curr = curr.next[c - 'a'];
        }
        curr.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curr = root;
        for (char c: word.toCharArray()) {
            if (curr.next[c - 'a'] == null) return false;
            curr = curr.next[c - 'a'];
        }
        return curr.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (char c: prefix.toCharArray()) {
            if (curr.next[c - 'a'] == null) return false;
            curr = curr.next[c - 'a'];
        }
        return true;
    }
    
    class TrieNode {
        TrieNode[] next;
        boolean isWord;
        public TrieNode() {
            next = new TrieNode[26];
        }
    }
}