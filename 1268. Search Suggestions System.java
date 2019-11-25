https://leetcode.com/problems/search-suggestions-system/

思路：Trie + Priority Queue

时间复杂度：
空间复杂度：

class Solution {
    class TrieNode {
        PriorityQueue<String> pq;
        TrieNode[] next;
        public TrieNode() {
            pq = new PriorityQueue<>(new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s2.compareTo(s1);
                }
            });
            next = new TrieNode[128];
        }
    }
    
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        
        // build the trie
        for (String word : products) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (curr.next[c] == null) {
                    curr.next[c] = new TrieNode();
                }
                curr = curr.next[c];
                curr.pq.offer(word);
                if (curr.pq.size() > 3) {
                    curr.pq.poll();
                }
            }
        }
        
        // traverse each char in searchWord
        boolean canSearch = true;
        TrieNode curr = root;
        for (char c : searchWord.toCharArray()) {
            List<String> list = new ArrayList<>();
            
            if (canSearch && curr.next[c] != null) {
                curr = curr.next[c];
                list.addAll(curr.pq);
                Collections.sort(list);
            } else if (canSearch) {
                canSearch = !canSearch;
            }
            
            res.add(list);
        }
        
        return res;
    }
}