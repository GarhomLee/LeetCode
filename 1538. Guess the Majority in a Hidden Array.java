https://leetcode.com/problems/guess-the-majority-in-a-hidden-array/

idea: Infer the first 5 elements. Then based on comparison of nums[i-3:i] and nums[i-4:i-1], infer nums[i] from nums[i - 4].
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int guessMajority(ArrayReader reader) {
        int n = reader.length();
        int[] nums = new int[n];
        nums[0] = 1;
        int q0 = reader.query(0, 1, 2, 3);
        int q4 = reader.query(1, 2, 3, 4);
        nums[4] = q4 == q0 ? nums[0] : 1 - nums[0];
        int q1 = reader.query(0, 2, 3, 4);
        nums[1] = q1 == q4 ? nums[0] : 1 - nums[0];
        int q2 = reader.query(0, 1, 3, 4);
        nums[2] = q2 == q1 ? nums[1] : 1 - nums[1];
        int q3 = reader.query(0, 1, 2, 4);
        nums[3] = q3 == q2 ? nums[2] : 1 - nums[2];
        
        int prev = q4;
        for (int i = 5; i < n; i++) {
            int curr = reader.query(i - 3, i - 2, i - 1, i);
            nums[i] = curr == prev ? nums[i - 4] : 1 - nums[i - 4];
            prev = curr;
        }
        
        
        int count0 = 0, count1 = 0;
        int idx0 = -1, idx1 = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                count0++;
                idx0 = i;
            } else {
                count1++;
                idx1 = i;
            }
        }
        
        if (count0 == count1) {
            return -1;
        }
        
        return count0 > count1 ? idx0 : idx1;
    }
}