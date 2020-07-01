https://leetcode.com/problems/boats-to-save-people/

idea: Sort + Two Pointers + Greedy
time complexity: O(n log n)
space complexity: O(n) for sorting

class Solution {
    public int numRescueBoats(int[] people, int limit) {
        int res = 0;
        Arrays.sort(people);
        int left = 0, right = people.length - 1;
        while (left <= right) {
            res++;
            
            if (people[left] + people[right] <= limit) {
                left++;
            } 
            
            right--;
        }
        
        return res;
    }
}