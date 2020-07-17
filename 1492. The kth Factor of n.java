https://leetcode.com/problems/the-kth-factor-of-n/

idea: Brute force
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int kthFactor(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i<= n; i++) {
            if (n % i == 0) {
                list.add(i);
            }
        }
        
        return list.size() >= k ? list.get(k - 1) : -1;
    }
}