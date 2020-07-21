https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/

solution1: 2-pass HashMap + Prefix Sum
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();    // sum -> index
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            map.put(sum, i);
        }
        
        int leftLen = n + 1, totalLen = n + 1;
        sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (map.containsKey(sum - target)) {
                leftLen = Math.min(leftLen, i - map.get(sum - target));
            }
            if (map.containsKey(sum + target)) {
                totalLen = Math.min(totalLen, leftLen + map.get(sum + target) - i);
            }
            
            // System.out.println("i="+i+",leftLen="+leftLen+",totalLen="+totalLen);
        }
        return totalLen <= n ? totalLen : -1;
    }
}


solution2: 1-pass HashMap with auxilary array (DP). Referring to: https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/discuss/685548/Java-Sliding-Window-with-dp-O(N)-20-lines
time complexity: O(n)
space complexity: O(n)