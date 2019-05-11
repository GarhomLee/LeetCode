https://leetcode.com/problems/letter-combinations-of-a-phone-number/

// 思路：用Backtracking模板，注意每一层对应的digit不同，在DFS的时候需要更新移动
// 注意：digits.length() == 0需要特殊处理

class Solution {
    String[] mapping = new String[]{"", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};  // hard-coded mapping
    List<String> res = new ArrayList<>();
    
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return res;  // ATTENTION to corner case
        find(digits, 0, "");
        return res;
    }
    private void find(String digits, int index, String s) {
        if (index == digits.length()) {  // GOAL: all digits are used
            res.add(s);
            return;
        }
        
        int digit = digits.charAt(index) - '0';
        for (char c: mapping[digit].toCharArray()) {  // CHOICES: all chars mapping to a digit
            find(digits, index + 1, s + c);  // index + 1 means move to the next digit in next level
        }
    }
}