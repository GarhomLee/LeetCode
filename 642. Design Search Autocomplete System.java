https://leetcode.com/problems/design-search-autocomplete-system/

// 思路：Trie + minHeap + Backtracking
//         维护变量：
//         1）class TrieNode，用来构建Trie。TrieNode中维护frequency变量，表示以这个字符为结尾的字符串出现的次数，如果为0
//             则表示不是出现过的完整字符串。
//         2）class Node，放进minHeap来求得频数最大的三个String
//         3）StringBuilder sb，记录用户输入的所有字符
//         实现功能：
//         1）List<String> input(char ch)，得到用户输入中以ch结尾的所有字符串中频数最大的三个字符串。
//             如果ch为终止字符'#'，那么要将用户输入过的所有字符整合到Trie中，利用build()更新其频数，最后返回空列表。
//             如果是其他字符，加入StringBuilder后判断所有的用户输入是否存在于Trie中，如果不存在，则直接返回空列表。
//             如果存在，那么从当前字符开始，【通过backtracking】来得到所有以用户输入为前缀的字符串，同时利用minHeap来记录
//             出现次数最多的三个字符串。最后将minHeap中的结果存到List中并返回。
//         2）自定义void traverse(TrieNode curr, StringBuilder sb, PriorityQueue<Node> pq)，递归遍历从当前节点curr
//             开始的所有完整字符串，记录在StringBuilder中，连同出现的频数一起放入pq中。
//         3）自定义void build(String word, int times)，用来新增或更新Trie中word的频数。
// 时间复杂度：AutocompleteSystem(): O(k*l), k=sentences.length, l=sentences[i].length
//     input(): O(p + q + m log(3)), p=length of the sentence formed till now, q=the number of all nodes in the trie considering the sentence formed till now as the root node, m=the options available for the hot sentences
// 空间复杂度：O(k*l), k=sentences.length, l=sentences[i].length

class AutocompleteSystem {
    StringBuilder sb;
    TrieNode root;
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        sb = new StringBuilder();
        root = new TrieNode();
        
        /* initialize the Trie */
        for (int i = 0; i < sentences.length; i++) {
            build(sentences[i], times[i]);
        }
        
    }
    
    public List<String> input(char ch) {
        if (ch == '#') {  // termination of current sentence
            build(sb.toString(), 1);  // put the typed sentence into Trie
            sb = new StringBuilder();  // reset
            
            return new ArrayList<String>();
        }
        
        sb.append(ch);
        /* minHeap */
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.freq == n2.freq ? n2.str.compareTo(n1.str) : n1.freq - n2.freq;
            }
        });
        
        TrieNode curr = root;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            int index = c == ' ' ? 26 : (c - 'a');
            if (curr.next[index] == null) {  // not found
                return new ArrayList<String>();
            }
            
            curr = curr.next[index];
        }
        
        /* backtracking */
        traverse(curr, sb, pq);
        
        List<String> res = new ArrayList<>();
        while(!pq.isEmpty()) {
            res.add(0, pq.poll().str);
        }
        
        return res;
    }
    
    private void traverse(TrieNode curr, StringBuilder sb, PriorityQueue<Node> pq) {
        if (curr.freq > 0) {
            pq.offer(new Node(sb.toString(), curr.freq));
            if (pq.size() > 3) {
                pq.poll();
            }
        }
        
        for (int i = 0; i < 27; i++) {
            char c = i == 26 ? ' ' : (char) (i + 'a');
            if (curr.next[i] != null) {
                sb.append(c);
                traverse(curr.next[i], sb, pq);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    
    private void build(String word, int times) {
        TrieNode curr = root;
        for (char c: word.toCharArray()) {
            int index = c == ' ' ? 26 : (c - 'a');
            if (curr.next[index] == null) {
                curr.next[index] = new TrieNode();
            }

            curr = curr.next[index];
        }

        curr.freq += times;
    }
    
    class TrieNode {
        int freq;
        TrieNode[] next;
        public TrieNode() {
            freq = 0;
            next = new TrieNode[27];
        }
    }
    
    class Node {
        String str;
        int freq;
        public Node(String s, int f) {
            str = s;
            freq = f;
        }
    }
}