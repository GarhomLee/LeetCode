https://leetcode.com/problems/check-if-a-string-can-break-another-string/

idea: Greedy + Sort
time complexity: O(n log n)
space complexity: O(n)

class Solution {
    private boolean check(char[] arr1, char[] arr2) {
        int n = arr1.length;
        for (int i = 0; i < n; i++) {
            if (arr1[i] > arr2[i]) return false;
        }
        
        return true;
    }
    
    public boolean checkIfCanBreak(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        return check(arr1, arr2) || check(arr2, arr1);
    }
}