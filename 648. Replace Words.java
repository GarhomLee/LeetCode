https://leetcode.com/problems/replace-words/

solution1: Trie
time complexity: O(d*l + s), d=dictionary.size(), l=average length of word in dictionary, s=sentence.length()
space complexity: O(d*l), d=dictionary.size(), l=average length of word in dictionary

class Solution {
    class TrieNode {
        TrieNode[] next;
        String word;
        
        public TrieNode() {
            next = new TrieNode[26];
        }
        
        public void add(char c) {
            if (next[c - 'a'] != null) return;
            
            next[c - 'a'] = new TrieNode();
        }
        
        public TrieNode next(char c) {
            return next[c - 'a'];
        }
        
        public String getWord() {
            return word;
        }
        
        public void setWord(String s) {
            word = s;
        }
    }
    
    public String getRoot(String s, TrieNode rootNode) {
        TrieNode curr = rootNode;
        for (char c: s.toCharArray()) {
            curr = curr.next(c);
            if (curr == null) {
                return s;
            }
            if (curr.getWord() != null) {
                return curr.getWord();
            }
        }
        return s;
    }
    
    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNode rootNode = new TrieNode();
        for (String s : dictionary) {
            TrieNode curr = rootNode;
            for (char c : s.toCharArray()) {
                curr.add(c);
                curr = curr.next(c);
            }
            curr.setWord(s);
        }
        
        String[] split = sentence.split("\\s+");
        for (int i = 0; i < split.length; i++) {
            split[i] = getRoot(split[i], rootNode);
        }
        
        return String.join(" ", split);
    }
}

solution2: Sort + HashSet + String.startsWith()


class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        String[] split = sentence.split("\\s+");
        Collections.sort(dictionary);
        Set<String> set = new HashSet<>();
        for (String s1 : dictionary) {
            boolean isRoot = true;
            for (String s2 : set) {
                isRoot = isRoot && !s1.startsWith(s2);
            }
            if (isRoot) {
                set.add(s1);
            }
        }
        
        for (int i = 0; i < split.length; i++) {
            for (String s : set) {
                if (split[i].startsWith(s)) {
                    split[i] = s;
                    break;
                }
            }
        }
        
        return String.join(" ", split);
    }
}