https://leetcode.com/problems/number-of-matching-subsequences/

idea: HashMap + Binary Search
time complexity: O(s + w*l*log s), s=S.length(), w=words.length, l=words[i].length()
space complexity: O(s)

class Solution {
    private boolean isSubseq(String w, TreeSet<Integer>[] indices) {
        int wLen = w.length();
        
        Integer j = -1;
        for (int i = 0; i < wLen; i++) {
            char c = w.charAt(i);
            j = indices[c].higher(j);
            if (j == null) {
                return false;
            }
        }
        
        return true;
    }
    
    public int numMatchingSubseq(String S, String[] words) {
        TreeSet<Integer>[] indices = new TreeSet[128]; 
        for (int i = 0; i < 128; i++) {
            indices[i] = new TreeSet<>();
        }
        
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            indices[c].add(i);
        }
        
        int count = 0;
        for (String w : words) {
            if (w.length() > S.length()) continue;
            count += isSubseq(w, indices) ? 1 : 0;
        }
        
        return count;
    }
}