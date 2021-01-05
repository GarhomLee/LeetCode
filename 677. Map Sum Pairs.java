https://leetcode.com/problems/map-sum-pairs/

idea: Trie + HashMap
time complexity: O(K), K=length of key
space complexity: O(N)

class MapSum {
    class TrieNode {
        TrieNode[] next;
        int sum;
        
        public TrieNode() {
            next = new TrieNode[26];
            sum = 0;
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
    Map<String, Integer> map;   // String key -> int val
    
    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
        map = new HashMap<>();
    }
    
    public void insert(String key, int val) {
        int diff = map.containsKey(key) ? val - map.get(key) : val;
        map.put(key, val);  // update map
        
        TrieNode curr = root;
        for (char c : key.toCharArray()) {
            curr.add(c);
            curr = curr.next(c);
            curr.sum += diff;   // update sum by diff
        }
    }
    
    public int sum(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            curr = curr.next(c);
            if (curr == null) {
                return 0;
            }
        }
        
        return curr.sum;
    }
}
