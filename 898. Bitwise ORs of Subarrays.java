https://leetcode.com/problems/bitwise-ors-of-subarrays/

idea: DP + Bit Manipulation. Referring to video: https://www.youtube.com/watch?v=E_0BhmAUVNQ
time complextiy: O(32 * n)
space complexity: O(32 + n)?

class Solution {
    public int subarrayBitwiseORs(int[] A) {
        Set<Integer> res = new HashSet<>(); // all OR results
        Set<Integer> preSet = new HashSet<>();  // the OR results ending with previous index i - 1
        for (int num : A) {
            // get OR results ending with current index i
            // by using results from previous index i - 1
            Set<Integer> currSet = new HashSet<>();
            currSet.add(num);   // add current number itself
            res.add(num);   // add current number itself
            for (int preOrRes : preSet) {
                int currOrRes = num | preOrRes;
                currSet.add(currOrRes);
                res.add(currOrRes);
            }
            preSet = currSet;   // use for next level
        }
        
        return res.size();
    }
}