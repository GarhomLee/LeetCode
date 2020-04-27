https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/

idea: Bit Manipulation + Backtracking
time complexity: O(n*l + 2^n), n=arr.size(), l=average length of arr[i]
space complexity: O(n), n=arr.size()

class Solution {
    /* return the maximum length of unique concatenation in bits[startIdx,size) */
    private int dfs(int pre, int startIdx, List<Integer> bits, Map<Integer, Integer> lenMap) {
        if (startIdx == bits.size()) {
            return 0;
        }
        
        int maxLen = 0;
        for (int i = startIdx; i < bits.size(); i++) {
            int curr = bits.get(i);
            if ((pre & curr) != 0) continue;
            
            int currLen = lenMap.get(curr) + dfs(pre | curr, i + 1, bits, lenMap);
            maxLen = Math.max(maxLen, currLen);
        }
        
        return maxLen;
    }
    
    public int maxLength(List<String> arr) {
        List<Integer> bits = new ArrayList<>(); // int representation of unique string
        Map<Integer, Integer> lenMap = new HashMap<>(); //  int -> num of 1s in this int
        for (int i = 0; i < arr.size(); i++) {
            int num = 0;
            String s = arr.get(i);
            boolean isUnique = true;    // record if the string has unique chars
            for (char c : s.toCharArray()) {
                isUnique &= (num & (1 << (c - 'a'))) == 0;
                num |= 1 << (c - 'a');
            }
            
            // only record the string with unique chars
            if (isUnique) {
                bits.add(num);
                lenMap.put(num, s.length());
            }
        }
        
        return dfs(0, 0, bits, lenMap);
    }
}