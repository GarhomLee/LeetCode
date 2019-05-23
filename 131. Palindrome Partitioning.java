https://leetcode.com/problems/palindrome-partitioning/

// 总体思路：Backtracking的模板。
//         Goal: String s的所有字符都扫描过了，start index到达s末尾
//         Choices: 从start index开始的s中所有长度的substring
//         Constraints: substring必须为palindrome
// 犯错点：1.在isPalindrome()中，left和right index在比较完char后需要update

class Solution {
    List<List<String>> resultList = new ArrayList<>();

    public List<List<String>> partition(String s) {
        find(s, 0, new ArrayList<String>());
        return resultList;
    }
    
    private void find(String s, int start, List<String> list) {
        if (start == s.length()) {  // Goal: when it reaches the end of the String s, add it to resultList
            resultList.add(new ArrayList<String>(list));
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {  
            String substring = s.substring(start, i);  // Choices: all lengths of subtring s[start:i) starting from start index
            if (isPalindrome(substring)) {  // Constraints: the substring must be palindrome
                list.add(substring);
                find(s, i, list);
                list.remove(list.size() - 1);
            }
        }
    }
    
    /** evaluate whether a String is palindrome */
    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;  // Trick: this way works faster than StringBuilder with reverse()
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;  // {Mistake 1: left and right index should update}
        }
        return true;
    }
}

// 优化：实际上，在判断substring是否为palindrome的过程中，进行了大量重复计算，如求isPalindrome(s[i:j))，可以由s[i]==s[j-1] && isPalindrome(s[i+1:j-1))
//     得到，所以对于isPalindrome(s[i:j))也可以用memoization来优化时间复杂度。

class Solution {
    List<List<String>> resultList = new ArrayList<>();

    public List<List<String>> partition(String s) {
        boolean[][] palindrome = new boolean[s.length() + 1][s.length() + 1];  // used for memoization
        find(s, 0, new ArrayList<String>(), palindrome);
        return resultList;
    }
    
    private void find(String s, int start, List<String> list, boolean[][] palindrome) {
        if (start == s.length()) {  // Goal: when it reaches the end of the String s, add it to resultList
            resultList.add(new ArrayList<String>(list));
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {  
            /* optimization */
            if (s.charAt(start) == s.charAt(i - 1) && (i - 1 - start <= 2 || palindrome[start + 1][i - 1])) {  // Constraints: the substring must be palindrome
                palindrome[start][i] = true;  // memoization
                list.add(s.substring(start, i));  // Choices: s[start:i) which is palindrome
                find(s, i, list, palindrome);
                list.remove(list.size() - 1);
            }
        }
    }
}