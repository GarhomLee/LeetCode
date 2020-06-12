https://leetcode.com/problems/counting-elements/

idea: HashSet
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int countElements(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        
        int count = 0;
        for (int num : arr) {
            if (set.contains(num + 1)) {
                count++;
            }
        }
        
        return count;
    }
}