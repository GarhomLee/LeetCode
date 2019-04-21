https://leetcode.com/problems/palindrome-pairs/

// 比较难的一道Trie的变种题目，需要多练习来熟悉。英文版详解：https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n-*-k2)-java-solution-with-Trie-structure
// 1）【注意corner cases】：["a",""]
// 2）自建一个class TrieNode，【倒序】表现一个word。其中index表示当前字符为开头（因为是倒序）的word在数组中的位置，
//     next表示可能出现的上一个字符（因为是倒序），restPalindromes表示在包含当前字符到最后的字符（在root中）的所有
//     words中，剩下的字符（从0到前一个字符）是palindrome的那些words的位置。
// 3）遍历words，【倒序】建立Trie，【从root开始】就要赋值restPalindromes和index。注意：当word遍历结束，【restPalindromes
//     也需要赋值为当前的word在数组中的位置】，因为剩下的字符是空字符，而空字符也是palindrome。
// 4）遍历words，对每一个word，从【倒序建立的Trie】中搜索可能的palindrome pairs。在对target word的字符进行比对的过程中，有
//     两种情况：
//     （a）target word prefix + rest of target word + whole trie word
//     （b）whole target word + rest of trie word + trie word suffix
//     对于情况（a）：target word搜寻还没结束，如果当前的TrieNode的index != -1，说明找到了一个完整的word作为palindrome的suffix，
//     那么如果target word的剩下部分也为palindrome，就可以组成pair。
//     对于情况（b）：target word搜寻结束，位于Trie的某个节点中，那么只需遍历restPalindromes这个list，排除自己的index，即可组成
//     palindrome pair。

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> resultList = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < words.length; i++) {
            search(words[i], i, root, resultList);
        }
        return resultList;
    }
    private void search(String word, int i, TrieNode root, List<List<Integer>> resultList) {
        TrieNode curr = root;
        for (int j = 0; j < word.length(); j++) {  // j points to the next character in the target word
            /* when the target word is not ended but there is a word in the trie that matches the prefix of the target word, check if the rest of the target word is palindrome */
            if (curr.index >= 0 && curr.index != i && isPalindrome(word, j, word.length() - 1)) {
                resultList.add(Arrays.asList(i, curr.index));
            }
            if (curr.next[word.charAt(j)] == null) return;
            curr = curr.next[word.charAt(j)];  // move to the next character in the target word
        }
        /*  when the target word is already ended, check if it matches other words in trie as prefix by checking restPalindromes list */
        for (int index: curr.restPalindromes) {
            if (index != i) resultList.add(Arrays.asList(i, index));
        }
    }
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            TrieNode curr = root;
            for (int j = words[i].length() - 1; j >= 0; j--) {
                char c = words[i].charAt(j);
                if (curr.next[c] == null) curr.next[c] = new TrieNode();
                
                if (isPalindrome(words[i], 0, j)) curr.restPalindromes.add(i);
                curr = curr.next[c];
            }
            curr.restPalindromes.add(i);
            curr.index = i;
        }
        return root;
    }
    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }
    class TrieNode {
        int index;
        List<Integer> restPalindromes;
        TrieNode[] next;
        boolean isRestPalindrome;
        TrieNode() {
            index = -1;
            restPalindromes = new ArrayList<Integer>();
            next = new TrieNode[128];
        }
    }
}