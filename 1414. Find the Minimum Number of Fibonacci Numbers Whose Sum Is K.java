https://leetcode.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/

idea: Greedy
    Find out all fibonacci numbers less than or equal to k (allow the last one greater than k).
    Then, pick from the largest number and increase count.
time complexity: O(k) (or log k?)
space complexity: O(k) (or log k?)

class Solution {
    public int findMinFibonacciNumbers(int k) {
        int f1 = 1, f2 = 1;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        while (f2 < k) {
            int f3 = f1 + f2;
            list.add(f3);
            f1 = f2;
            f2 = f3;
        }
        
        int count = 0, i = list.size() - 1;
        while (k > 0 && i >= 0) {
            if (list.get(i) <= k) {
                k -= list.get(i);
                count++;
            } else {
                i--;
            }
        }
        
        return count;
    }
}