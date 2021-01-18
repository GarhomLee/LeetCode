https://leetcode.com/problems/custom-sort-string/

solution1: HashMap + Sort
time complexity: O(s) + O(t log t)
space complexity: O(t)

class Solution {
    public String customSortString(String S, String T) {
        Map<Character, Integer> idxMap = new HashMap<>();   // char -> idx at S
        for (int i = 0; i < S.length(); i++) {
            idxMap.put(S.charAt(i), i);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            if (!idxMap.containsKey(c)) {
                idxMap.put(c, 26);
            }
        }
        
        
        Character[] arr = new Character[T.length()];
        for (int i = 0; i < T.length(); i++) {
            arr[i] = T.charAt(i);
        }
        Arrays.sort(arr, (a,b) -> idxMap.get(a) - idxMap.get(b));
        
        StringBuilder sb = new StringBuilder();
        for (Character c : arr) {
            sb.append(c);
        }
        return sb.toString();
    }
}

solution2: Info Cache (count and write)
time complexity: O(s) + O(t log t)
space complexity: O(1)