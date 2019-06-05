https://leetcode.com/problems/word-search-ii/

// 总体思路：79. Word Search的follow-up。DFS+Backtracking，要用到Trie这个数据结构
//         首先，遍历所有words以及每个word的每个字符，将Trie构建好。可以单独包装成一个function。
//         然后，DFS+Backtracking找到所有符合的words。
//         goal: Trie中有当前word的完整信息，即trie.word != null
//         choices:相邻的四个元素
//         constraints:1）元素在board范围内，不过界
//                     2）没有被搜索过，不带'*'标记
//                     3）trie.next[board[row][col]]!=null，表示board[row][col]存在于Trie中的合适的位置，可以进一步搜索。注意：还有另一种写法，判断trie!=null
// 犯错点：1.找到了一个word后，Trie里记录的word要赋值为null，避免重复搜索导致结果中有duplicate
//        2.注意helper method中Trie参数的含义，为next中存有board[row][col]的TrieNode，是board[row][col]的parent
// 优化：Trie中的next数组可以只分配26个元素

class Solution {
    List<String> res = new ArrayList();
    int[] dir = new int[]{0, 1, 0, -1, 0};
    
    public List<String> findWords(char[][] board, String[] words) {
        Trie root = new Trie();
        
        /* build Trie */
        for (String word: words) {
            Trie curr = root;
            for (char c: word.toCharArray()) {
                curr.add(c);
                curr = curr.next[c];
            }
            curr.word = word;
        }
        
        /* depth-first search */
        int rowLen = board.length, colLen = rowLen == 0 ? 0 : board[0].length;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (root.next[board[row][col]] != null) {
                    //dfs(board, row, col, root.next[board[row][col]]);  // {Mistake 2}
                    dfs(board, row, col, root);  // {Correction 2: no need to go into its corresponding Trie node directly}
                }
            }
        }
        
        return res;
    }
    
    private void dfs(char[][] board, int row, int col, Trie trie) {
        /* 写法一：先确保trie.next[board[row][col]] */
        /* corner cases, constraints */
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] == '*' || trie.next[board[row][col]] == null) return;
        
        char c = board[row][col];
        board[row][col] = '*';
        /* goal */
        if (trie.next[c].word != null) {
            res.add(trie.next[c].word);
            trie.next[c].word = null;  // {Mistake 1} {Correction 1: since the values of words are distinct, and duplicate is not allowed in List res, the word should be removed once found}
        }
        
        /* 写法二：先进行trie是否为null的判断，那么需要先将trie.word加入res，再判断是否越界。如果先判断越界，那么即使trie.word有信息也会被跳过，导致错误 */
        /*if (trie == null) return;  // corner case
        if (trie.word != null) {  // goal
            res.add(trie.word);
            trie.word = null;  // {Mistake 1} {Correction 1: since the values of words are distinct, and duplicate is not allowed in List res, the word should be removed once found}
        }
        
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] == '*') return;  // constraints */
        

        /* depth-first search, choices */
        for (int i = 0; i < 4; i++) {
            //dfs(board, row + dir[i], col + dir[i + 1], trie.next[board[row + dir[i]][col + dir[i + 1]]]);  // {Mistake 2}
            dfs(board, row + dir[i], col + dir[i + 1], trie.next[c]);  // {Correction 2: use its parent Trie node instead of its corresponding Trie node directly}
        }
        board[row][col] = c;
    }
    
    class Trie {
        Trie[] next;
        String word;
        public Trie() {
            next = new Trie[128];
        }
        public void add(char c) {
            if (next[c] == null) next[c] = new Trie();
        }
    }
}