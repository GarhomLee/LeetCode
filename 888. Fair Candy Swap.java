https://leetcode.com/problems/fair-candy-swap/

idea: Math
time complexity: O(a + b)
space complexity: O(b)

class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int sum1 = 0, sum2 = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : A) {
            sum1 += num;
        }
        for (int num : B) {
            set.add(num);
            sum2 += num;
        }
        
        int delta = (sum2 - sum1) / 2;
        
        for (int num : A) {
            if (set.contains(num + delta)) {
                return new int[]{num, num + delta};
            }
        }
        
        return null;
    }
}